import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;


public class ElevatorSubsystem extends SubsystemBase {

    // Variables
    // Defining the 2 motors
    public final CANSparkMax motor1 = new CANSparkMax(1, MotorType.kBrushless);
    public final CANSparkMax motor2 = new CANSparkMax(2, MotorType.kBrushless);

    // Creating the joystick used to control the elevator
    public final Joystick joystick = new Joystick(0);

    // Initializing
    public void robotInit() {
        // Set the motors to idle mode at initialization
        motor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        motor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void teleopPeriodic() {
        //  Runs the controller
        controller();
    }

    public void controller {
        // If button is pressed, raise the elevator, if the button is released, lower the button, else, stop the motors
        if (joystick.getRawButtonPressed(0)) {
            raise();
        } else if (joystick.getRawButtonReleased(0)) {
            lower();
        } else {
            stop();
        }
    }

    //  Functions to raise and lower motors
    public void raise() {
        // percent output of the motor, 1 being the max to raise them the most
        motor1.set(1.0);
        motor2.set(1.0);
    }

    public void lower() {
        // percent output of the motor, -1 being the least to lower them the most
        motor1.set(-1.0);
        motor2.set(-1.0);
    }

    public void stop() {
        // Stopping the motor
        motor1.set(0.0);
        motor2.set(0.0);
    }
}
