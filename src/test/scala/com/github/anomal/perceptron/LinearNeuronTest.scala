package com.github.anomal.perceptron

import org.junit.Assert._
import org.junit.Test

class LinearNeuronTest {

  @Test
  def testOutputAndAdjustWeights() = {
    val inputs = Seq(1.0, 2.0, 3.0)
    var neuron = new LinearNeuron(inputs.length, 0.5)
    // should be 6 + 0.5 = 6.5
    assertEquals(6.5, neuron.output(inputs), 0.01)
    neuron.adjustWeights(Seq.fill(inputs.length)(-0.5))
    // should be (0.5 + 1 + 1.5) + 0.5 = 3 + 0.5 = 3.5
    assertEquals(3.5, neuron.output(inputs), 0.01)
  }
}
