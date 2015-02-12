package phoenix.rem.api.wrench;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Elec332 on 7-2-2015.
 */
public interface IRotatable {
    public Boolean rotateBlock(World world, int x, int y, int z);

    public ForgeDirection getFacing();
}
