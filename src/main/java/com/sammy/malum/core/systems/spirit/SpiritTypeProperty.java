package com.sammy.malum.core.systems.spirit;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpiritTypeProperty extends Property<String> {

   private final ImmutableSet<String> values;
   private final Map<String, MalumSpiritType> types = Maps.newHashMap();

   public SpiritTypeProperty(String name, Collection<RegistryObject<MalumSpiritType>> types) {
      super(name, String.class);
      this.values = ImmutableSet.copyOf(types.stream().map(s -> s.get().getRegistryName().getPath()).collect(Collectors.toList()));

      for (RegistryObject<MalumSpiritType> type : types) {
         if (this.types.containsKey(type.get().getRegistryName().getPath())) {
            throw new IllegalArgumentException("Multiple values have the same name '" + type.get().getRegistryName().getPath() + "'");
         }

         this.types.put(type.get().getRegistryName().getPath(), type.get());
      }
   }

   @Override
   public Collection<String> getPossibleValues() {
      return this.values;
   }

   @Override
   public Optional<String> getValue(String value) {
      return values.stream().filter(v -> v.equals(value)).findAny();
   }

   @Override
   public String getName(String value) {
      return value;
   }

   @Override
   public boolean equals(Object pOther) {
      if (this == pOther) {
         return true;
      } else if (pOther instanceof SpiritTypeProperty property && super.equals(pOther)) {
         return this.values.equals(property.values) && this.types.equals(property.types);
      } else {
         return false;
      }
   }

   @Override
   public int generateHashCode() {
      int i = super.generateHashCode();
      i = 31 * i + this.values.hashCode();
      return 31 * i + this.types.hashCode();
   }
}