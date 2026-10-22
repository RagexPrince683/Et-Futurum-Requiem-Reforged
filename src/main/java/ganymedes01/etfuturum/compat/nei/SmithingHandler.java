package ganymedes01.etfuturum.compat.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import ganymedes01.etfuturum.ModBlocks;
import ganymedes01.etfuturum.recipes.SmithingTableRecipes;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import java.util.*;

/** Views the authoritative smithing registry, including scripts and ore-dictionary alternatives. */
public class SmithingHandler extends TemplateRecipeHandler {
	private class Upgrade extends CachedRecipe {
		private final List<PositionedStack> ingredients;
		private final PositionedStack output;
		Upgrade(SmithingTableRecipes.SmithingTableRecipe recipe) {
			ingredients = Arrays.asList(new PositionedStack(recipe.getInputItem(), 25, 18), new PositionedStack(recipe.getMaterial(), 66, 18));
			output = new PositionedStack(recipe.getRecipeOutput().copy(), 120, 18);
		}
		@Override public List<PositionedStack> getIngredients() { return getCycledIngredients(cycleticks / 20, ingredients); }
		@Override public PositionedStack getResult() { return output; }
		@Override public List<PositionedStack> getOtherStacks() { return Collections.singletonList(new PositionedStack(ModBlocks.SMITHING_TABLE.newItemStack(), 66, 45)); }
	}
	private boolean matches(Object input, ItemStack stack) {
		if (input instanceof ItemStack item) return OreDictionary.itemMatches(item, stack, false);
		if (input instanceof List<?> alternatives) for (Object value : alternatives) if (value instanceof ItemStack item && OreDictionary.itemMatches(item, stack, false)) return true;
		return false;
	}
	@Override public void loadCraftingRecipes(String id, Object... results) { if (id.equals(getOverlayIdentifier())) for (SmithingTableRecipes.SmithingTableRecipe recipe : SmithingTableRecipes.getInstance().getRecipes()) arecipes.add(new Upgrade(recipe)); else super.loadCraftingRecipes(id, results); }
	@Override public void loadCraftingRecipes(ItemStack output) { for (SmithingTableRecipes.SmithingTableRecipe recipe : SmithingTableRecipes.getInstance().getRecipes()) if (recipe.getRecipeOutput().isItemEqual(output)) arecipes.add(new Upgrade(recipe)); }
	@Override public void loadUsageRecipes(ItemStack input) {
		if (input.getItem() == ModBlocks.SMITHING_TABLE.getItem()) { loadCraftingRecipes(getOverlayIdentifier()); return; }
		for (SmithingTableRecipes.SmithingTableRecipe recipe : SmithingTableRecipes.getInstance().getRecipes()) if (matches(recipe.getInputItem(), input) || matches(recipe.getMaterial(), input)) arecipes.add(new Upgrade(recipe));
	}
	@Override public void drawBackground(int recipe) {
		org.lwjgl.opengl.GL11.glColor4f(1, 1, 1, 1);
		GuiDraw.changeTexture(getGuiTexture());
		// Reuse the workstation's empty slot frame behind each 16x16 ingredient/result.
		for (int x : new int[]{24, 65, 119}) GuiDraw.drawTexturedModalRect(x, 17, 26, 46, 18, 18);
	}
	@Override public void drawExtras(int recipe) { GuiDraw.drawString("+", 49, 23, 0x404040, false); GuiDraw.drawString(">", 100, 23, 0x404040, false); }
	@Override public String getOverlayIdentifier() { return "etfuturum.smithing"; }
	@Override public String getGuiTexture() { return "textures/gui/container/smithing.png"; }
	@Override public String getRecipeName() { return I18n.format("tile.etfuturum.smithing_table.name"); }
	@Override public int recipiesPerPage() { return 2; }
}
