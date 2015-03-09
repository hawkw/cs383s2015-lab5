package edu.allegheny.searchbot;

/**
 * The search program.
 * @author Hawk Weisman
 */
object Search {
    val angleProvider = searchbot getAngleMode
    val rangeProvider = searchbot getDistanceMode
    val MaxRange = 50.0f // change as appropriate
    private val sample = new Array[Float](2) // this is because the LeJOS api is awful

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
            case Array(r if r <= MaxRange, a)   => Some((i,a))
            case _                              => None
        }
    }


}