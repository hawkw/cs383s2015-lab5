package edu.allegheny.searchbot;

import scala.util.Random.nextInt
import lejos.hardware.BrickFinder
import lejos.hardware.ev3.EV3

object RandomNav extends App {

    val maxDist = 50 // TODO: placeholder, replace this with correct values
    val keys    = BrickFinder.getLocal.asInstanceOf[EV3].getKeys
    val moves   = Stream continually ((nextInt(360), nextInt(maxDist), keys readButtons))
    val pilot   = searchbot getPilot

    def main(argv: Array[String]) = moves
    takeWhile {
        (_,_,buttons) => buttons == 0
    } foreach {
        // TODO: possibly some kind of collision avoidance?
        (heading, dist, _) =>
            pilot rotate heading
            pilot travel dist
    }

}
