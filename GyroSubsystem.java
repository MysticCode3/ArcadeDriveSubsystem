import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// Motor Variables
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class GyroSubsystem extends SubsystemBase {
    // Variables
    // Defining the motor
    public CANSparkMax motor = new CANSparkMax(1, MotorType.kBrushless);

    public PigeonIMU pigeon;
    // the angle wanted
    public final double angleWanted = 45;


    public GyroSubsystem() {
        pigeon = new PigeonIMU(0);
    }

    @override
    public void periodic() {
        checkIfAtAngle();
    }
    
    public void raiseElevator() {
        // percent output of the motor, 1 being the max to raise them the most
        motor.set(1.0);
    }

    public void checkIfAtAngle() {
        if (pigeon.getAngle() == angleWanted) {
            raiseElevator();
            SmartDashboard.putBoolean("ElevatorRaised", true);
        } else {
            SmartDashboard.putBoolean("ElevatorRaised", false);
        }
    }

}
