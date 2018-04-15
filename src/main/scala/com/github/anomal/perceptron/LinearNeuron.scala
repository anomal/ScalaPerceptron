package com.github.anomal.perceptron

import scala.util.{Try, Success, Failure}

/**
  * Represents a linear neuron
  * @param numInputConnections number of input connections to the neuron, excluding the bias input connection
  * @param bias value of the bias input connection with a weight of 1
  */
class LinearNeuron (var numInputConnections:Int, var bias:Double) {

  private var weights:Seq[Double] = Seq.fill(numInputConnections)(1.0)

  /**
    * Represents the axon of the neuron, but outputs a real number;
    * the sum over the (input * weight) for each element of the inputs and corresponding weights, plus the bias
    * @param inputs input values for each connection to the neuron; the size of the input values must be equal to
    *               the number of input connections
    * @return the neuron's activation as a real number
    */
  def output(inputs:Seq[Double]): Try[Double] = {
    if (inputs.lengthCompare(numInputConnections) != 0) {
      Failure(new IllegalArgumentException(s"length of inputs must be $numInputConnections"))
    } else {
      Success((inputs, weights).zipped.map(_ * _).sum + bias)
    }
  }

  /**
    * Adjust the weights of the input connections to the neuron.
    * @param adjustments weight adjustments; the number of adjustments must equal the number of input connections
    */
  def adjustWeights(adjustments:Seq[Double]) = {
    if (adjustments.lengthCompare(numInputConnections) != 0) {
      throw new IllegalArgumentException(s"length of adjustments must be $numInputConnections")
    } else {
      weights = (weights, adjustments).zipped.map(_ + _)
    }
  }

  /**
    * View the weights of the input connections
    * @return weights of the input connections
    */
  def getWeights():Seq[Double] = for (e <- weights) yield e

}

