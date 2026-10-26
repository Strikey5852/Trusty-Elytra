package com.strikey.trusty_elytra.mixin;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class ElytraBreakMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setSharedFlag(IZ)V"), method = "updateFallFlying", cancellable = true)
    private void trusty_elytra$onSetSharedFlag(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!entity.onGround() && !entity.isPassenger() && !entity.hasEffect(MobEffects.LEVITATION) && entity.isFallFlying()) {
            ItemStack chest = entity.getItemBySlot(EquipmentSlot.CHEST);
            if (chest.is(Items.ELYTRA) && !ElytraItem.isFlyEnabled(chest)) {
                // keep gliding until landing
                ci.cancel();
            }
        }
    }
}
