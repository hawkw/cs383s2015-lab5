package edu.allegheny.searchbot;

import scala.util.Random.nextInt
import scala.language.postfixOps
import lejos.hardware.BrickFinder
import lejos.hardware.ev3.EV3

object RandomNav {

    val maxDist = 50 // TODO: placeholder, replace this with correct distance
    val keys    = BrickFinder.getLocal.asInstanceOf[EV3].getKeys
    val moves   = Stream continually ((nextInt(360), nextInt(maxDist), keys readButtons))
    val pilot   = searchbot getPilot // package object handles this

    def main(argv: Array[String]) = moves takeWhile {
        // if no buttons were pressed, buttons should be zero
        case (_,_,buttons) => buttons == 0
    } foreach {
        // TODO: possibly some kind of collision avoidance?
        case (heading, dist, _) =>
            pilot rotate heading
            pilot travel dist
    }

}
