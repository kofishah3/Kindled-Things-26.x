package lemokodev.kindledthings.entity.client.sunfish;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

import javax.swing.text.html.parser.Entity;

public class SunFishModel extends EntityModel<SunFishRenderState>{
    private final ModelPart sunfish;
    private final ModelPart body;
    private final ModelPart top_fin;
    private final ModelPart topt;
    private final ModelPart topb;
    private final ModelPart bottom_fin;
    private final ModelPart bottomb;
    private final ModelPart bottomt;
    private final ModelPart left_fin;
    private final ModelPart right_fin;

    private final KeyframeAnimation idlingAnimation;

    public SunFishModel(ModelPart root) {
        super(root);
        this.sunfish = root.getChild("sunfish");
        this.body = this.sunfish.getChild("body");
        this.top_fin = this.body.getChild("top_fin");
        this.topt = this.top_fin.getChild("topt");
        this.topb = this.top_fin.getChild("topb");
        this.bottom_fin = this.body.getChild("bottom_fin");
        this.bottomb = this.bottom_fin.getChild("bottomb");
        this.bottomt = this.bottom_fin.getChild("bottomt");
        this.left_fin = this.body.getChild("left_fin");
        this.right_fin = this.body.getChild("right_fin");

        this.idlingAnimation = SunFishAnimations.IDLE_SWIM.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition sunfish = partdefinition.addOrReplaceChild("sunfish", CubeListBuilder.create(), PartPose.offset(0.0F, 14.0F, 2.0F));

        PartDefinition body = sunfish.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -10.0F, -10.0F, 4.0F, 19.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(0, 39).addBox(-1.5F, -9.0F, 10.0F, 3.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition top_fin = body.addOrReplaceChild("top_fin", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -9.0F, 5.0F, -0.2182F, 0.0F, 0.0F));

        PartDefinition topt = top_fin.addOrReplaceChild("topt", CubeListBuilder.create().texOffs(46, 39).addBox(-1.0F, -5.0F, -3.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

        PartDefinition topb = top_fin.addOrReplaceChild("topb", CubeListBuilder.create().texOffs(14, 39).addBox(-1.0F, -7.0F, -5.0F, 2.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

        PartDefinition bottom_fin = body.addOrReplaceChild("bottom_fin", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 9.0F, 5.0F, -2.9234F, 0.0F, 0.0F));

        PartDefinition bottomb = bottom_fin.addOrReplaceChild("bottomb", CubeListBuilder.create().texOffs(30, 39).addBox(-1.0F, -6.0F, -5.0F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 2.0F));

        PartDefinition bottomt = bottom_fin.addOrReplaceChild("bottomt", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, -5.0F, -3.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(48, 11).addBox(0.0F, -1.5F, 0.1F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -2.5F, -3.1F));

        PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(48, 14).addBox(-2.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -2.5F, -3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(SunFishRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

        this.idlingAnimation.apply(state.idleAnimationState, state.ageInTicks, 1f);

    }
}
