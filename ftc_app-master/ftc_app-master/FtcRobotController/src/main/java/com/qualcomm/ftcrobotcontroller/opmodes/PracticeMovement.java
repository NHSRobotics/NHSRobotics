package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by phama21 on 9/13/2015.
 */
public class PracticeMovement extends OpMode {

    public MovementTeleOp Chassis;


    @Override
    public void init() {
        Chassis = new MovementTeleOp(hardwareMap.dcMotor.get("leftMotors"), hardwareMap.dcMotor.get("rightMotors"));

    }

    @Override
    public void loop() {

        if (gamepad1.y) {
            Chassis.nudgeDrive(1);
        } else if (gamepad1.x) {
            Chassis.nudgeDrive(2);
        } else if (gamepad1.a) {
            Chassis.nudgeDrive(3);
        } else if (gamepad1.b) {
            Chassis.nudgeDrive(4);
        } else {
            Chassis.tankDrive(gamepad1, true);
        }
    }
}
