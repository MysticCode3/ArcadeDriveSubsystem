import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// Imports for the autonomous command
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

// Timed Robot
import edu.wpi.first.wpilibj.TimedRobot;

// Variables
// Defining all 4 motors(both left and both right), CANSparkMax(device id, motor type)
public final CANSparkMax frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
public final CANSparkMax rearLeftMotor = new CANSparkMax(2, MotorType.kBrushless);
public final CANSparkMax frontRightMotor = new CANSparkMax(3, MotorType.kBrushless);
public final CANSparkMax rearRightMotor = new CANSparkMax(4, MotorType.kBrushless);

// Creating the drive system used for arcade drive
public final DifferentialDrive drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

// Creating the joystick used to control the robot
public final Joystick joystick = new Joystick(0);

// Drive System
public class ArcadeDriveSubsystem {

    // Initializing
    public void initialize() {
        // Inverting the motors to make sure all of them are moving in the same direction
        frontLeftMotor.setInverted(false);
        rearLeftMotor.setInverted(false);
        frontRightMotor.setInverted(false);
        rearRightMotor.setInverted(false);
    }

    // Runs when the driver controls the robot
    public void teleopPeriodic() {
        // Gets the x and y value from the joystick to control the arcadeDrive
        drive.arcadeDrive(-joystick.getY(), -joystick.getX());
    }
}

// Autonomous Command
public class AutonomousCommand extends CommandBase {
    private final Timer timer;

    // Adjust these values based on your robot's characteristics
    private static final double drive_speed = 0.5;
    private static final double turn_speed = 0.5;
    private static final double drive_time = 10.0;
    private static final double turn_angle = 90.0;

    public AutonomousCommand() {
        this.drive = drive;
        // Creates the timer
        this.timer = new Timer();
        // Allows the autonomousCommand function to have access to the drive system
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        // resets the timer to make sure it is at 0 and then starts the timer
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        // While the timer is below the drive time the robot moves at the drive speed
        if (timer.get() < drive_time) {
            // the robot moves at the drive speed and turns 90 degrees gradually throughout the drive_time
            drive.arcadeDrive(drive_speed, turn_angle/drive_time);
        } else {
            // Once the timer is greater than the drive time the robot stops
            drive.stopMotor();
        }
    }
}

// Robot
public class Robot extends TimedRobot {
    private ArcadeDriveSubsystem driveSubsystem;
    private AutonomousCommand autonomousCommand;

    // What happens first with the Robot is initialized
    @Override
    public void robotInit() {
        driveSubsystem = new ArcadeDriveSubsystem();
        autonomousCommand = new AutonomousCommand();
        driveSubsystem.initialize()
        autonomousCommand.initialize()
    }

    @Override
    public void autonomousInit() {
        autonomousCommand.execute();
    }

    @Override
    public void teleopPeriodic() {
        // Allows the driver to controll the robot
        driveSubsystem.teleopPeriodic();
    }
}
