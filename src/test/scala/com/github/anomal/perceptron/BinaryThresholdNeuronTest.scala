package com.github.anomal.perceptron

import scala.util.{Try, Success, Failure}
import org.junit.Assert._
import org.junit.Test

class BinaryThresholdNeuronTest {

  @Test def testThreshold0 = {

    val inputs = Seq(1.0, 2.0, -4.0)
    val weights:Seq[Double] = Seq.fill(inputs.length)(1.0)
    val neuron = new BinaryThresholdNeuron(weights, 0.0)
    neuron.output(inputs) match {
      case Success(v) => assertEquals(0, v)
      case Failure(e) => throw e
    }


  }

  @Test def testThresholdNeg1 = {
    val inputs = Seq(1.0, 2.0, -4.0)
    val weights:Seq[Double] = Seq.fill(inputs.length)(1.0)
    val neuron = new BinaryThresholdNeuron(weights, -1.0)
    neuron.output(inputs) match {
      case Success(v) => assertEquals(1, v)
      case Failure(e) => throw e
    }
  }

}
