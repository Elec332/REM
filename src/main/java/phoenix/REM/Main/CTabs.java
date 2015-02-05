package phoenix.REM.Main;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

/**
 * Created by Elec332 on 5-2-2015.
 */
public class CTabs {
    public static CreativeTabs TabMain = new CreativeTabs(localise("TabMain")) {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(Blocks.anvil);
        }
    };

    static String localise(String name){
        return REMMod.modID + name;
    }
}
