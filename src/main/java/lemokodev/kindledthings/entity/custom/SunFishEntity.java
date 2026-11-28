package lemokodev.kindledthings.entity.custom;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.fish.AbstractFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.IntFunction;

public class SunFishEntity extends AbstractFish {
    private static final String TAG_TYPE = "type";
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private static final EntityDataAccessor<Integer> DATA_TYPE = SynchedEntityData.defineId(SunFishEntity.class, EntityDataSerializers.INT);

    public SunFishEntity(EntityType<? extends AbstractFish> type, Level level) {
        super(type, level);
        this.refreshDimensions();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return AbstractFish.createAttributes()
                .add(Attributes.MAX_HEALTH, 40d)
                .add(Attributes.MOVEMENT_SPEED, 0.2d);
    }
    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.SALMON_FLOP;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.SALMON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.SALMON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SALMON_DEATH;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return ItemStack.EMPTY;
    }

    @Override
    protected InteractionResult mobInteract(final Player player, final InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    protected void defineSynchedData(final SynchedEntityData.Builder entityData) {
        super.defineSynchedData(entityData);
        entityData.define(DATA_TYPE, SunFishEntity.Variant.DEFAULT.id());
    }

    @Override
    public void tick() {
        super.tick();
        if(this.level().isClientSide()) {
            this.setupAnimationState();
        }
    }

    private void setupAnimationState() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    private void setVariant(final SunFishEntity.Variant variant) {
        this.entityData.set(DATA_TYPE, variant.id);
    }

    public SunFishEntity.Variant getVariant() {return SunFishEntity.Variant.BY_ID.apply(this.entityData.get(DATA_TYPE)); }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(
            final ServerLevelAccessor level, final DifficultyInstance difficulty, final EntitySpawnReason spawnReason, final @Nullable SpawnGroupData groupData
    ) {
        WeightedList.Builder<SunFishEntity.Variant> builder = WeightedList.builder();
        builder.add(SunFishEntity.Variant.SMALL, 20);
        builder.add(SunFishEntity.Variant.MEDIUM, 20);
        builder.add(SunFishEntity.Variant.LARGE, 20);
        builder.add(SunFishEntity.Variant.XLARGE, 20);
        builder.build().getRandom(this.random).ifPresent(this::setVariant);
        return super.finalizeSpawn(level, difficulty, spawnReason, groupData);
    }

    public float getSunfishScale() {
        return this.getVariant().boundingBoxScale;
    }

    @Override
    protected EntityDimensions getDefaultDimensions(final Pose pose) {
        return super.getDefaultDimensions(pose).scale(this.getSunfishScale());

    }

    @Override
    protected void addAdditionalSaveData(final ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store(TAG_TYPE, Variant.CODEC, this.getVariant());
    }

    @Override
    protected void readAdditionalSaveData(final ValueInput input) {
        super.readAdditionalSaveData(input);
        this.setVariant(input.read(TAG_TYPE, Variant.CODEC).orElse(Variant.DEFAULT));
    }

    public enum Variant implements StringRepresentable {
        SMALL ("small", 0, 0.75F),
        MEDIUM("medium", 1, 1.0F),
        LARGE ("large", 2, 1.5F),
        XLARGE("extralarge", 3, 2F);

        public static final SunFishEntity.Variant DEFAULT = MEDIUM;
        public static final Codec<SunFishEntity.Variant> CODEC = StringRepresentable.fromEnum(SunFishEntity.Variant::values);
        private static final IntFunction<SunFishEntity.Variant> BY_ID = ByIdMap.continuous(SunFishEntity.Variant::id, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        public static final StreamCodec<ByteBuf, SunFishEntity.Variant> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, SunFishEntity.Variant::id);
        private final String name;
        private final int id;
        private final float boundingBoxScale;

        Variant(final String name, final int id, final float boundingBoxScale) {
            this.name = name;
            this.id = id;
            this.boundingBoxScale = boundingBoxScale;
        }

        public int id() {
            return this.id;
        }

        @Override
        public @NonNull String getSerializedName() {
            return this.name;
        }
    }
}
