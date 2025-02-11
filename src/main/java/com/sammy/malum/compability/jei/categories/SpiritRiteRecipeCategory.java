package com.sammy.malum.compability.jei.categories;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.MalumMod;
import com.sammy.malum.client.screen.codex.*;
import com.sammy.malum.compability.jei.JEIHandler;
import com.sammy.malum.registry.common.item.ItemRegistry;
import com.sammy.malum.core.systems.rites.MalumRiteType;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

import static com.sammy.malum.MalumMod.malumPath;

public class SpiritRiteRecipeCategory implements IRecipeCategory<MalumRiteType> {
    public static final ResourceLocation UID = malumPath("spirit_rite");
    private final IDrawable background;
    private final IDrawable overlay;
    private final IDrawable icon;
    private final Font font;

    public SpiritRiteRecipeCategory(IGuiHelper guiHelper) {
        background = guiHelper.createBlankDrawable(142, 185);
        overlay = guiHelper.createDrawable(new ResourceLocation(MalumMod.MALUM, "textures/gui/spirit_rite_jei.png"), 0, 0, 142, 183);
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ItemRegistry.RUNEWOOD_TOTEM_BASE.get()));
        font = Minecraft.getInstance().font;
    }

    @Override
    public void draw(MalumRiteType rite, IRecipeSlotsView recipeSlotsView, PoseStack poseStack, double mouseX, double mouseY) {
        overlay.draw(poseStack);
        String translated = I18n.get(rite.translationIdentifier(false));
        ArcanaCodexHelper.renderText(poseStack, new TextComponent(translated), 71 - font.width(translated) / 2, 160);
    }

    @Override
    public RecipeType<MalumRiteType> getRecipeType() {
        return JEIHandler.RITES;
    }

    @Nonnull
    @Override
    @SuppressWarnings("removal")
    public ResourceLocation getUid() {
        return UID;
    }

    @Nonnull
    @Override
    @SuppressWarnings("removal")
    public Class<? extends MalumRiteType> getRecipeClass() {
        return MalumRiteType.class;
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("malum.jei." + UID.getPath());
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nonnull
    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MalumRiteType rite, IFocusGroup focuses) {
        for (int i = 0; i < rite.spirits.size(); i++) {
            builder.addSlot(RecipeIngredientRole.CATALYST, 63, 121 - 20 * i)
                 .addItemStack(rite.spirits.get(i).getSpiritShardItem().getDefaultInstance());
        }
    }
}
