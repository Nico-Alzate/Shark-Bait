package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.TeleOp;

/**
 * A skeletal example of a do-nothing first OpMode. Go ahead and change this code
 * to suit your needs, or create sibling OpModes adjacent to this one in the same
 * Java package.
 */
@TeleOp(name="TutorialTeleOp")
public class TutorialTeleOp extends SynchronousOpMode {
    /* Declare here any fields you might find useful. */
    DcMotor treadMotorLeft = null;
    DcMotor treadMotorRight = null;
    DcMotor tapeExtendLeft = null;
    DcMotor tapeExtendRight = null;
    Servo tapeAngle = null;

    double tapeAngle_min = 0.0;
    double tapeAngle_max = 1.0;
    double tapeAngle_increment = 0.1;

    @Override public void main() throws InterruptedException
    {
        /* Initialize our hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names you assigned during the robot configuration
         * step you did in the FTC Robot Controller app on the phone.
         */
        treadMotorLeft = hardwareMap.dcMotor.get("treadMotorLeft");
        treadMotorRight = hardwareMap.dcMotor.get("treadMotorRight");
        tapeExtendLeft = hardwareMap.dcMotor.get("tapeExtendLeft");
        tapeExtendRight = hardwareMap.dcMotor.get("tapeExtendRight");
        tapeAngle = hardwareMap.servo.get("tapeAngle");

        treadMotorLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        treadMotorRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        tapeExtendLeft.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
        tapeExtendRight.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

        treadMotorLeft.setDirection(DcMotor.Direction.REVERSE);
        tapeExtendLeft.setDirection(DcMotor.Direction.REVERSE);

        tapeAngle.setPosition(tapeAngle_min);



        // Wait for the game to start
        waitForStart();

        // Go go gadget robot!
        while (opModeIsActive())
        {
            if (updateGamepads())
            {
                controlTheTreads();
                controlTheTapeAngle();
                controlTheTapeExtend();
            }

            telemetry.update();
            idle();
        }
    }

    private void controlTheTreads() {
        treadMotorLeft.setPower(gamepad1.left_stick_y);
        treadMotorRight.setPower(gamepad1.right_stick_y);
    }

    private void controlTheTapeExtend() {
        // when you press y, make the motors go forward, max power
        // when you press x, make the motors go backward, max power
        // when you press nothing, stop the motors.

        if (gamepad1.y) {
            tapeExtendLeft.setPower(1.0);
            tapeExtendRight.setPower(1.0);
        } else if (gamepad1.x) {
            tapeExtendLeft.setPower(-1.0);
            tapeExtendRight.setPower(-1.0);
        } else {
            tapeExtendLeft.setPower(0.0);
            tapeExtendRight.setPower(0.0);
        }
    }

    private void controlTheTapeAngle() {
        double newPosition = tapeAngle.getPosition();
        if (gamepad1.a) {
            newPosition = tapeAngle.getPosition() - tapeAngle_increment;
        } else if (gamepad1.b) {
            newPosition = tapeAngle.getPosition() + tapeAngle_increment;
        }
        if (newPosition < tapeAngle_min) {
            newPosition = tapeAngle_min;
        }
        if (newPosition > tapeAngle_max) {
            newPosition = tapeAngle_max;
        }
        tapeAngle.setPosition(newPosition);
    }
}
