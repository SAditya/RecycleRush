package auto;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import libs.Matrix;

public class StrapdownInertialNavigationSystem {

	BuiltInAccelerometer accel = new BuiltInAccelerometer();
	
	
	
	public Matrix getAccelVectors() {
	
		
		
		Matrix m = new Matrix(1, 2);
		m.set(0, 0, accel.getX() );
		m.set(0, 1, accel.getY());
		
		return m;
		
	}
	
	
	
	public Matrix getFinalVelocityVector(Matrix m){
		
		
		
		
		
	} 
	
	
}
