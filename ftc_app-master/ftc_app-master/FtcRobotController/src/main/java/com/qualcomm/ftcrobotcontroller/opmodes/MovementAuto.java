package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by phama21 on 9/12/2015.
 */

/*
    MovementAuto class that creates common methods used for movement in autonoumous files
 */
public class MovementAuto extends Movement {

    private DcMotor leftMotor;              //Declares DcMotor object
    private DcMotor rightMotor;             //Declares DcMotor object

    final static int ENCODER_CPR = 1440;    //Constant of how many counts/rotation the encoder counts
    private double gearRatio;               //Declares double variable which holds the gear ratio
    private int wheelDiameter;              //Declares int variable which holds the wheel's diameter

    /*
        Constructor method that initializes most of the variables and objects
        @param  left            the left side motors
        @param  right           the right side motors
        @param  WheelDiameter   the diameter of the wheel
        @param  GearRatio       the gear ratio
     */
    public MovementAuto(DcMotor left, DcMotor right, int WheelDiameter, double GearRatio) {
        leftMotor = left;
        rightMotor = right;
        wheelDiameter = WheelDiameter;
        gearRatio = GearRatio;

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

    }

    final private double POWER = 0.5;       //The power that the motors are set to for each command

    /*
        forwardDriveAuto method that calculates the distance from inches to encoder counts and then
        sets the motors to drive to the target location
        @param distance     distance to travel in inches
     */
    public void forwardDriveAuto(double distance) {

        double counts = ENCODER_CPR * (distance / (Math.PI * wheelDiameter)) * gearRatio;

        leftMotor.setTargetPosition((int) counts);
        rightMotor.setTargetPosition((int) counts);

        if (leftMotor.getCurrentPosition() != leftMotor.getTargetPosition()) {  //Resets the encoders after the position has been reached

            leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

            forwardDrive(leftMotor, rightMotor, POWER);

        } else {
            leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    /*
        reverseDriveAuto sets the motors to drive in reverse to the target location
        @param distance     distance to travel in inches
     */
    public void reverseDriveAuto(double distance) {
        double counts = ENCODER_CPR * (distance / (Math.PI * wheelDiameter)) * gearRatio;

        leftMotor.setTargetPosition((int) counts);
        rightMotor.setTargetPosition((int) counts);

        if (leftMotor.getCurrentPosition() != leftMotor.getTargetPosition()) {

            leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

            reverseDrive(leftMotor, rightMotor, POWER);

        } else {
            leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    /*
        forwardDriveAuto method that accepts a set time and power to drive a certain distance
        @param  seconds     the int value which the motors run
        @param  power       the double value which that motor power is set to
     */
    public void forwardDriveAuto(int seconds, double power) throws InterruptedException {
        forwardDrive(leftMotor, rightMotor, power);

        wait(seconds * 1000);   //Converts to milliseconds

        stopDrive(leftMotor, rightMotor);

    }

    /*
        reverseDriveAuto method accepts a set time and power
        reverses the power and calls the forwardDriveAuto method
        @param seconds      the int value which the motors run
        @para   power       the double value which the motors power is set to
     */
    public void reverseDriveAuto(int seconds, double power) throws InterruptedException {
        forwardDriveAuto(seconds, -power);
    }

    /*
        leftTurnAuto method accepts a distance and sets the motors to run to that target
        @param  distance    value in inches for how far the motors will turn
     */
    public void leftTurnAuto(double distance) {
        double counts = ENCODER_CPR * (distance / (Math.PI * wheelDiameter)) * gearRatio;

        leftMotor.setTargetPosition((int) counts);
        rightMotor.setTargetPosition((int) counts);

        if (leftMotor.getCurrentPosition() != leftMotor.getTargetPosition()) {

            leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

            leftSpin(leftMotor, rightMotor, POWER);

        } else {
            leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    /*
        leftTurnAuto method accepts a time and power for the motors to turn at
        @param seconds  value in seconds for how long the motors will turn for
        @param power    a double value for how much power the motors are set to
     */
    public void leftTurnAuto(int seconds, double power) throws InterruptedException {
        leftSpin(leftMotor, rightMotor, power);

        wait(seconds * 1000);

        stopDrive(leftMotor, rightMotor);
    }

    /*
         rightTurnAuto method accepts a distance and sets the motors to run to that target
         @param  distance    value in inches for how far the motors will turn
      */
    public void rightTurnAuto(double distance) {
        double counts = ENCODER_CPR * (distance / (Math.PI * wheelDiameter)) * gearRatio;

        leftMotor.setTargetPosition((int) counts);
        rightMotor.setTargetPosition((int) counts);

        if (leftMotor.getCurrentPosition() != leftMotor.getTargetPosition()) {

            leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
            rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

            rightSpin(leftMotor, rightMotor, 0.5);

        } else {
            leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
            rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        }
    }

    /*
     rightTurnAuto method accepts a time and power for the motors to turn at
     @param seconds  value in seconds for how long the motors will turn for
     @param power    a double value for how much power the motors are set to
  */
    public void rightTurnAuto(int seconds, double power) throws InterruptedException {
        rightSpin(leftMotor, rightMotor, power);

        wait(seconds * 1000);

        stopDrive(leftMotor, rightMotor);
    }


}
