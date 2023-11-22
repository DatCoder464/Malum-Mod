package com.sammy.malum.common.spiritrite.greater;

import com.sammy.malum.common.block.curiosities.totem.TotemBaseBlockEntity;
import com.sammy.malum.common.packets.particle.curiosities.rite.generic.MajorEntityEffectParticlePacket;
import com.sammy.malum.core.systems.rites.MalumRiteEffect;
import com.sammy.malum.core.systems.rites.MalumRiteType;
import com.sammy.malum.registry.common.DamageSourceRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

import static com.sammy.malum.registry.common.PacketRegistry.MALUM_CHANNEL;
import static com.sammy.malum.registry.common.SpiritTypeRegistry.*;

public class EldritchWickedRiteType extends MalumRiteType {
    public EldritchWickedRiteType() {
        super("greater_wicked_rite", ELDRITCH_SPIRIT.get(), ARCANE_SPIRIT.get(), WICKED_SPIRIT.get(), WICKED_SPIRIT.get());
    }

    @Override
    public MalumRiteEffect getNaturalRiteEffect() {
        return new MalumRiteEffect(MalumRiteEffect.MalumRiteEffectCategory.LIVING_ENTITY_EFFECT) {
            @Override
            public void doRiteEffect(TotemBaseBlockEntity totemBase) {
                getNearbyEntities(totemBase, LivingEntity.class, e -> !(e instanceof Player)).forEach(e -> {
                    if (e.getHealth() <= 2.5f && !e.isInvulnerableTo(DamageSourceRegistry.VOODOO)) {
                        MALUM_CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> e), new MajorEntityEffectParticlePacket(getEffectSpirit().getPrimaryColor(), e.getX(), e.getY()+ e.getBbHeight() / 2f, e.getZ()));
                        e.hurt(DamageSourceRegistry.VOODOO, 10f);
                    }
                });
            }
        };
    }

    @Override
    public MalumRiteEffect getCorruptedEffect() {
        return new MalumRiteEffect(MalumRiteEffect.MalumRiteEffectCategory.LIVING_ENTITY_EFFECT) {
            @Override
            public void doRiteEffect(TotemBaseBlockEntity totemBase) {
                Map<Class<? extends Animal>, List<Animal>> animalMap = getNearbyEntities(totemBase, Animal.class, e -> e.getAge() > 0 && !e.isInvulnerableTo(DamageSourceRegistry.VOODOO)).collect(Collectors.groupingBy(Animal::getClass));
                for (List<Animal> animals : animalMap.values()) {
                    if (animals.size() < 20) {
                        return;
                    }
                    int maxKills = animals.size() - 20;
                    animals.removeIf(Animal::isInLove);
                    for (Animal entity : animals) {
                        entity.hurt(DamageSourceRegistry.VOODOO, entity.getMaxHealth());
                        MALUM_CHANNEL.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), new MajorEntityEffectParticlePacket(WICKED_SPIRIT.get().getPrimaryColor(), entity.getX(), entity.getY() + entity.getBbHeight() / 2f, entity.getZ()));
                        if (maxKills-- <= 0) {
                            return;
                        }
                    }
                }
            }
        };
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