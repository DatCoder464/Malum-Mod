package com.sammy.malum.common.effect;

import com.sammy.malum.registry.common.SpiritTypeRegistry;
import com.sammy.malum.registry.common.MobEffectRegistry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import team.lodestar.lodestone.helpers.ColorHelper;

public class InfernalAura extends MobEffect {
    public InfernalAura() {
        super(MobEffectCategory.BENEFICIAL, ColorHelper.getColor(SpiritTypeRegistry.INFERNAL_SPIRIT.get().getPrimaryColor()));
        addAttributeModifier(Attributes.ATTACK_SPEED, "0a74b987-a6ec-4b9f-815e-a589bf435b93", 0.2f, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) {
    }

    public static void increaseDigSpeed(PlayerEvent.BreakSpeed event) {
        Player player = event.getPlayer();
        if (player.hasEffect(MobEffectRegistry.MINERS_RAGE.get())) {
            event.setNewSpeed(event.getOriginalSpeed() * (1 + 0.2f *player.getEffect(MobEffectRegistry.MINERS_RAGE.get()).getAmplifier()));
        }
    }
}