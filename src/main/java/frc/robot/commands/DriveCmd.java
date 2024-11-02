// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class DriveCmd extends Command {
  private final DriveTrain driveTrain;
 
  private DoubleSupplier inLy, inRx, inRy;
  private BooleanSupplier inMode;

  double ly;
  double ry;
  double rx;
  boolean mode;
  private XboxController xc;
  private SlewRateLimiter leftYslew = new SlewRateLimiter(5.5);
  private SlewRateLimiter rightXslew = new SlewRateLimiter(0.5);
  private SlewRateLimiter rightYslew = new SlewRateLimiter(0.5);


  public DriveCmd(
    DriveTrain driveTrain, DoubleSupplier inLy, DoubleSupplier inRx, DoubleSupplier inRy, BooleanSupplier inMode) {
    this.driveTrain = driveTrain;
    this.inLy = inLy;
    this.inRx = inRx;
    this.inRy = inRy;
    this.inMode = inMode;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
    ly = inLy.getAsDouble();
    rx = inRx.getAsDouble();
    ry = inRy.getAsDouble();
    
    ly = (Math.abs(ly) > 0.17) ? leftYslew.calculate(ly)  : 0.0;
    ry = (Math.abs(ry) > 0.17) ? rightYslew.calculate(ry) : 0.0;
    rx = (Math.abs(rx) > 0.17) ? rightXslew.calculate(rx) : 0.0;
    
    
    driveTrain.drive(ly, rx, ry);
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
