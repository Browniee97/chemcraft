package browniee.chemcraft.OrganicDecomposer;

import browniee.chemcraft.chemcraft;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import static net.minecraft.block.BlockDispenser.TRIGGERED;

public class blockOrganicDecomposer extends Block implements ITileEntityProvider {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");


    public static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation(chemcraft.MODID ,"OrganicDecomposer");

    public blockOrganicDecomposer() {

        super(Material.IRON);
        setRegistryName(RESOURCE_LOCATION);

        setTranslationKey(chemcraft.MODID + ".OrganicDecomposer");
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(chemcraft.creativeTab);

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
         if(worldIn.isRemote) {
             return true;
         }
         TileEntity te = worldIn.getTileEntity(pos);
         if(!(te instanceof tileOrganicDecomposer)){
             return false;
         }
        playerIn.openGui(chemcraft.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
         return true;

    }

    @SideOnly(Side.CLIENT)
    public void initModel(){
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new tileOrganicDecomposer();
    }
}
