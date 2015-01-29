package auto;

import edu.wpi.first.wpilibj.Timer;
import core.Drive;
import core.Elevator;
import core.Claw;
import core.Dashboard;
import util.Config;

public class Auto
{
	private Claw claw;
	private Elevator elevator;
	private Drive drive;
	private Dashboard dash;
	private Timer timer;
	private int autoId;
	private int autoStep = 0;
	
	public Auto(Claw newClaw, Elevator newElevator, Drive newDrive)
	{
		claw = newClaw;
		elevator = newElevator;
		drive = newDrive;
		timer = new Timer();
	}
	
	public void getAutoMode()
	{
		autoId = dash.getAutoType();
	}
	
	public void run()
	{
		switch(autoId)
		{
			case Config.Auto.idDoNothing:
			{
				doNothing();
				break;
			}
			
			case Config.Auto.idDriveForwardEnc:
			{
				driveForwardEnc();
				break;
			}
			
			case Config.Auto.idDriveForwardTimer:
			{
				driveForwardTimer();
				break;
			}

			case Config.Auto.idGetAllTotesEnc:
			{
				getAllTotesEnc();
				break;
			}
			
			case Config.Auto.idGetAllTotesTimer:
			{
				getAllTotesTimer();
				break;
			}
			
			case Config.Auto.idGetOneToteEnc:
			{
				getOneToteEnc();
				break;
			}
			
			case Config.Auto.idGetOneToteTimer:
			{
				getOneToteTimer();
				break;
			}
		}
	}
	
	private void doNothing()
	{
		
	}
	
	private void driveForwardEnc()
	{
			drive.setSpeed(Config.Auto.encDriveForwardDistance, Config.Auto.encDriveForwardDistance, 0, 0);
	}
	
	private void driveForwardTimer()
	{
		
	}
	
	private void getAllTotesEnc()
	{
//		pickUpTote(1);
//		if(isFinishedPickUpOne)
//		{
//			strafe(1);
//			if(isFinishedStrafeOne)
//			{
//				pickUpTote(2);
//				if(isFinishedPickUpTwo)
//				{
//					strafe(2);
//					if(isFinishedStrafeTwo)
//					{
//						pickUpTote(3);
//						if(isFinishedPickUpThree)
//							drive.setSpeed(Config.Auto.encDriveForwardDistance, Config.Auto.encDriveForwardDistance, 0, 0);
//					}
//				}
//			}
//		}
		
		switch
		
	}
	
	private void getAllTotesTimer()
	{
		
	}
	
	private void getOneToteEnc()
	{
//		pickUpTote(1);
//			if(isFinishedPickUpOne)
//				drive.setSpeed(Config.Auto.encDriveForwardDistance, Config.Auto.encDriveForwardDistance, 0, 0);
	}
	
	private void getOneToteTimer()
	{
		
	}
	
	public void pickUpTote(int num)
	{
		drive.setSpeed(Config.Auto.encDistanceForwardToTote, Config.Auto.encDistanceForwardToTote, 0, 0);
		if(drive.getLeftEncDist() == Config.Auto.encDistanceForwardToTote && drive.getRightEncDist() == Config.Auto.encDistanceForwardToTote)
		{
			claw.openClaw();
			claw.closeClaw();
			drive.setSpeed(0, 0, 0, 0);
			if(drive.getLeftEncDist() == 0 && drive.getRightEncDist() == 0)
				if(num == 1)
					autoStep++;
				else if(num == 2)
					autoStep++;
				else 
					autoStep++;
		}
		
	}
	
	public void strafe(int num)
	{
		drive.setSpeed(0, 0, Config.Auto.encDistanceBetweenTotes, Config.Auto.encDistanceBetweenTotes);
		if(drive.getFrontEncDist() == Config.Auto.encDistanceBetweenTotes && drive.getBackEncDist() == Config.Auto.encDistanceBetweenTotes)
			if(num == 1)
			{
				isFinishedStrafeOne = true;
				drive.encReset();
			}
			else
			{
				isFinishedStrafeTwo = true;
				drive.encReset();
			}
	}
	
	
}