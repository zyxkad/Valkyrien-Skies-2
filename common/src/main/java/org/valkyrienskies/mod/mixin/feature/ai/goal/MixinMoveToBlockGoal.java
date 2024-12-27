package org.valkyrienskies.mod.mixin.feature.ai.goal;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.valkyrienskies.mod.common.VSGameUtilsKt;

@Mixin(MoveToBlockGoal.class)
public class MixinMoveToBlockGoal {
    @Shadow
    @Final
    protected PathfinderMob mob;

    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;closerToCenterThan(Lnet/minecraft/core/Position;D)Z"))
    private boolean onCloserToCenterThan(BlockPos instance, Position position, double v, Operation<Boolean> original) {
        return original.call(new BlockPos(VSGameUtilsKt.toWorldCoordinates(this.mob.level, instance)), position, v);
    }
}
