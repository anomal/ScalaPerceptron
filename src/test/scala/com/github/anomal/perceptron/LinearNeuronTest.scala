package com.github.anomal.perceptron

import scala.util.{Try, Success, Failure}
import org.junit.Assert._
import org.junit.Test

class LinearNeuronTest {

  @Test
  def testOutput = {

    val inputs = Seq(1.0, 2.0, 3.0)
    val weights:Seq[Double] = Seq.fill(inputs.length)(1.0)
    var neuron = new LinearNeuron(weights, 0.5)
    neuron.output(inputs) match {
      // should be 6 + 0.5 = 6.5
      case Success(v) => assertEquals(6.5, v, 0.01)
      case Failure(e) => throw e
    }

  }
}
