package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class FirstOpmode extends LinearOpMode{
    private Gyroscope imu;
    private DcMotor motLeft;
    private DcMotor motRight;
    private Servo handeee;
    private DcMotor armCentre;
    @Override
    public void runOpMode() throws InterruptedException {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        DcMotor driveMotor1 = hardwareMap.get(DcMotor.class, "motLeft");
        DcMotor driveMotor2 = hardwareMap.get(DcMotor.class, "motRight");
        DcMotor armMot = hardwareMap.get(DcMotor.class, "armCentre");

        Servo handServo = hardwareMap.get(Servo.class, "handeee");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        double tgtPower = 0;
        double armTgtPower = 255;
        handServo.setPosition(0);
        int turnIn = 0;

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
                driveMotor2.setPower(tgtPower + this.gamepad1.left_stick_x);
            } else if (this.gamepad1.left_stick_x < 0) {
                //right
                driveMotor1.setPower(tgtPower + this.gamepad1.left_stick_x);
            }

            if (this.gamepad1.dpad_up) {
                armMot.setPower(armTgtPower);
            } else if (this.gamepad1.dpad_down) {
                armMot.setPower(armTgtPower * -1);
            } else {
                armMot.setPower(0);
            }
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", driveMotor1.getPower());
            telemetry.addData("Motor Power", driveMotor2.getPower());
            telemetry.addData("ARM POWER", armMot.getPower());

            //SERVOS

            if(gamepad1.a && handServo.getPosition() == 1 && turnIn == 0) {
                // move to 0 degrees.
                turnIn = 1;
                handServo.setPosition(0);

                turnIn = 0;
            } else if (gamepad1.a && handServo.getPosition() == 0 && turnIn == 0 ) {
                // Open hand for grabbing
                turnIn = 1;
                handServo.setPosition(1);
                turnIn = 0;
            }
            telemetry.addData("Servo Position", handServo.getPosition());
            telemetry.addData("Status", "Running");
            telemetry.update();

        }
    }
}
