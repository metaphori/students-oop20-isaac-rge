package ryleh.model.components;

import java.util.Random;

import com.sun.javafx.geom.Vec2d;

import ryleh.model.World;

public class DrunkComponent extends Component {
	
	public DrunkComponent(World world) {
		super(world);
	}


	//private double angleAdjustRate = FXGLMath.random(0, 0.15);
	private Random random = new Random();
	//private double angleAdjustRate = null; // random.doubles(0, 0.15);
	

    //private Vec2d velocity = new Vec2d();
 //   private double directionAngle = FXGLMath.toDegrees(FXGLMath.random(-1, 1) * FXGLMath.PI2);

  //  private int moveSpeed;
   // private int rotationSpeed = FXGLMath.random(-100, 100);

   // private float tx = FXGLMath.random(1000, 10000);

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		super.onUpdate();
	}
	

}
