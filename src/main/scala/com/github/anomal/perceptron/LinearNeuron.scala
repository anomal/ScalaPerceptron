package com.github.anomal.perceptron

class LinearNeuron (var numInputs:Int, var bias:Double) {

  private var weights:Seq[Double] = Seq.fill(numInputs)(1.0)

  def output(inputs:Seq[Double]): Double = {
    if (inputs.lengthCompare(numInputs) != 0) {
      throw new IllegalArgumentException("length of inputs must be " + numInputs)
    } else {
      (inputs, weights).zipped.map(_ * _ + bias).sum
    }
  }

  def adjustWeights(adjustments:Seq[Double]) = {
    if (adjustments.lengthCompare(numInputs) != 0) {
      throw new IllegalArgumentException("length of adjustments must be " + numInputs)
    } else {
      weights = (weights, adjustments).zipped.map(_ + _)
    }
  }
}

