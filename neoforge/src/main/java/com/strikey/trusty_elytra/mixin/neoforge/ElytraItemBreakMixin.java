package com.strikey.trusty_elytra.mixin.neoforge;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElytraItem.class)
public class ElytraItemBreakMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V"), method = "elytraFlightTick")
    private void trusty_elytra$onElytraBreak(ItemStack stack, LivingEntity entity, int flightTicks, CallbackInfoReturnable<Boolean> cir) {
        ItemStack chest = entity.getItemBySlot(EquipmentSlot.CHEST);
        if (chest.is(Items.ELYTRA)) {
            int damage = chest.getDamageValue();
            int maxDamage = chest.getMaxDamage();
            if (damage == maxDamage - 2) {
                // trigger vanilla break sound + particles
                entity.onEquippedItemBroken(chest.getItem(), EquipmentSlot.CHEST);
            }
        }
    }
}
