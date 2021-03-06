package phoenix.rem.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elec332.repack.core.helper.RegisterHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import phoenix.rem.api.wrench.IRotatable;
import phoenix.rem.data.ModInfo;
import phoenix.rem.main.CTabs;

import java.util.Random;

/**
 * Created by Elec332 on 8-2-2015.
 */
//This code is 99,9% copied from ElecCore, you can the code here:
// https://github.com/Elecs-Mods/ElecCore/blob/05324a5ccd78443535af672886f8093778d24793/src/main/java/elec332/core/util/blocks/baseblock.java
public class BlockBase extends Block {
	

    static String modID = ModInfo.MODID;
    int amountDropped;
    Item dropped;
    boolean ghost;
    String name;
    Boolean opaqueCube;
	
    public BlockBase(Material baseMaterial, String blockName) {
        super(baseMaterial);
        setBlockName(modID + "." + blockName);
        setCreativeTab(CTabs.TabMain);
        this.name = blockName;
        RegisterHelper.registerBlock(this, blockName);
    }

    public BlockBase(Material baseMaterial, String blockName,  CreativeTabs CreativeTab) {
        this(baseMaterial, blockName);
        setCreativeTab(CreativeTab);
    }

    public BlockBase(Material baseMaterial, String blockName, CreativeTabs CreativeTab,  int setQuantityDropped) {
        this(baseMaterial, blockName, CreativeTab);
        this.amountDropped = setQuantityDropped;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z){
        if (ghost)
            return null;
        return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ, (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }

    @Override
    public Item getItemDropped(int par1, Random rand, int par2){
        return dropped != null ? dropped : super.getItemDropped(par1, rand, par2);
    }

    @Override
    @SideOnly(Side.CLIENT)
    protected String getTextureName(){
        if (this.textureName != null)
            return textureName;
        return modID + ":" + name;
    }

    @Override
    public boolean isOpaqueCube(){
        return opaqueCube != null ? opaqueCube : true;
    }

    @Override
    public int quantityDropped(Random random){
        return amountDropped;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        blockIcon = iconRegister.registerIcon(this.getTextureName());
    }

    @Override
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection direction) {
        TileEntity tile = world.getTileEntity(x, y, z);
        return tile instanceof IRotatable ?((IRotatable)tile).rotateBlock(world, x, y, z):false;
    }

    public BlockBase setGhost(){
        this.ghost = true;
        this.setNoOpaqueCube();
        return this;
    }

    public BlockBase setItemDropped(Item itemDropped){
        this.dropped = itemDropped;
        return this;
    }

    public BlockBase setNoOpaqueCube(){
        this.opaqueCube = false;
        return this;
    }

    public BlockBase setQuantityDropped(int setQuantityDropped){
        this.amountDropped = setQuantityDropped;
        return this;
    }

    //I know vanilla has this, but that's a void, this isn't ;)
    public BlockBase setToolLevel(String toolClass, int level){
        this.setHarvestLevel(toolClass, level);
        return this;
    }
}
