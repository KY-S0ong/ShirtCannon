// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class DriveCmd extends Command {
  private final DriveTrain driveTrain;
 
  private Supplier<Double> inLy, inRx, inRy;
  private Supplier<Boolean> inMode;

  double ly;
  double ry;
  double rx;
  boolean mode;
  private XboxController xc;
  private SlewRateLimiter slew = new SlewRateLimiter(8.5);

  public DriveCmd(
    DriveTrain driveTrain, XboxController xc) {
    this.driveTrain = driveTrain;
    this.xc = xc;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
    
    ly = (Math.abs(ly) > 0.17) ? ly : 0.0;
    ry = (Math.abs(ry) > 0.17) ? ry : 0.0;
    rx = (Math.abs(rx) > 0.17) ? rx : 0.0;
    
    double l = xc.getRawAxis(2);
    driveTrain.drive(slew.calculate(xc.getLeftY()), slew.calculate(xc.getRightX()), slew.calculate(xc.getRightY()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
