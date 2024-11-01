// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  private final Spark fl = new Spark(0);
  private final Spark fr = new Spark(1);
  private final Spark br = new Spark(2);
  private final Spark bl = new Spark(3);
  private double ly;
  private double rx;
  private double ry;

  public DriveTrain() {
    fl.setInverted(false);
    fr.setInverted(true);
    bl.setInverted(false);
    br.setInverted(true);

  }

  @Override
  public void periodic() {
  }

  public void drive(double ly, double rx, double ry) {
    

    bl.set(ly);
    fl.set(ly);
    br.set(ry);
    fr.set(ry);
    
    System.out.println(ry);
    System.out.println(ly);

  }

}
