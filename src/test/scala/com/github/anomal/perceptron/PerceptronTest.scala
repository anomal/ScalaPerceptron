package com.github.anomal.perceptron

import org.junit.Assert._
import org.junit.Test

class PerceptronTest {

  private val rand = scala.util.Random

  /**
    * Test that perceptron can classify points on or to the top-right of the line y = -x + 5
    * with label 1, and other points with label 0
    */
  @Test def testLearnEquationOfLine() = {
    val perceptron = new Perceptron(2, 0.0)
    val numTrainingCases = 5000
    val numTests = 50

    for (i <- 0 until numTrainingCases) {
      val x1 = randomNumberBetween0and(10)
      val y1 = randomNumberBetween0and(10)

      // for a given x1, the expected y should be on or above the linen y = -x + 5
      val y = -x1 + 5

      if (y1 >= y) {
        perceptron.train(Seq(x1,y1), 1)
      } else {
        perceptron.train(Seq(x1,y1), 0)
      }
    }

    var numPass = 0
    for (i <- 0 until numTests) {
      val x1 = randomNumberBetween0and(10)
      val y1 = randomNumberBetween0and(10)

      // for a given x1, the expected y should be on or above the linen y = -x + 5
      val y = -x1 + 5
      var expected = y1 match {
        case y1 if (y1 >= y) => 1
        case _ => 0
      }

      val actual = perceptron.classify(Seq(x1, y1))

      if (actual == expected) {
        numPass += 1
      }

    }

    val percentCorrect = 100.0*numPass/numTests
    println("testLearnEquationOfLine accuracy: " + percentCorrect)
    assertTrue(percentCorrect > 75)
  }

  private def randomNumberBetween0and(max : Int): Double = {
    rand.nextDouble() * (max + 1)
  }

}