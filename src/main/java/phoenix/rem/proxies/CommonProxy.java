package phoenix.rem.proxies;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import phoenix.rem.blocks.test.TestBlockTE;
import phoenix.rem.blocks.tile.engine.TERedstoneEngine;
import phoenix.rem.blocks.tile.transmitter.TEIronStraight;
import phoenix.rem.blocks.tile.transmitter.TEWoodCorner;
import phoenix.rem.blocks.tile.transmitter.TEWoodStraight;
import phoenix.rem.handler.EventHandlerCommon;

/**
 * Created by Elec332 on 6-2-2015.
 */
public class CommonProxy {
    public void registerHandlers(){
        FMLCommonHandler.instance().bus().register(new EventHandlerCommon());
        MinecraftForge.EVENT_BUS.register(new EventHandlerCommon());
    }

    public void registerRenderer(){}

    public void registerTileEntities(){
        GameRegistry.registerTileEntity(TestBlockTE.class, "Test");
        GameRegistry.registerTileEntity(TERedstoneEngine.class, "EngineRedstone");
        GameRegistry.registerTileEntity(TEWoodStraight.class, "WoodSTransmitter");
        GameRegistry.registerTileEntity(TEIronStraight.class, "IronSTransmitter");
        GameRegistry.registerTileEntity(TEWoodCorner.class, "WoodCTransmitter");
    }
}
