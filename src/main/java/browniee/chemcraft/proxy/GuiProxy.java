package browniee.chemcraft.proxy;

import browniee.chemcraft.OrganicDecomposer.containerOrganicDecomposer;
import browniee.chemcraft.OrganicDecomposer.guiOrganicDecomposer;
import browniee.chemcraft.OrganicDecomposer.tileOrganicDecomposer;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiProxy implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x,y,z);
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof tileOrganicDecomposer) {
            return new containerOrganicDecomposer(entityPlayer.inventory, (tileOrganicDecomposer) te);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x,y,z);
        TileEntity te = world.getTileEntity(pos);
        if(te instanceof tileOrganicDecomposer){
            tileOrganicDecomposer containerTileEntity = (tileOrganicDecomposer) te;
            return new guiOrganicDecomposer(containerTileEntity, new containerOrganicDecomposer(entityPlayer.inventory, containerTileEntity));
        }
        return null;
    }
}
