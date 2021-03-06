package core;

import util.Config;
import util.LIDAR;
import util.Controller;
import util.LimitSwitch;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C.Port;


public class Elevator 
{
	private CANTalon mtElevator = new CANTalon(Config.Elevator.chnMtElevator);
	private LimitSwitch limitBottom = new LimitSwitch(Config.Elevator.chnLimitSwitchBottom, false);
	private LimitSwitch limitTop = new LimitSwitch(Config.Elevator.chnLimitSwitchTop, false);
	private Encoder enc = new Encoder(Config.Elevator.chnEncOne, Config.Elevator.chnEncTwo);
	private Controller contr;
	private boolean mode = false;
	private double baseValue = 0;
	private int level= 0;
	private LIDAR lidar = new LIDAR(Port.kMXP);
	//Button number array
	private int [] levels = 
	{
			Config.Elevator.btLvlOne,
			Config.Elevator.btLvlTwo,
			Config.Elevator.btLvlThree,
			Config.Elevator.btLvlFour,
			Config.Elevator.btLvlFive,
			Config.Elevator.btLvlSix
	};

	/**
	 * Constructor
	 * @param newContr controller
	 */
	public Elevator(Controller newContr)
	{
		contr = newContr;
		enc.reset();
		mtElevator.changeControlMode(CANTalon.ControlMode.Position);
		enc.setDistancePerPulse(360.0/265);
		baseValue = 0;
	}
	
	/**
	 * Runs the elevatorMove function while adjusting parameters
	 */
	public void run()
	{
		// If the switch mode button is pressed set the lowest position on the elevator to six inches
		if(contr.getButton(Config.Elevator.btModeSwitch))
			mode = !mode;
		
		if(mode)
			baseValue = Config.Elevator.adjustedBaseHeight;
			
		// Checks all elevator buttons
		for(int i = 0; i < levels.length; i++)
		{
			if(contr.getButton(levels[i]))
			{
				level = levels[i];
				elevatorMove();
			}
		}
	}
	
	/**
	 * Moves the elevator to the specific indexing location
	 * @param level
	 */
	public void elevatorMove()
	{
		if(!limitBottom.get() && !limitTop.get())
			mtElevator.set(baseValue + level * Config.Elevator.toteHeight);
		else
			mtElevator.set(mtElevator.get());
	}
	
	/**
	 * Gets the current level of the Elevator
	 * @return Elevator level
	 */
	public int getLevel()
	{
		return level; 
	}
	
	/**
	 * Gets the distance form the current Elevator position to the base
	 * @return distance from the current position to the base
	 */
	public double getDistanceFromBase()
	{
		return lidar.getDistance() * 2.54;
	}
	
	/**
	 * Sets the elevator level to the specified level
	 * @param wantedLevel the level you want to set the elevator to
	 */
	public void setLevel(int wantedLevel)
	{
		mtElevator.set(baseValue + wantedLevel * Config.Elevator.toteHeight );
	}
}
