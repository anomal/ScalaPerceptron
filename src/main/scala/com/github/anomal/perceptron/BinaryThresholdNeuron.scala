package com.github.anomal.perceptron

class BinaryThresholdNeuron (var numInputs:Int, var threshold:Double) {

  private val linearNeuron = new LinearNeuron(numInputs, -1 * threshold)

  def output(inputs:Seq[Double]) : Int = {
    if (linearNeuron.output(inputs) >= 0) {
      1
    } else {
      0
    }
  }

  def adjustWeights(adjustments:Seq[Double]) = {
    linearNeuron.adjustWeights(adjustments)
  }
}
