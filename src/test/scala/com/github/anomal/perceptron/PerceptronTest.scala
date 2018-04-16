package com.github.anomal.perceptron

import scala.util.{Try, Success, Failure}
import org.junit.Assert._
import org.junit.Test

class PerceptronTest {

  private val rand = scala.util.Random

  /**
    * Test that perceptron can classify points on or to the top-right of the line y = -x + 5
    * with label 1, and other points with label 0
    */
  @Test def testLearnEquationOfLineNegativeSlope = {
    val slope = -1
    val yIntercept = 5

    val perceptron = new Perceptron
    val numTrainingCases = 5000
    val numTests = 50
    val weights:Seq[Double] = Seq.fill(2)(1.0)
    var decisionUnit = new BinaryThresholdNeuron(weights, 0)

    for (i <- 0 until numTrainingCases) {

      val x1 = randomNumberBetween0and(10)
      val y1 = randomNumberBetween0and(10)

      // for a given x1, the expected y should be on or above the line y = -x + 5
      val y = slope * x1 + yIntercept

      if (y1 >= y) {
        decisionUnit = perceptron.train(decisionUnit, Seq(x1,y1), 1) match {
          case Success(u) => u
          case Failure(e) => throw e
        }
      } else {
        decisionUnit = perceptron.train(decisionUnit, Seq(x1,y1), 0) match {
          case Success(u) => u
          case Failure(e) => throw e
        }
      }
    }

    var numPass = 0
    for (i <- 0 until numTests) {
      val x1 = randomNumberBetween0and(10)
      val y1 = randomNumberBetween0and(10)

      // for a given x1, the expected y should be on or above the line y = -x + 5
      val y = slope * x1 + yIntercept
      var expected = y1 match {
        case y1 if (y1 >= y) => 1
        case _ => 0
      }

      decisionUnit.output(Seq(x1, y1)) match {
        case Success(actual) =>
          if (actual == expected) {
          numPass += 1
          }
      }
    }

    val percentCorrect = 100.0*numPass/numTests
    println(s"testLearnEquationOfLineNegativeSlope accuracy: $percentCorrect%")

    print("weights: ")
    for (weight <- decisionUnit.weights) { print(s"$weight ") }
    println()

    assertTrue(percentCorrect > 70)
  }

  private def randomNumberBetween0and(max : Int): Double = {
    rand.nextDouble() * (max + 1)
  }

  /**
    * Test that perceptron can classify points above the line y = 3
    * with label 1, and other points with label 0
    */
  @Test def testLearnEquationOfLineZeroSlope = {
    val slope = 0
    val yIntercept = 3

    val perceptron = new Perceptron
    val numTrainingCases = 5000
    val numTests = 50
    val weights:Seq[Double] = Seq.fill(2)(1.0)
    var decisionUnit = new BinaryThresholdNeuron(weights, 0)

    for (i <- 0 until numTrainingCases) {

      val x1 = randomNumberBetween0and(10)
      val y1 = randomNumberBetween0and(10)

      // for a given x1, the expected y should be on or above the line y = 3
      val y = slope * x1 + yIntercept

      if (y1 >= y) {
        decisionUnit = perceptron.train(decisionUnit, Seq(x1,y1), 1) match {
          case Success(u) => u
          case Failure(e) => throw e
        }
      } else {
        decisionUnit = perceptron.train(decisionUnit, Seq(x1,y1), 0) match {
          case Success(u) => u
          case Failure(e) => throw e
        }
      }
    }

    var numPass = 0
    for (i <- 0 until numTests) {
      val x1 = randomNumberBetween0and(10)
      val y1 = randomNumberBetween0and(10)

      // for a given x1, the expected y should be on or above the line y = 3
      val y = slope * x1 + yIntercept
      var expected = y1 match {
        case y1 if (y1 >= y) => 1
        case _ => 0
      }

      decisionUnit.output(Seq(x1, y1)) match {
        case Success(actual) =>
          if (actual == expected) {
            numPass += 1
          }
      }

    }

    val percentCorrect = 100.0*numPass/numTests
    println(s"testLearnEquationOfLineZeroSlope accuracy: $percentCorrect%")

    print("weights: ")
    for (weight <- decisionUnit.weights) { print(s"$weight ") }
    println()

    assertTrue(percentCorrect > 70)
  }

}