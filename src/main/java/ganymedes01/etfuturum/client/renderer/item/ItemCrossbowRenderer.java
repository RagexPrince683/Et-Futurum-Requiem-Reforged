package ganymedes01.etfuturum.client.renderer.item;

import ganymedes01.etfuturum.items.ItemCrossbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/** Native sprite extrusion with the released crossbow's hand-display rotations. */
public final class ItemCrossbowRenderer implements IItemRenderer {
	@Override public boolean handleRenderType(ItemStack stack, ItemRenderType type) { return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON; }
	@Override public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack stack, ItemRendererHelper helper) { return helper == ItemRendererHelper.EQUIPPED_BLOCK; }
	@Override public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
		EntityLivingBase holder = data.length > 1 && data[1] instanceof EntityLivingBase living ? living : Minecraft.getMinecraft().thePlayer;
		IIcon icon = stack.getItem().getIcon(stack, 0);
		if (holder instanceof EntityPlayer player) icon = stack.getItem().getIcon(stack, 0, player, player.getItemInUse(), player.getItemInUseCount());
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_CURRENT_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_TEXTURE_BIT);
		GL11.glPushMatrix();
		try {
			GL11.glEnable(GL12.GL_RESCALE_NORMAL); GL11.glEnable(GL11.GL_ALPHA_TEST); GL11.glAlphaFunc(GL11.GL_GREATER, .1F);
			GL11.glColor4f(1, 1, 1, 1);
			boolean first = type == ItemRenderType.EQUIPPED_FIRST_PERSON;
			GL11.glTranslatef(.5F + (first ? 1.13F : 2) / 16, .5F + (first ? 3.2F : .1F) / 16, .5F + (first ? 1.13F : -3) / 16);
			GL11.glRotatef(-90, 1, 0, 0); GL11.glRotatef(first ? -55 : -60, 0, 0, 1);
			float size = first ? 1.02F : 1.35F; GL11.glScalef(size, size, size); GL11.glTranslatef(-.5F, -.5F, 0);
			Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationItemsTexture);
			draw(icon);
			if (stack.hasEffect(0)) {
				GL11.glDepthFunc(GL11.GL_EQUAL); GL11.glDisable(GL11.GL_LIGHTING); GL11.glEnable(GL11.GL_BLEND); GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
				Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("textures/misc/enchanted_item_glint.png"));
				GL11.glColor4f(.38F, .19F, .61F, 1);
				for (int pass = 0; pass < 2; pass++) {
					GL11.glMatrixMode(GL11.GL_TEXTURE); GL11.glPushMatrix();
					try { GL11.glScalef(.125F, .125F, .125F); GL11.glTranslatef((Minecraft.getSystemTime() % (pass == 0 ? 3000L : 4873L)) / (pass == 0 ? 3000F : 4873F) * (pass == 0 ? 8 : -8), 0, 0); GL11.glRotatef(pass == 0 ? -50 : 10, 0, 0, 1); draw(icon); }
					finally { GL11.glPopMatrix(); GL11.glMatrixMode(GL11.GL_MODELVIEW); }
				}
			}
		} finally { GL11.glPopMatrix(); GL11.glPopAttrib(); }
	}
	private void draw(IIcon icon) { if (icon != null) ItemRenderer.renderItemIn2D(Tessellator.instance, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), .0625F); }
}
