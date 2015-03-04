package edu.allegheny.searchbot;

import scala.util.Random.nextInt

object RandomNav {

    val maxDist = 50 // TODO: placeholder, replace this with correct value
    val moves   = Stream continually ((nextInt(360), nextInt(maxDist)))
    val pilot   = searchbot getPilot

    def main(argv: Array[String]) = moves foreach {
        // TODO: break on button press
        // TODO: possibly some kind of collision avoidance?
        (heading, dist) =>
            pilot rotate heading
            pilot travel dist
    }

}
