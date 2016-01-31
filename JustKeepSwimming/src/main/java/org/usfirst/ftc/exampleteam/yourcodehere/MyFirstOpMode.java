package org.usfirst.ftc.exampleteam.yourcodehere;

import com.qualcomm.robotcore.hardware.*;
import org.swerverobotics.library.*;
import org.swerverobotics.library.interfaces.*;

/**
 * A skeletal example of a do-nothing first OpMode. Go ahead and change this code
 * to suit your needs, or create sibling OpModes adjacent to this one in the same
 * Java package.
 */
@TeleOp(name="My First OpMode")
public class MyFirstOpMode extends SynchronousOpMode
    {
    /* Declare here any fields you might find useful. */
    // DcMotor treadMotorLeft = null;
    // DcMotor treadMotorRight = null;

    @Override public void main() throws InterruptedException
        {
        /* Initialize our hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names you assigned during the robot configuration
         * step you did in the FTC Robot Controller app on the phone.
         */
        // this.treadMotorLeft = this.hardwareMap.dcMotor.get("treadMotorLeft");
        // this.treadMotorRight = this.hardwareMap.dcMotor.get("treadMotorRight");

        // Wait for the game to start
        waitForStart();

        // Go go gadget robot!
        while (opModeIsActive())
            {
            if (updateGamepads())
                {
                // The game pad state has changed. Do something with that!
                }

            telemetry.update();
            idle();
            }
        }
    }
