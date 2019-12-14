  /*----------------------------------------------------------------------------*/
  /* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
  /* Open Source Software - may be modified and shared by FRC teams. The code   */
  /* must be accompanied by the FIRST BSD license file in the root directory of */
  /* the project.                                                               */
  /*----------------------------------------------------------------------------*/

  package frc.robot;

  import edu.wpi.first.wpilibj.DoubleSolenoid;
  import edu.wpi.first.wpilibj.IterativeRobot;
  import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
  import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
  
  import edu.wpi.first.wpilibj.Talon;
  import edu.wpi.first.wpilibj.Joystick;
  import edu.wpi.first.wpilibj.buttons.*;
  import edu.wpi.first.wpilibj.command.Command;
  import edu.wpi.first.wpilibj.Solenoid;
  import edu.wpi.first.wpilibj.Timer;
  import edu.wpi.first.wpilibj.DoubleSolenoid;
  import edu.wpi.first.wpilibj.SpeedControllerGroup;
  import edu.wpi.first.wpilibj.drive.DifferentialDrive;
  /**
   * The VM is configured to automatically run this class, and to call the
   * functions corresponding to each mode, as described in the IterativeRobot
   * documentation. If you change the name oftt this class or the package after
   * creating this project, you must also update the build.gradle file in the
   * project.
   */
  public class Robot extends IterativeRobot {
    public static OI oi;
    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";
    private static String m_autoSelected;
    private static final SendableChooser<String> m_chooser = new SendableChooser<>();
    //public static Talon frontLeft,frontRight,backLeft,backRight;
    public static DoubleSolenoid heightSolenoid, shooterSolenoid, gearboxSolenoid;
    public static Timer timer;
  
    public static DifferentialDrive drive;
    public static boolean toggleOn = false;
    public static boolean togglePressed = false;
  



    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
      RobotMap.init();
      m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
      m_chooser.addOption("My Auto", kCustomAuto);
      SmartDashboard.putData("Auto choices", m_chooser);
      oi = new OI();
      //frontLeft = new Talon(4);
      //frontRight = new Talon(1);
      //backLeft = new Talon(3);
      //backRight = new Talon(2);
      shooterSolenoid = new DoubleSolenoid(5,6); //Controls shooter height
      heightSolenoid = new DoubleSolenoid(2,3);   //The pistons that control the pressure in the motor
      gearboxSolenoid = new DoubleSolenoid(0,7);   //The pistons that control the shooting
      
      

      


      
  
      //shooterSolenoid.set(true,true);
      //shooterSolenoid.set(Value.kReverse);
      //shooterSolenoid.set(Value.kForward);
      
    //  SpeedControllerGroup leftSide = new SpeedControllerGroup(RobotMap.leftFrontDriveMotor, RobotMap.leftRearDriveMotor);
    //  SpeedControllerGroup rightSide = new SpeedControllerGroup(RobotMap.rightFrontDriveMotor, RobotMap.rightRearDriveMotor);
    // drive = new DifferentialDrive(leftSide, rightSide);


      
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want to run during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
   //   if(c_current >= 120) {
   //     compressor.setClosedLoopControl(false);
   //   }else if(c_current < 120) {
   //     compressor.setClosedLoopControl(true);
   //   }
    // boolean get = Robot.oi.ps4_Controller.getRawButtonPressed(4);
    // System.out.println(get);
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional comparisons to
     * the switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
      m_autoSelected = m_chooser.getSelected();
      // drive.tankDrive(0.5, -0.5);
      
      // Timer.delay(5);

      // drive.tankDrive(0,0);
    

      //System.out.println("Auto selected: " + m_autoSelected);
    }


    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
      switch (m_autoSelected) {
        case kCustomAuto:
          // Put custom auto code here
          break;
        case kDefaultAuto:
        default:
          // Put default auto code here
          break;
      }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
      

      boolean kForward = Robot.oi.ps4_Controller.getRawButtonPressed(3);
      boolean kReverse = Robot.oi.ps4_Controller.getRawButtonPressed(2);
      boolean firing = Robot.oi.ps4_Controller.getRawButtonPressed(1);

      boolean hasFired = false;
      // System.out.println(kForward);
      // System.out.println(kReverse);
     
      if (kForward){
        heightSolenoid.set(DoubleSolenoid.Value.kForward);
        System.out.println("Circle pressed");
      }
      if (kReverse){
        heightSolenoid.set(DoubleSolenoid.Value.kReverse);
      System.out.println("X pressed");
      }

      if(firing){
        if(hasFired == false){
          shooterSolenoid.set(DoubleSolenoid.Value.kForward);
          hasFired = true;
          System.out.println(hasFired);
        }else if(hasFired == true){
          shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
          hasFired = false;
          System.out.println(hasFired);
        }
        System.out.println("Square pressed");
      }
    }
    /** public void updateToggle(){
      if(joystick.getRawButtonPressed(1)){
        if(!togglePressed){
          toggleOn = !toggleOn;
          togglePressed = true;
          System.out.println("Yeet");
        }
      }  
      
        else{
          togglePressed = false;
        }
    }


    */
    @Override
    public void testPeriodic() {
    }
  }
