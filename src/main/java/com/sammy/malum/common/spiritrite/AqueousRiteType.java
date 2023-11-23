package com.sammy.malum.common.spiritrite;

import com.sammy.malum.registry.common.MobEffectRegistry;
import com.sammy.malum.core.systems.rites.PotionRiteEffect;
import com.sammy.malum.core.systems.rites.MalumRiteEffect;
import com.sammy.malum.core.systems.rites.MalumRiteType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

import static com.sammy.malum.registry.common.SpiritTypeRegistry.AQUEOUS;
import static com.sammy.malum.registry.common.SpiritTypeRegistry.ARCANE;

public class AqueousRiteType extends MalumRiteType {
    public AqueousRiteType() {
        super("aqueous_rite", ARCANE.get(), AQUEOUS.get(), AQUEOUS.get());
    }

    @Override
    public MalumRiteEffect getNaturalRiteEffect() {
        return new PotionRiteEffect(LivingEntity.class, MobEffectRegistry.POSEIDONS_GRASP);
    }

    @Override
    public MalumRiteEffect getCorruptedEffect() {
        return new PotionRiteEffect(LivingEntity.class, MobEffectRegistry.ANGLERS_LURE);
    }

    @Override
    public MalumRiteType setRegistryName(ResourceLocation name) {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return null;
    }

    @Override
    public Class<MalumRiteType> getRegistryType() {
        return null;
    }
}