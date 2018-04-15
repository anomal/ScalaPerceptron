package com.github.anomal.perceptron

import scala.util.{Try, Success, Failure}
import org.junit.Assert._
import org.junit.Test

class BinaryThresholdNeuronTest {

  @Test def testThreshold() = {
    val inputs = Seq(1.0, 2.0, -4.0)
    val neuron = new BinaryThresholdNeuron(inputs.length, 0.0)
    neuron.output(inputs) match {
      case Success(v) => assertEquals(0, v)
      case Failure(e) => throw e
    }
    val neuron2 = new BinaryThresholdNeuron(inputs.length, -1.0)
    neuron2.output(inputs) match {
      case Success(v) => assertEquals(1, v)
      case Failure(e) => throw e
    }

  }

}
