import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


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

    @override
    public void periodic {
        // If button is pressed, raise the elevator, if the button is released, lower the button, else, stop the motors
        if (joystick.getRawButtonPressed(0)) {
            raiseRobot();
        } else if (joystick.getRawButtonReleased(0)) {
            lowerRobot();
        } else {
            stopElevator();
        }
    }

    //  Functions to raise and lower motors
    public void raiseRobot() {
        // percent output of the motor, 1 being the max to raise them the most
        motor1.set(1.0);
        motor2.set(1.0);
    }

    public void lowerRobot() {
        // percent output of the motor, -1 being the least to lower them the most
        motor1.set(-1.0);
        motor2.set(-1.0);
    }

    public void stopElevator() {
        // Stopping the motor
        motor1.set(0.0);
        motor2.set(0.0);
    }
}
