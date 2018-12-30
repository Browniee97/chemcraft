package browniee.chemcraft;

import browniee.chemcraft.OrganicDecomposer.blockOrganicDecomposer;
import browniee.chemcraft.TestBlock.blockTestBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModsBlocks {

    @GameRegistry.ObjectHolder("chemcraft:TestBlock")
    public static blockTestBlock blockTestBlock;

    @GameRegistry.ObjectHolder("chemcraft:organicdecomposer")
    public static blockOrganicDecomposer blockOrganicDecomposer;

    @SideOnly(Side.CLIENT)
    public static void initModels(){
        blockTestBlock.initModel();
        blockOrganicDecomposer.initModel();
    }

}
