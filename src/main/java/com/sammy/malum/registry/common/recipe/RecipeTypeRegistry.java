 package com.sammy.malum.registry.common.recipe;

import com.sammy.malum.MalumMod;
import com.sammy.malum.common.recipe.*;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

 public class RecipeTypeRegistry {

     public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registry.RECIPE_TYPE.key(), MalumMod.MALUM);

     public static final RegistryObject<RecipeType<FavorOfTheVoidRecipe>> VOID_FAVOR = RECIPE_TYPES.register(FavorOfTheVoidRecipe.NAME, () -> registerRecipeType(FavorOfTheVoidRecipe.NAME));
     public static final RegistryObject<RecipeType<SpiritInfusionRecipe>> SPIRIT_INFUSION = RECIPE_TYPES.register(SpiritInfusionRecipe.NAME, () -> registerRecipeType(SpiritInfusionRecipe.NAME));
     public static final RegistryObject<RecipeType<SpiritFocusingRecipe>> SPIRIT_FOCUSING = RECIPE_TYPES.register(SpiritFocusingRecipe.NAME, () -> registerRecipeType(SpiritFocusingRecipe.NAME));
     public static final RegistryObject<RecipeType<SpiritTransmutationRecipe>> SPIRIT_TRANSMUTATION = RECIPE_TYPES.register(SpiritTransmutationRecipe.NAME, () -> registerRecipeType(SpiritTransmutationRecipe.NAME));
     public static final RegistryObject<RecipeType<SpiritRepairRecipe>> SPIRIT_REPAIR = RECIPE_TYPES.register(SpiritRepairRecipe.NAME, () -> registerRecipeType(SpiritRepairRecipe.NAME));

     public static <T extends Recipe<?>> RecipeType<T> registerRecipeType(final String identifier) {
         return new RecipeType<>() {
             public String toString() {
                 return MalumMod.MALUM + ":" + identifier;
             }
         };
     }
 }