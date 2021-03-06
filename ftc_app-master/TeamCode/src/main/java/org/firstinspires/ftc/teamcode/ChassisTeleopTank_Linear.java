/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwareK9bot;

/**
 * This OpMode uses the common HardwareK9bot class to define the devices on the robot.
 * All device access is managed through the HardwareK9bot class. (See this class for device names)
 * The code is structured as a LinearOpMode
 *
 * This particular OpMode executes a basic Tank Drive Teleop for the K9 bot
 * It raises and lowers the arm using the Gampad Y and A buttons respectively.
 * It also opens and closes the claw slowly using the X and B buttons.
 *
 * Note: the configuration of the servos is such that
 * as the arm servo approaches 0, the arm position moves up (away from the floor).
 * Also, as the claw servo approaches 0, the claw opens up (drops the game element).
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Chassis: Teleop Tank", group="Chassis")
//@Disabled
public class ChassisTeleopTank_Linear extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareChassis   robot           = new HardwareChassis();              // Use a K9'shardware
//    double          armPosition     = robot.ARM_HOME;                   // Servo safe position
//    double          clawPosition    = robot.CLAW_HOME;                  // Servo safe position
//    final double    CLAW_SPEED      = 0.01 ;                            // sets rate to move servo
//    final double    ARM_SPEED       = 0 .01 ;                            // sets rate to move servo

    @Override
    public void runOpMode() {
        // hsvValues is an array that will hold the hue, saturation, and value information.
//        float hsvValues[] = {0F,0F,0F};

        // values is a reference to the hsvValues array.
//        final float values[] = hsvValues;


        double left;
        double right;
        double direction;
        double power;
        double spinnerSpeed = 0;
        boolean rightBumperPressed = false;
        boolean leftBumperPressed = false;


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Set the LED in the beginning
//        robot.colorSensor.enableLed(false);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // convert the RGB values to HSV values.
//            Color.RGBToHSV(robot.colorSensor.red() * 8, robot.colorSensor.green() * 8, robot.colorSensor.blue() * 8, hsvValues);

            // send the info back to driver station using telemetry function.
//            telemetry.addData("Clear", robot.colorSensor.alpha());
//            telemetry.addData("Red  ", robot.colorSensor.red());
//            telemetry.addData("Green", robot.colorSensor.green());
//            telemetry.addData("Blue ", robot.colorSensor.blue());
//            telemetry.addData("Hue", hsvValues[0]);

            // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)

            direction = Math.atan2(gamepad1.left_stick_x,  gamepad1.right_stick_y);
            power = Math.sqrt(gamepad1.left_stick_x*gamepad1.left_stick_x + gamepad1.left_stick_y*gamepad1.left_stick_y)

            left = Math.sin(direction)+Math.cos(direction);
            right =Math.sin(direction)-Math.cos(direction);

            //left = -gamepad1.left_stick_x+gamepad1.left_stick_y;

            //right = gamepad1.left_stick_x+gamepad1.left_stick_y;

            robot.leftMotor.setPower(power*left);
            robot.rightMotor.setPower(power*right);

            leftBumperPressed = gamepad1.left_bumper;
            rightBumperPressed = gamepad1.right_bumper;

            if (rightBumperPressed) {
                robot.leftSpinnerMotor.setPower(-1);
                robot.rightSpinnerMotor.setPower(-1);
            }
            else if (leftBumperPressed){
                robot.leftSpinnerMotor.setPower(0);
                robot.rightSpinnerMotor.setPower(0);
            }

            if (gamepad1.a)
                robot.flapperMotor.setPower(-1);
            else if (gamepad1.b)
                robot.flapperMotor.setPower(0);
            else if (gamepad1.x)
                robot.flapperMotor.setPower(1);
            // Use gamepad Y & A raise and lower the arm
//            if (gamepad1.a)
//                armPosition += ARM_SPEED;
//            else if (gamepad1.y)
//                armPosition -= ARM_SPEED;
//
//            // Use gamepad X & B to open and close the claw
//            if (gamepad1.x)
//                clawPosition += CLAW_SPEED;
//            else if (gamepad1.b)
//                clawPosition -= CLAW_SPEED;
//
//            // Move both servos to new position.
//            armPosition  = Range.clip(armPosition, robot.ARM_MIN_RANGE, robot.ARM_MAX_RANGE);
//            robot.arm.setPosition(armPosition);
//            clawPosition = Range.clip(clawPosition, robot.CLAW_MIN_RANGE, robot.CLAW_MAX_RANGE);
//            robot.claw.setPosition(clawPosition);
//
//            // Send telemetry message to signify robot running;
//            telemetry.addData("arm",   "%.2f", armPosition);
//            telemetry.addData("claw",  "%.2f", clawPosition);
            telemetry.addData("left",  "%.2f", left);
            telemetry.addData("right", "%.2f", right);
            telemetry.update();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            robot.waitForTick(40);
        }
    }
}
