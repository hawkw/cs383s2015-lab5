package edu.allegheny.searchbot;

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

    def turnAndRange(angle: Int): Option[(Float,Float)] = {
        pilot rotate heading
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
        rangeProvider fetchSample (sample)
        angleProvider fetchSample (sample,1)
        sample match {
            case Array(range, angle) if range <= maxRange   => Some((range,angle))
            case _                                          => None
        }
    }

    def main (argv: Attay[String]) = for {
            angle <- 0 until MaxIter
            range <- turnAndRange(angle % 90)
        } {
            // this block will execute when we find the target
            // TODO: put target tracking code here.
        }

}
