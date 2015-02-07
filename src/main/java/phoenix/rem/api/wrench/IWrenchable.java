package phoenix.rem.api.wrench;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Elec332 on 5-2-2015.
 */
public interface IWrenchable{

    public ItemStack ItemDropped();

    public void onWrenched(World world, int x, int y, int z, ForgeDirection direction);
}
