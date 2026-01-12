package com.conquestfixnoslowness;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("conquestfixnoslowness")
public class ConquestFixNoSlowness {

    public ConquestFixNoSlowness() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPotionApplicable(MobEffectEvent.Applicable event) {
        if (event.getEntity() instanceof Player) {
            var level = event.getEntity().level();
            if (level != null) {
                var effectKey = level.registryAccess()
                    .registryOrThrow(Registries.MOB_EFFECT)
                    .getKey(event.getEffectInstance().getEffect());

                if (effectKey != null && effectKey.equals(new ResourceLocation("conquest", "custom_slowness"))) {
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }
}
