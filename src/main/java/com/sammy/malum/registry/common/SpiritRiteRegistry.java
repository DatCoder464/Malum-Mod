package com.sammy.malum.registry.common;

import com.sammy.malum.common.spiritrite.*;
import com.sammy.malum.common.spiritrite.greater.*;
import com.sammy.malum.core.systems.rites.MalumRiteType;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.*;

import java.util.List;
import java.util.function.Supplier;

import static com.sammy.malum.MalumMod.MALUM;

public class SpiritRiteRegistry {
    public static final DeferredRegister<MalumRiteType> RITES = DeferredRegister.create(new ResourceLocation(MALUM, "rites"), MALUM);
    public static RegistryObject<MalumRiteType> SACRED_RITE = RITES.register("sacred_rite", SacredRiteType::new);
    public static RegistryObject<MalumRiteType> ELDRITCH_SACRED_RITE = RITES.register("eldritch_sacred_rite", EldritchSacredRiteType::new);
    public static RegistryObject<MalumRiteType> WICKED_RITE = RITES.register("wicked_rite", WickedRiteType::new);
    public static RegistryObject<MalumRiteType> ELDRITCH_WICKED_RITE = RITES.register("eldritch_rite", EldritchWickedRiteType::new);

    public static RegistryObject<MalumRiteType> EARTHEN_RITE = RITES.register("earthen_rite", EarthenRiteType::new);
    public static RegistryObject<MalumRiteType> ELDRITCH_EARTHEN_RITE = RITES.register("eldritch_earthen_rite", EldritchEarthenRiteType::new);
    public static RegistryObject<MalumRiteType> INFERNAL_RITE = RITES.register("infernal_rite", InfernalRiteType::new);
    public static RegistryObject<MalumRiteType> ELDRITCH_INFERNAL_RITE = RITES.register("eldritch_infernal_rite", EldritchInfernalRiteType::new);
    public static RegistryObject<MalumRiteType> AERIAL_RITE = RITES.register("aerial_rite", AerialRiteType::new);
    public static RegistryObject<MalumRiteType> ELDRITCH_AERIAL_RITE = RITES.register("eldritch_aerial_rite", EldritchAerialRiteType::new);
    public static RegistryObject<MalumRiteType> AQUEOUS_RITE = RITES.register("aqueous_rite", AqueousRiteType::new);
    public static RegistryObject<MalumRiteType> ELDRITCH_AQUEOUS_RITE = RITES.register("eldritch_aqueous_rite", EldritchAqueousRiteType::new);

    public static RegistryObject<MalumRiteType> ARCANE_RITE = RITES.register("arcane_rite", ArcaneRiteType::new);

    public static List<MalumRiteType> getValues() {
        List<MalumRiteType> values = SACRED_RITE.stream().toList();
        for (RegistryObject<MalumRiteType> rite : RITES.getEntries()) {
            values.add(rite.get());
        }
        return values;
    }

    public static MalumRiteType getRite(String identifier) {
        for (MalumRiteType rite : getValues()) {
            if (rite.identifier.equals(identifier)) {
                return rite;
            }
        }
        return null;
    }

    public static MalumRiteType getRite(List<MalumSpiritType> spirits) {
        for (MalumRiteType rite : getValues()) {
            if (rite.spirits.equals(spirits)) {
                return rite;
            }
        }
        return null;
    }
}
