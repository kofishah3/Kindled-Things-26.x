package lemokodev.kindledthings.entity.client.copperbuddy;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class CopperBuddyRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState idleLookAroundAnimationState = new AnimationState();
}
