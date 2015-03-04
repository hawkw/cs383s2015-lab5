package edu.allegheny.searchbot;

import scala.util.Random

object RandomNav {

    val maxDist = 50 // TODO: placeholder, replace this with correct value
    val headings: Stream[Int]   = Random.nextInt(360)     :: headings
    val distances: Stream[Int]  = Random.nextInt(maxDist) :: distances

}