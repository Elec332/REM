package phoenix.rem.items;

import elec332.repack.core.helper.RegisterHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import phoenix.rem.api.wrench.IWrenchable;
import phoenix.rem.data.ModInfo;
import phoenix.rem.main.CTabs;

/**
 * Created by Elec332 on 5-2-2015.
 */
public class Wrench extends Item {
    public Wrench(String name) {
        setCreativeTab(CTabs.TabMain);
        setUnlocalizedName(ModInfo.MODID + "." + name);
        setTextureName(ModInfo.MODID + ":" + name);
        setContainerItem(this);
        setNoRepair();
        setMaxDamage(72);
        setMaxStackSize(1);
        RegisterHelper.registerItem(this, name);
    }

    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
        return false;
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(this, 1, itemStack.getItemDamage() + 1);
    }

    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float HitX, float HitY, float HitZ) {
        Block block = world.getBlock(x, y, z);
        if (block instanceof IWrenchable) {
            if (player.isSneaking()) {
                world.setBlockToAir(x, y, z);
                if (!world.isRemote) {
                    world.spawnEntityInWorld(new EntityItem(world, x, y, z, ((IWrenchable) block).ItemDropped()));
                }
            } else {
                ((IWrenchable) block).onWrenched(world, x, y, z, ForgeDirection.getOrientation(side));
            }
            player.swingItem();
            itemStack.damageItem(1, player);
            return true;
        } else if (block.rotateBlock(world, x, y, z, ForgeDirection.getOrientation(side))) {
            player.swingItem();
            itemStack.damageItem(1, player);
            return true;
        }
        return false;
    }
}
