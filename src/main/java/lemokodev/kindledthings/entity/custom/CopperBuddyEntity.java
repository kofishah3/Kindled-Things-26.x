package lemokodev.kindledthings.entity.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CopperBuddyEntity extends TamableAnimal implements InventoryCarrier {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 5;

    public final AnimationState idleLookAroundAnimationState = new AnimationState();
    private int idleLookAroundAnimationTimeout = 0;

    private final SimpleContainer inventory = new SimpleContainer(9);

    public CopperBuddyEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1f, 5f, 2f));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 5f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 40d)
                .add(Attributes.MOVEMENT_SPEED, 0.25d)
                .add(Attributes.TEMPT_RANGE, 20d)
                .add(Attributes.FOLLOW_RANGE, 32d)
                .add(Attributes.SAFE_FALL_DISTANCE, 15d)
                .add(Attributes.ARMOR, 10d);
    }

    // ANIMATIONS
    private void setupAnimationStates() {
        boolean isStill = this.getDeltaMovement().horizontalDistanceSqr() < 1.0E-6;

        if(!isStill) {
            idleLookAroundAnimationState.stop();
            idleLookAroundAnimationTimeout = 0;
        } else if(this.idleLookAroundAnimationTimeout > 0) {
            --this.idleLookAroundAnimationTimeout;
            if(this.idleLookAroundAnimationTimeout <= 0) {
                this.idleLookAroundAnimationState.stop();
                this.idleAnimationTimeout = 0;
            }
            return;
        }

        if(isStill && this.random.nextInt(200) == 0) {
            this.idleAnimationState.stop();
            this.idleLookAroundAnimationTimeout = 40;
            this.idleLookAroundAnimationState.start(this.tickCount);
            return;
        }

        if(!isStill) {
          idleAnimationState.stop();
          idleAnimationTimeout = 0;
        } else if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
        return null;
    }

    private void tryToTame(Player player) {
        if (this.random.nextInt(3) == 0) {
            this.tame(player);
            this.navigation.stop();
            this.setTarget(null);
            this.setOrderedToSit(true);
            this.level().broadcastEntityEvent(this, (byte) 7);
        } else {
            this.level().broadcastEntityEvent(this, (byte) 6);
        }
    }

    @Override
    public boolean canMate(final Animal partner) {
        return false;
    }

    protected SoundEvent getHurtSound() {
        return SoundEvents.COPPER_GOLEM_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.COPPER_GOLEM_DEATH;
    }

    @Override
    public @NonNull InteractionResult mobInteract(final Player player, final InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if(this.isTame()) {
            if (itemStack.is(Items.COPPER_INGOT) && this.getHealth() < this.getMaxHealth()) {
                this.feed(player, hand, itemStack, 2.0f, 2.0f);
                return InteractionResult.SUCCESS;
            }

            InteractionResult interactionResult = super.mobInteract(player, hand);

            if(player.isShiftKeyDown() && !interactionResult.consumesAction() && this.isOwnedBy(player)) {
                player.openMenu(new SimpleMenuProvider(
                        (containerId, playerInventory, player1) ->
                                new ChestMenu(MenuType.GENERIC_9x1, containerId, playerInventory, this.inventory, 1),
                        this.getDisplayName()
                ));

                return InteractionResult.SUCCESS;
            } else if (!interactionResult.consumesAction() && this.isOwnedBy(player)) {
                this.setOrderedToSit(!this.isOrderedToSit());
                this.jumping = false;
                this.navigation.stop();
                this.setTarget(null);
                return InteractionResult.SUCCESS.withoutItem();
            }


            return interactionResult;

        } else if(!this.level().isClientSide() && itemStack.is((Items.COPPER_INGOT))) {
            itemStack.consume(1, player);
            this.tryToTame(player);
            return InteractionResult.SUCCESS_SERVER;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public SimpleContainer getInventory() {
        return inventory;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        this.writeInventoryToTag(output);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.readInventoryFromTag(input);
    }

    @Override
    public boolean canPickUpLoot() {
        return super.canPickUpLoot();
    }

}
