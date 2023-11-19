import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Motor Variables
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class GyroSubsystem extends SubsystemBase {
    // Variables
    // Defining the 2 motors
    public final CANSparkMax motor = new CANSparkMax(1, MotorType.kBrushless);

    public final PigeonIMU pigeon;
    public double heading;
    // the angle wanted
    public double angle_wanted = 45;


    public void robotInit() {
        pigeon = new PigeonIMU(0);
    }

    public void raise_elevator() {
        // percent output of the motor, 1 being the max to raise them the most
        motor.set(1.0);
    }

    // Function to get heading
    public void get_heading() {
        heading = pigeon.getAngle();
    }

    public void check_if_at_angle() {
        if (heading == angle_wanted) {
            raise_elevator();
            SmartDashboard.putBoolean("ElevatorRaised", true);
        }
    }

}
