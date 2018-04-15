package com.github.anomal.perceptron

import scala.util.{Try, Success, Failure}

/**
  * Represents a binary threshold neuron or decision unit
  * @param numInputConnections number of input connections to the neuron
  * @param threshold threshold of the neuron's activation to output 1
  */
class BinaryThresholdNeuron (var numInputConnections:Int, var threshold:Double) {

  private val linearNeuron = new LinearNeuron(numInputConnections, -1 * threshold)

  /**
    * Outputs 1 if the sum over the (input * weight) for each element of the inputs and corresponding weights
    * is greater than or equal to the threshold; otherwise, outputs 0
    * @param inputs input values for each connection to the neuron; the size of the input values must be equal to
    *               the number of input connections
    * @return 1 if the the neuron's activation is greater than or equal to the threshold; otherwise, returns 0
    */
  def output(inputs:Seq[Double]) : Try[Int] = {
    linearNeuron.output(inputs) match {
      case Success(v) =>
        Success(
          if (v >= 0) {
            1
          } else {
            0
          }
        )
      case Failure(e) => Failure(e)
    }
  }

  /**
    * Adjust the weights of the input connections to the neuron.
    * @param adjustments weight adjustments; the number of adjustments must equal the number of input connections
    */
  def adjustWeights(adjustments:Seq[Double]) = {
    linearNeuron.adjustWeights(adjustments)
  }

  /**
    * View the weights of the input connections
    * @return weights of the input connections
    */
  def getWeights : Seq[Double] = linearNeuron.getWeights()
}
