package edu.allegheny.searchbot;

import scala.util.Random

object RandomNav {

    val maxDist = 50 // TODO: placeholder, replace this with correct value
    val headings:   Stream[Int]         = Random nextInt(360)       :: headings
    val distances:  Stream[Int]         = Random nextInt(maxDist)   :: distances
    val moves:      Stream[(Int,Int)]   = headings zip distances

    def main(argv: Array[String]) = moves.foreach{ (heading, dist) => ??? }

}
