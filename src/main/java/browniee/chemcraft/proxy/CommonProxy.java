package browniee.chemcraft.proxy;

import browniee.chemcraft.ModsBlocks;
import browniee.chemcraft.OrganicDecomposer.blockOrganicDecomposer;
import browniee.chemcraft.OrganicDecomposer.tileOrganicDecomposer;
import browniee.chemcraft.TestBlock.blockTestBlock;
import browniee.chemcraft.chemcraft;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.GameRuleChangeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(chemcraft.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new blockTestBlock());
        event.getRegistry().register(new blockOrganicDecomposer());

        GameRegistry.registerTileEntity(tileOrganicDecomposer.class, chemcraft.MODID + "_OrganicDecomposer");
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ItemBlock(ModsBlocks.blockTestBlock).setRegistryName(blockTestBlock.TESTBLOCK));
        event.getRegistry().register(new ItemBlock(ModsBlocks.blockOrganicDecomposer).setRegistryName(blockOrganicDecomposer.RESOURCE_LOCATION));
    }

}
