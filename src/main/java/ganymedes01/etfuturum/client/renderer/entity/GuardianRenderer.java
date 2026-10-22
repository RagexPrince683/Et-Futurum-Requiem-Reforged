package ganymedes01.etfuturum.client.renderer.entity;

import ganymedes01.etfuturum.entities.EntityGuardian;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuardianRenderer extends RenderLiving {
	private static final ResourceLocation NORMAL = texture("guardian"), ELDER = texture("elder_guardian");
	private static ResourceLocation texture(String name) { return new ResourceLocation("etfuturum", "textures/entity/" + name + ".png"); }
	public GuardianRenderer() { super(new Model(), .5F); }
	@Override protected ResourceLocation getEntityTexture(Entity entity) { return ((EntityGuardian) entity).isElder() ? ELDER : NORMAL; }
	@Override protected void preRenderCallback(EntityLivingBase entity, float partialTicks) {
		if (((EntityGuardian) entity).isElder()) GL11.glScalef(2.35F, 2.35F, 2.35F);
	}
	@Override public void doRender(EntityLiving entity, double x, double y, double z, float yaw, float partialTicks) {
		super.doRender(entity, x, y, z, yaw, partialTicks);
		EntityGuardian guardian = (EntityGuardian) entity;
		Entity target = guardian.worldObj.getEntityByID(guardian.beamTarget());
		if (guardian.beamTarget() == 0 || target == null) return;
		double dx = target.prevPosX + (target.posX - target.prevPosX) * partialTicks - (guardian.prevPosX + (guardian.posX - guardian.prevPosX) * partialTicks);
		double dy = target.prevPosY + (target.posY - target.prevPosY) * partialTicks + target.height * .5 - (guardian.prevPosY + (guardian.posY - guardian.prevPosY) * partialTicks + guardian.getEyeHeight());
		double dz = target.prevPosZ + (target.posZ - target.prevPosZ) * partialTicks - (guardian.prevPosZ + (guardian.posZ - guardian.prevPosZ) * partialTicks);
		double length = Math.sqrt(dx * dx + dy * dy + dz * dz);
		if (length < .001) return;
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT | GL11.GL_COLOR_BUFFER_BIT | GL11.GL_CURRENT_BIT);
		GL11.glPushMatrix();
		GL11.glTranslated(x, y + guardian.getEyeHeight(), z);
		GL11.glRotated(Math.atan2(dx, dz) * 180 / Math.PI, 0, 1, 0);
		GL11.glRotated(-Math.atan2(dy, Math.sqrt(dx * dx + dz * dz)) * 180 / Math.PI, 1, 0, 0);
		GL11.glDisable(GL11.GL_TEXTURE_2D); GL11.glDisable(GL11.GL_LIGHTING); GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_BLEND); GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads(); tessellator.setColorRGBA(180, 200, 75, 210);
		for (int face = 0; face < 4; face++) {
			double a = face * Math.PI / 2, b = a + Math.PI / 2;
			double ax = Math.cos(a) * .08, ay = Math.sin(a) * .08, bx = Math.cos(b) * .08, by = Math.sin(b) * .08;
			tessellator.addVertex(ax, ay, 0); tessellator.addVertex(bx, by, 0); tessellator.addVertex(bx, by, length); tessellator.addVertex(ax, ay, length);
		}
		tessellator.draw(); GL11.glPopMatrix(); GL11.glPopAttrib();
	}
	private static class Model extends ModelBase {
		private final ModelRenderer body, tail, fin;
		private final ModelRenderer[] spikes = new ModelRenderer[12];
		Model() {
			textureWidth = 64; textureHeight = 64;
			body = new ModelRenderer(this, 0, 0); body.addBox(-6, -6, -8, 12, 12, 16); body.setRotationPoint(0, 16, 0);
			ModelRenderer eye = new ModelRenderer(this, 8, 0); eye.addBox(-1, -3, -8.25F, 2, 2, 1); body.addChild(eye);
			for (int i = 0; i < spikes.length; i++) {
				spikes[i] = new ModelRenderer(this, 0, 0); spikes[i].addBox(-1, -5, -1, 2, 6, 2);
				spikes[i].rotateAngleZ = (float) (i % 4 * Math.PI / 2); spikes[i].rotationPointZ = (i / 4 - 1) * 6;
				spikes[i].rotationPointX = (float) Math.sin(i % 4 * Math.PI / 2) * 6;
				spikes[i].rotationPointY = -(float) Math.cos(i % 4 * Math.PI / 2) * 6;
				body.addChild(spikes[i]);
			}
			tail = new ModelRenderer(this, 40, 0); tail.addBox(-2, -2, 0, 4, 4, 8); tail.setRotationPoint(0, 0, 8); body.addChild(tail);
			fin = new ModelRenderer(this, 25, 19); fin.addBox(-.5F, -4.5F, 0, 1, 9, 9); fin.setRotationPoint(0, 0, 7); tail.addChild(fin);
		}
		@Override public void render(Entity entity, float swing, float amount, float age, float yaw, float pitch, float scale) {
			body.rotateAngleY = yaw * (float) Math.PI / 180; body.rotateAngleX = pitch * (float) Math.PI / 180;
			tail.rotateAngleY = (float) Math.sin(age * (((EntityGuardian) entity).isSwimming() ? .5 : .15)) * .35F;
			fin.rotateAngleY = tail.rotateAngleY; body.render(scale);
		}
	}
}
