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

    @Override
    public void runOpMode() throws InterruptedException {
        imu = hardwareMap.get(Gyroscope.class, "imu");
        DcMotor driveMotor1 = hardwareMap.get(DcMotor.class, "motLeft");
        DcMotor driveMotor2 = hardwareMap.get(DcMotor.class, "motRight");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        double tgtPower = 0;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("Status", "Running");
            tgtPower = -this.gamepad1.left_stick_y;
            driveMotor1.setPower(tgtPower);
            driveMotor2.setPower(tgtPower);
            telemetry.addData("Target Power", 5);
            telemetry.addData("Motor Power", driveMotor1.getPower());
            telemetry.addData("Motor Power", driveMotor2.getPower());
            telemetry.addData("Status", "Running");
            telemetry.update();
            telemetry.update();

        }
    }
}
