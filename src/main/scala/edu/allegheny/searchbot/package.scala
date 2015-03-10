package edu.allegheny.searchbot;

import lejos.hardware.ev3.EV3
import lejos.hardware.motor.EV3LargeRegulatedMotor
import lejos.hardware.sensor.{EV3UltrasonicSensor,EV3GyroSensor}
import lejos.hardware.port.{MotorPort, SensorPort, Port}
import lejos.robotics.navigation.DifferentialPilot


import scala.language.postfixOps

/**
 * Contains common constants and functions for both programs.
 * @author Hawk Weisman
 */
object searchbot {
    // Port assignment for sensors and motors
    // Change these as appropriate
    val LMotorPort      = MotorPort.B
    val RMotorPort      = MotorPort.C
    val UltrasonicPort  = SensorPort.S1
    val GyroPort        = SensorPort.S2

    // Robot physical constants
    // change as appropriate
    val WheelDiam   = 2.0625f
    val TrackWidth  = 4.75f

    /**
     * Makes a DifferentialPilot for this robot configuration
     * @type {DifferentialPilot}
     * @return an instance of DifferentialPilot configured for the
     *         robot's hardware configuration
     */
    def getPilot = new DifferentialPilot(
            WheelDiam,
            TrackWidth,
            new EV3LargeRegulatedMotor(LMotorPort),
            new EV3LargeRegulatedMotor(RMotorPort)
        )
    /**
     * Instantiates an
     * [[lejos.hardware.sensor.EV3UltrasonicSensor UltrasonicSensor]] and
     *  returns that sensor's distance mode sample provider.
     * @type {EV3UltrasonicSensor}
     * @return a [[lejos.robotics.SampleProvider SampleProvider]] for distance
     *         samples
     */
    def getDistanceMode = new EV3UltrasonicSensor(UltrasonicPort) getDistanceMode
    /**
     * Instantiates an
     * [[lejos.hardware.sensor.EV3GyroSensor GyroSensor]] and
     *  returns that sensor's distance mode sample provider.
     * @type {EV3UltrasonicSensor}
     * @return a [[lejos.robotics.SampleProvider SampleProvider]] for distance
     *         samples
     */
    def getAngleMode    = new EV3GyroSensor(GyroPort) getAngleMode

}