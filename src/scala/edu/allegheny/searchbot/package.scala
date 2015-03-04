package edu.allegheny.searchbot;

import lejos.hardware.ev3.EV3
import lejos.hardware.motor.EV3LargeRegulatedMotor
import lejos.hardware.port.{MotorPort, SensorPort, Port}
import lejos.robotics.navigation.DifferentialPilot

object searchbot {
    val LMotorPort  = MotorPort.B // change as appropriate
    val RMotorPort  = MotorPort.C // change as appropriate
    val WheelDiam   = 2.0625f
    val TrackWidth  = 4.75f

    def getPilot = new DifferentialPilot(
            WheelDiam,
            TrackWidth,
            new EV3LargeRegulatedMotor(LMotorPort),
            new EV3LargeRegulatedMotor(RMotorPort)
        )

}