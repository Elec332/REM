package phoenix.rem.blocks.tile.transmitter;

import phoenix.rem.api.power.BaseTileTransmitter;

/**
 * Created by Elec332 on 10-2-2015.
 */
public class TEIronStraight extends BaseTileTransmitter {
    @Override
    public boolean doesExplode() {
        return false;
    }

    @Override
    public int maxSpeed() {
        return 700;
    }

    @Override
    public int maxTorque() {
        return 500;
    }

    @Override
    public int minTorque() {
        return 5;
    }
}
