package com.github.anomal.perceptron

import scala.util.{Try, Success, Failure}

/**
  * Represents a linear neuron
  * @param weights weights of the neuron
  * @param bias value of the bias input connection with a weight of 1
  */
class LinearNeuron (val weights:Seq[Double], var bias:Double) {

  /**
    * Represents the axon of the neuron, but outputs a real number;
    * the sum over the (input * weight) for each element of the inputs and corresponding weights, plus the bias
    * @param inputs input values for each connection to the neuron; the size of the input values must be equal to
    *               the number of weights
    * @return the neuron's activation
    */
  def output(inputs:Seq[Double]): Try[Double] = {
    if (inputs.lengthCompare(weights.length) != 0) {
      Failure(new IllegalArgumentException(s"length of inputs must be ${weights.length}"))
    } else {
      Success((inputs, weights).zipped.map(_ * _).sum + bias)
    }
  }

}

