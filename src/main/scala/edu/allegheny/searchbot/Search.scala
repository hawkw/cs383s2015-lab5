package edu.allegheny.searchbot;

import scala.language.postfixOps
import scala.math;

/**
 * The search program.
 * @author Hawk Weisman
 */
object Search {
    // robot bits
    val angleProvider = searchbot getAngleMode
    val rangeProvider = searchbot getDistanceMode
    val pilot         = searchbot getPilot

    // Constants, change as appropriate
    val MaxRange = 50.0f // maximum search radius for the environment
    val MaxIter  = 1000 // maximum number of iterations for search

    private val sample = new Array[Float](2) // this is because the LeJOS api is awful

    type Vector     = (Float,Float)
    type Coordinate = (Float,Float)

    def turnAndRange(angle: Int): Option[(Float,Float)] = {
        pilot rotate angle
        range
    }

    /**
     * Gets a range from the ultrasonic sensor and the current heading
     * from the gyro.
     * Returns a Some((Float, Float) if something is within range,
     * None if no objects were detected within the search range.
     * @type {Option[(Float,Float)]}
     */
    def range: Option[(Float,Float)] = {
        rangeProvider fetchSample (sample,0)
        angleProvider fetchSample (sample,1)
        sample match {
            case Array(range, angle) if range <= MaxRange   => Some((range,angle))
            case _                                          => None
        }
    }

    def toCartesian(v: Vector): Coordinate = {
        case (r, theta) => (r * Math.cos(theta), r * Math.sin(theta))
    }

    def estimateDestination(loc1: Coordinate, loc2: Coordinate): Coordinate = {
        case ((x1,y1), (x2,y2)) =>
            val deltaX = x2 - x1
            val deltaY = y2 - y1
            (x2 + deltaX, y2 + deltaY)
    }

    def distance(loc1: Coordinate, loc2: Coordinate): Float = {
        case((x1,y1),(x2,y2)) => Math.sqrt((x2 - x1)**2 + (y2 - y1)**2)
    }

    def getHeadingAngle(loc1: Coordinate, loc2: Coordinate): Float = {
        val a = distance(loc2,loc1)
        val b = distance(loc2,(0,0))
        val c = distance(loc3,(0,0))
        Math.acos((b**2 + c**2 - a**2) / 2*b*c)
    }

    def main (argv: Array[String]): Unit = for {
            angle <- 0 until MaxIter
            range <- turnAndRange(angle % 90)
        } {
            // this block will execute when we find the target
            // TODO: put target tracking code here.
        }
    }*/

}
