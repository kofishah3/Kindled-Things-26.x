package lemokodev.kindledthings.entity.client.copperbuddy;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CopperBuddyModel extends EntityModel<CopperBuddyRenderState> {
    private final ModelPart coppperbuddy;
    private final ModelPart head;
    private final ModelPart coffer;
    private final ModelPart topside;
    private final ModelPart body;
    private final ModelPart windingkey;
    private final ModelPart rightleg;
    private final ModelPart leftleg;
    private final ModelPart rightarm;
    private final ModelPart leftarm;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idlingAnimation;
    private final KeyframeAnimation idleLookAroundAnimation;

    public CopperBuddyModel(ModelPart root) {
        super(root);
        this.coppperbuddy = root.getChild("coppperbuddy");
        this.head = this.coppperbuddy.getChild("head");
        this.coffer = this.head.getChild("coffer");
        this.topside = this.coffer.getChild("topside");
        this.body = this.coppperbuddy.getChild("body");
        this.windingkey = this.body.getChild("windingkey");
        this.rightleg = this.coppperbuddy.getChild("rightleg");
        this.leftleg = this.coppperbuddy.getChild("leftleg");
        this.rightarm = this.coppperbuddy.getChild("rightarm");
        this.leftarm = this.coppperbuddy.getChild("leftarm");

        this.walkingAnimation = CopperBuddyAnimations.WALK.bake(root);
        this.idlingAnimation = CopperBuddyAnimations.IDLE.bake(root);
        this.idleLookAroundAnimation = CopperBuddyAnimations.IDLE_LOOKAROUND.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition coppperbuddy = partdefinition.addOrReplaceChild("coppperbuddy", CubeListBuilder.create(), PartPose.offset(0.0F, 18.0F, 0.0F));

        PartDefinition head = coppperbuddy.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -5.0F, 8.0F, 5.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(14, 39).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

        PartDefinition coffer = head.addOrReplaceChild("coffer", CubeListBuilder.create().texOffs(0, 28).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition topside = coffer.addOrReplaceChild("topside", CubeListBuilder.create().texOffs(32, 15).addBox(-4.0F, -2.0F, -8.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(36, 12).addBox(-3.0F, -1.0F, -9.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 39).addBox(2.0F, -1.0F, -9.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 4.0F));

        PartDefinition body = coppperbuddy.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 15).addBox(-4.0F, -3.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

        PartDefinition windingkey = body.addOrReplaceChild("windingkey", CubeListBuilder.create().texOffs(24, 44).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(23, 43).addBox(0.0F, -1.5F, 1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(24, 44).addBox(0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(23, 43).addBox(0.0F, 0.5F, 1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 4.0F));

        PartDefinition rightleg = coppperbuddy.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(32, 33).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(-0.002F)), PartPose.offset(-2.0F, 4.0F, -2.0F));

        PartDefinition leftleg = coppperbuddy.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(32, 33).mirror().addBox(-2.0F, 0.0F, -1.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(-0.002F)).mirror(false), PartPose.offset(2.0F, 4.0F, -2.0F));

        PartDefinition rightarm = coppperbuddy.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(36, 0).addBox(-3.0F, -8.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 0.0F));

        PartDefinition leftarm = coppperbuddy.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(36, 0).mirror().addBox(0.0F, -8.0F, -2.0F, 3.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(CopperBuddyRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(state.yRot, state.xRot);

        this.walkingAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2f);
        this.idlingAnimation.apply(state.idleAnimationState, state.ageInTicks, 1f);
        this.idleLookAroundAnimation.apply(state.idleLookAroundAnimationState, state.ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch  * ((float)Math.PI / 180f);
    }
}
