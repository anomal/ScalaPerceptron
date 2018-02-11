package com.github.anomal.perceptron

import org.junit.Assert._
import org.junit.Test

class BinaryThresholdNeuronTest {

  @Test def testThreshold() = {
    val inputs = Seq(1.0, 2.0, -4.0)
    val neuron = new BinaryThresholdNeuron(inputs.length, 0.0)
    assertEquals(0, neuron.output(inputs))
    val neuron2 = new BinaryThresholdNeuron(inputs.length, -1.0)
    assertEquals(1, neuron2.output(inputs))
  }

}
