package com.strikey.trusty_elytra.mixin.fabric;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.FireworkRocketItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireworkRocketItem.class)
public class FireworkRocketUseMixin {
    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private void trusty_elytra$onUse(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (player.isFallFlying()) {
            ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
            if (chest.is(Items.ELYTRA) && !ElytraItem.isFlyEnabled(chest)) {
                // Elytra broke mid-flight: don't use firework rocket
                cir.setReturnValue(InteractionResultHolder.pass(player.getItemInHand(interactionHand)));
                cir.cancel();
            }
        }
    }
}
