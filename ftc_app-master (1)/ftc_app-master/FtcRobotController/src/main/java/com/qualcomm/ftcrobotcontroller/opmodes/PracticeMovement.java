package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by phama21 on 9/13/2015.
 */
public class PracticeMovement extends OpMode {

    public Movement Chassis;


    //Sets up the robot by finding the motor controller equipment
    @Override
    public void init() {
        Chassis = new Movement(hardwareMap.dcMotor.get("left"), hardwareMap.dcMotor.get("right"));

    }

    //Simple movement that utilizes the buttons for precise movement and joysticks for general movement
    @Override
    public void loop() {

        if (gamepad1.left_stick_y > 0) {
            Chassis.forwardDrive(gamepad1.left_stick_y);
        } else if (gamepad1.left_stick_y < 0) {
            Chassis.reverseDrive(Math.abs(gamepad1.left_stick_y));
        } else if (gamepad1.right_stick_x > 0) {
            Chassis.leftSpin(gamepad1.right_stick_x);
        } else if (gamepad1.right_stick_x < 0) {
            Chassis.rightSpin(Math.abs(gamepad1.right_stick_x));
        } else {
            Chassis.stopDrive();
        }


    }
}
