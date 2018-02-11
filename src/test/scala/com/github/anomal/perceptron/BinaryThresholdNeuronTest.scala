package com.github.anomal.perceptron

object BinaryThresholdNeuronTest {

  def main(args:Array[String]) = {
    val inputs = Seq(1.0, 2.0, -4.0)
    val neuron = new BinaryThresholdNeuron(inputs.length, 0.0)
    println(neuron.output(inputs))
    val neuron2 = new BinaryThresholdNeuron(inputs.length, -1.0)
    println(neuron2.output(inputs))
  }

}
