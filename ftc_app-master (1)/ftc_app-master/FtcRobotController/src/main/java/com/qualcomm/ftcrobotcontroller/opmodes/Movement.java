package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by phama21 on 9/13/2015.
 */

/*
    Movement class which extends to the MovementAuto and MovementTeleOp class.
    Provides methods for motor commands.
 */
public class Movement {

    private DcMotor leftMotor;          //Declares DcMotor object
    private DcMotor rightMotor;         //Declares DcMotor object

    public Movement(DcMotor left, DcMotor right) {
        right.setDirection(DcMotor.Direction.REVERSE);

        leftMotor = left;
        rightMotor = right;
    }

    /*
        Stops the motors
        @param  leftMotor   the left side motors
        @param  rightMotor  the right side motors
     */
    public void stopDrive(){
        leftMotor.setPower(0.0);
        rightMotor.setPower(0.0);
    }

    /*
        Sets the motors to move forward
        @param  leftMotor   the left side motors
        @param  rightMotor  the right side motors
        @param  power       the value which the motor's power is set to
     */
    public void forwardDrive( double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    /*
        Sets the motor to reverse by making the power negative and calling the forwardDrive method
        @param leftMotor    the left side motors
        @param rightMotor   the right side motors
        @param power        the value which sets the power after it is made negative
     */
    public void reverseDrive(double power) {
        forwardDrive(-power);
    }

    /*
        Sets the motor to turn left by making the left motor power negative and the right motor power positive
        @param leftMotor    the left side motors
        @param rightMotor   the right side motors
        @param power        the value which sets the power of the motors
                            negative for the leftMotor and positive for the rightMotor
     */
    public void leftSpin(double power) {
        leftMotor.setPower(-power);
        rightMotor.setPower(power);
    }

    /*
        Sets the motor to turn right by making the right motor power negative and the right motor power positive
        @param leftMotor    the left side motors
        @param rightMotor   the right side motors
        @param power        the value which sets the power of the motors
                            negative for the rightMotor and positive for the leftMotor
     */
    public void rightSpin(double power) {
        leftMotor.setPower(power);
        rightMotor.setPower(-power);
    }
}
