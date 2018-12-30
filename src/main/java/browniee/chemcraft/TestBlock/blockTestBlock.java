package browniee.chemcraft.TestBlock;

import browniee.chemcraft.chemcraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class blockTestBlock extends Block {

    public static final ResourceLocation TESTBLOCK = new ResourceLocation(chemcraft.MODID ,"TestBlock");

    public blockTestBlock() {

        super(Material.IRON);
        setRegistryName(TESTBLOCK);

        setTranslationKey(chemcraft.MODID + ".TestBlock");
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(chemcraft.creativeTab);
    }
        @SideOnly(Side.CLIENT)
        public void initModel(){
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        }

}
