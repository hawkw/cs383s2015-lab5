package edu.allegheny.searchbot;

object Search {

    val rangeProvider = searchbot getDistanceMode
    val MaxRange = 50.0f // change as appropriate
    private val sample = new Array[Float](1) // this is because the LeJOS api is awful

    /**
     * Gets a range from the ultrasonic sensor.
     * Returns a Some(Float) if something is within range,
     * None if no objects were detected within the search range.
     * @type {Option[Float]}
     */
    def range: Option[Float] = {
        rangeProvider fetchSample(sample)
        sample match {
            case Array(i if i <= MaxRange)  => Some(i)
            case _                          => None
        }
    }


}