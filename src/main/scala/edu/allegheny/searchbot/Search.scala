package edu.allegheny.searchbot;

import scala.language.{postfixOps, implicitConversions}
import scala.math
import searchbot._

/**
 * The search program.
 * @author Hawk Weisman
 * @author Willem Yarbrough
 */
object Search {

    class FloatWithExp(self:Float) {
        def ~* (other: Float) = math.pow(self,other)
    }

    implicit def addExpToFloat(it: Float): FloatWithExp = new FloatWithExp(it)

    // robot bits
    val angleProvider = getAngleMode
    val rangeProvider = getDistanceMode
    val pilot         = getPilot

    // Constants, change as appropriate
    val MaxRange = 50.0f // maximum search radius for the environment
    val MaxIter  = 1000 // maximum number of iterations for search

    private val sample = new Array[Float](2) // this is because the LeJOS api is awful

    type Vector     = Tuple2[Float,Float]
    type Coordinate = Tuple2[Float,Float]

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

    /**
     * Takes a polar-form vector (magnitude, angle) and uses trig
     * to derive cartesian coordinates (with our robot's position as origin)
     * @param v polar vector to be transformed
     * @return (x,y) where x and y are derived from the components of v
     */
    def toCartesian(v: Vector): Coordinate = {
        v match { case (r, theta) => (
            r * Math.cos(theta.asInstanceOf[Double]).asInstanceOf[Float],
            r * Math.sin(theta.asInstanceOf[Double]).asInstanceOf[Float]
            ) }
    }

    /**
     * given two (x,y) coordinates, use their components to extrapolate
     * a further location along the line between them, whose distance from loc2
     * is equal to the distance between loc2 and loc1
     * @param loc1 first coordinate
     * @param loc2 second coordinate
     * @return new (x,y) coordinate
     */
    def estimateDestination(loc1: Coordinate, loc2: Coordinate): Coordinate = {
        (loc1, loc2) match { case ((x1,y1), (x2,y2)) =>
            val deltaX = x2 - x1
            val deltaY = y2 - y1
            (x2 + deltaX, y2 + deltaY) }
    }

    /**
     * Standard euclidean distance function. (Probably should've used
     * some library for this?)
     * @param loc1 first coordinate
     * @param loc2 second coordinate
     * @return     distance between the two coordinates
     */
    def distance(loc1: Coordinate, loc2: Coordinate): Float = {
        (loc1, loc2) match {
        case((x1,y1),(x2,y2)) => Math.sqrt(((x2 - x1)~*2 + (y2 - y1)~*2).asInstanceOf[Double]).asInstanceOf[Float] }
    }

    /**
     * Returns the difference between the headings when our robot faces loc1 and
     * loc2. Used as a helper function for calculating the distance our robot
     * needs to turn to face a new location.
     * @param loc1 first coordinate
     * @param loc2 second coordinate
     * @return     difference in heading between the two
     */
    def getHeadingDifference(loc1: Coordinate, loc2: Coordinate): Float = {
        val a = distance(loc2,loc1)
        val b = distance(loc2,(0,0))
        val c = distance(loc1,(0,0))
        Math.acos(((b~*2 + c~*2 - a~*2) / 2*b*c).asInstanceOf[Double]).asInstanceOf[Float]
    }

    def main (argv: Array[String]): Unit = {
        var pos_prev: Option[Coordinate] = None
        for {
            angle <- 0 until MaxIter
            pos <- toCartesian turnAndRange(angle)
        } { // if we've spotted the target
            pos_prev foreach { // if we've spotted the target previously

            }
            pos_prev = pos
        }
    }

}
