package phoenix.rem.api.power;

import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraftforge.common.util.ForgeDirection;
import phoenix.rem.api.wrench.BaseTileRotatable;
import phoenix.rem.helper.DirectionHelper;

/**
 * Created by Elec332 on 9-2-2015.
 */
public abstract class BaseTileReceiver extends BaseTileRotatable implements IPowerReceiver{

	public boolean isBroken = false;
	public int speed = 0;
	public int torque = 0;
	
	@Override
	public boolean canReceivePowerFromSide(ForgeDirection direction){
		return DirectionHelper.getOppositeSide(getFacing()) == direction;
	}

	//IPowerReciever

	
	
	public boolean canWork(){
		if (!isBroken){
			if (speed >= maxSpeed() || torque >= maxTorque()){
				if (doesExplode()){
					EntityTNTPrimed boom = new EntityTNTPrimed(worldObj, xCoord, yCoord, zCoord, null);
					boom.fuse = 0;
					worldObj.spawnEntityInWorld(boom);
				}else
					this.isBroken = true; // TODO: make wrench fix machine when
											// broken with fancy gui 'n stuff
			}else if (speed >= 0 && torque >= minTorque()){
				this.speed = 0;
				this.torque = 0;
				return true;
			}
		}
		return false;
	}

	@Override
	public int getSpeed(){
		return speed;
	}

	@Override
	public int getTorque(){
		return torque;
	}

	@Override
	public void recievePower(int torque, int speed){
		this.torque += torque;
		this.speed += speed;
	}

}
