import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ElevatorSubsystem extends SubsystemBase {

    // Variables
    // Defining the 2 motors
    public final CANSparkMax motor1 = new CANSparkMax(1, MotorType.kBrushless);
    public final CANSparkMax motor2 = new CANSparkMax(2, MotorType.kBrushless);

    // Creating the joystick used to control the elevator
    public final Joystick joystick = new Joystick(0);

    // Initializing
    public ElevatorSubsystem() {
        // Set the motors to idle mode at initialization
        motor1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        motor2.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void controller {
        // If button is pressed, raise the elevator, if the button is released, lower the button, else, stop the motors
        if (joystick.getRawButtonPressed(0)) {
            raise_elevator();
        } else if (joystick.getRawButtonReleased(0)) {
            lower_elevator();
        } else {
            stop_elevator();
        }
    }

    //  Functions to raise and lower motors
    public void raise_elevator() {
        // percent output of the motor, 1 being the max to raise them the most
        motor1.set(1.0);
        motor2.set(1.0);
    }

    public void lower_elevator() {
        // percent output of the motor, -1 being the least to lower them the most
        motor1.set(-1.0);
        motor2.set(-1.0);
    }

    public void stop_elevator() {
        // Stopping the motor
        motor1.set(0.0);
        motor2.set(0.0);
    }
}
