package com.github.anomal.perceptron

import scala.util.{Try, Success, Failure}
import org.junit.Assert._
import org.junit.Test

class LinearNeuronTest {

  @Test
  def testOutputAndAdjustWeights() = {
    val inputs = Seq(1.0, 2.0, 3.0)
    var neuron = new LinearNeuron(inputs.length, 0.5)
    neuron.output(inputs) match {
      // should be 6 + 0.5 = 6.5
      case Success(v) => assertEquals(6.5, v, 0.01)
      case Failure(e) => throw e
    }

    neuron.adjustWeights(Seq.fill(inputs.length)(-0.5))

    neuron.output(inputs) match {
      case Success(v) =>
        // should be (0.5 + 1 + 1.5) + 0.5 = 3 + 0.5 = 3.5
        assertEquals(3.5, v, 0.01)
      case Failure(e) => throw e

    }

  }
}
