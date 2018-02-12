package com.github.anomal.perceptron

class Perceptron (var numInputs:Int, var threshold:Double) {

  private val decisionUnit = new BinaryThresholdNeuron(numInputs, threshold)

  def train(inputs:Seq[Double], answer:Int) = {
    val output = classify(inputs)
    if (output != answer) {
      output match {
        case 0 => decisionUnit.adjustWeights(inputs)
        case 1 => decisionUnit.adjustWeights(inputs.map(-1 * _))
      }
    }
  }

  def classify(inputs:Seq[Double]) = {
    decisionUnit.output(inputs)
  }

  def getWeights(): Seq[Double] = decisionUnit.getWeights

}
