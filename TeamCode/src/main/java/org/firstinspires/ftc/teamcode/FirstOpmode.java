package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class FirstOpmode extends LinearOpMode{
    private Gyroscope imu;
    private DcMotor motLeft;
    private DcMotor motRight;
    private Servo handeee;

    @Override
    public void runOpMode() throws InterruptedException {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        DcMotor driveMotor1 = hardwareMap.get(DcMotor.class, "motLeft");
        DcMotor driveMotor2 = hardwareMap.get(DcMotor.class, "motRight");

        Servo handServo = hardwareMap.get(Servo.class, "handeee");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        double tgtPower = 0;
        double armTgtPower = 20;
        handServo.setPosition(0);

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            // MOTOR CONTROL
            tgtPower = -this.gamepad1.left_stick_y;
            if (this.gamepad1.left_stick_x == 0) {
                driveMotor1.setPower(tgtPower);
                driveMotor2.setPower(tgtPower * -1);
            } else if (this.gamepad1.left_stick_x > 0) {
                // left
                driveMotor1.setPower(tgtPower);
                driveMotor2.setPower(tgtPower);
            } else if (this.gamepad1.left_stick_x < 0) {
                //right
                driveMotor1.setPower(tgtPower * -1);
                driveMotor2.setPower(tgtPower * -1);
            }
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", driveMotor1.getPower());
            telemetry.addData("Motor Power", driveMotor2.getPower());

            //SERVOS
            if(gamepad1.y) {
                // move to 0 degrees.
                handServo.setPosition(0);
            } else if (gamepad1.x) {
                // move to 90 degrees.
                handServo.setPosition(0.5);
            } else if (gamepad1.b) {
                // move to 180 degrees.
                handServo.setPosition(1);
            }
            telemetry.addData("Servo Position", handServo.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
