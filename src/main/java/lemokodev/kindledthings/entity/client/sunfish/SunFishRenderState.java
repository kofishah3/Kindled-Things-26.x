package lemokodev.kindledthings.entity.client.sunfish;

import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class SunFishRenderState extends LivingEntityRenderState {
    public float sunfishScale = 1.0F;

    public final AnimationState idleAnimationState = new AnimationState();
}

