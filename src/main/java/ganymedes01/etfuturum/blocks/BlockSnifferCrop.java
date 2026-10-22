package ganymedes01.etfuturum.blocks;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import java.util.Random;
public class BlockSnifferCrop extends BlockCrops {
	private final boolean pitcher;
	private final IIcon[] icons;
	public BlockSnifferCrop(boolean pitcher) { this.pitcher = pitcher; icons = new IIcon[pitcher ? 5 : 3]; setBlockName("etfuturum." + (pitcher ? "pitcher_crop" : "torchflower_crop")); }
	@cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
	@Override public void registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister register) { for (int i = 0; i < icons.length; i++) icons[i] = register.registerIcon(!pitcher && i == 2 ? "torchflower" : (pitcher ? "pitcher_crop_bottom_stage_" : "torchflower_crop_stage_") + i); }
	@Override public IIcon getIcon(int side, int meta) { return icons[Math.min(icons.length - 1, (meta & 7) * icons.length / 8)]; }
	@Override protected Item func_149866_i() { return (pitcher ? ganymedes01.etfuturum.ModItems.PITCHER_POD : ganymedes01.etfuturum.ModItems.TORCHFLOWER_SEEDS).get(); }
	@Override protected Item func_149865_P() { return (pitcher ? ganymedes01.etfuturum.ModBlocks.PITCHER_PLANT : ganymedes01.etfuturum.ModBlocks.TORCHFLOWER).getItem(); }
}
