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
        this.treadMotorLeft = this.hardwareMap.dcMotor.get("treadMotorLeft");
        this.treadMotorRight = this.hardwareMap.dcMotor.get("treadMotorRight");
        this.tapeExtendLeft = this.hardwareMap.dcMotor.get("tapeExtendLeft");
        this.tapeExtendRight = this.hardwareMap.dcMotor.get("tapeExtendRight");
        this.tapeAngle = this.hardwareMap.servo.get("tapeAngle");

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
                // The game pad state has changed. Do something with that!
                treadMotorLeft.setPower(gamepad1.left_stick_y);
                treadMotorRight.setPower(gamepad1.right_stick_y);

                double newPosition = tapeAngle.getPosition();
                if (gamepad1.a) {
                    newPosition = tapeAngle.getPosition() + tapeAngle_increment;
                } else if (gamepad1.b) {
                    newPosition = tapeAngle.getPosition() - tapeAngle_increment;
                }
                if (newPosition < tapeAngle_min) {
                    newPosition = tapeAngle_min;
                }
                if (newPosition > tapeAngle_max) {
                    newPosition = tapeAngle_max;
                }
                tapeAngle.setPosition(newPosition);
            }

            telemetry.update();
            idle();
        }
    }
}
