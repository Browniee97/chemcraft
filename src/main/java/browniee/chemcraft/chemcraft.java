package browniee.chemcraft;

import browniee.chemcraft.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import org.apache.logging.log4j.Logger;

@Mod(modid = chemcraft.MODID, name = chemcraft.MODNAME, version = chemcraft.MODVERSION, dependencies = "required-after:forge@[14.23.5.2768,)", useMetadata = true)
public class chemcraft {

    public static final String MODID = "chemcraft";
    public static final String MODNAME = "Chemcraft";
    public static final String MODVERSION= "0.0.1";

    @SidedProxy(clientSide = "browniee.chemcraft.proxy.ClientProxy", serverSide = "browniee.chemcraft.proxy.ServerProxy")
    public static CommonProxy proxy;

    public static CreativeTabs creativeTab = new CreativeTabs("chemcraft") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModsBlocks.blockTestBlock);
        }
    };

    @Mod.Instance
    public static chemcraft instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
