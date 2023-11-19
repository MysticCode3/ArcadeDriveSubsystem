import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// Timed Robot
import edu.wpi.first.wpilibj.TimedRobot;

// Imports for the autonomous command
import edu.wpi.first.wpilibj.Timer;

// Robot
public class ArcadeDriveSubsystem extends TimedRobot {

    // Defining all 4 motors(both left and both right), CANSparkMax(device id, motor type)
    public CANSparkMax frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
    public CANSparkMax rearLeftMotor = new CANSparkMax(2, MotorType.kBrushless);
    public CANSparkMax frontRightMotor = new CANSparkMax(3, MotorType.kBrushless);
    public CANSparkMax rearRightMotor = new CANSparkMax(4, MotorType.kBrushless);

    // Creating the drive system used for arcade drive
    public DifferentialDrive drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    // Creating the joystick used to control the robot
    public Joystick joystick = new Joystick(0);

    // What happens first with the Robot is initialized
    @Override
    public void robotInit() {
        // Inverting the motors to make sure all of them are moving in the same direction
        frontLeftMotor.setInverted(false);
        rearLeftMotor.setInverted(false);
        frontRightMotor.setInverted(false);
        rearRightMotor.setInverted(false);

        timer = new Timer();

        private final double driveSpeed = 0.5;
        private final double driveTime = 10.0;
    }

    @Override
    public void autonomousInit() {
        // resets the timer to make sure it is at 0 and then starts the timer
        timer.reset();
        timer.start();

        // While the timer is below the drive time the robot moves at the drive speed
        if (timer.get() < driveTime) {
            // the robot moves at the drive speed
            drive.arcadeDrive(driveSpeed, 0.0);
        } else {
            // Once the timer is greater than the drive time the robot turns 90 degrees and then stops
            drive.arcadeDrive(0.0, 0.5);
            drive.stopMotor();
            timer.stop()
        }

    }

    @Override
    public void teleopPeriodic() {
        try {
            // Gets the x and y value from the joystick to control the arcadeDrive
            drive.arcadeDrive(-joystick.getY(), -joystick.getX()); 
        } catch (Error e) {
            System.out.println(e);
        }
    }
}
