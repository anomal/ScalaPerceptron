package com.github.anomal.perceptron

import scala.util.{Try, Success, Failure}


/**
  * Perceptron neural network.
  * The standard perceptron architecture actually consists of input units connected to hand-coded feature units,
  * which in turn feed into the decision unit. For the purposes of machine learning, the output of
  * the feature units can be referred to as "input units" relative to the decision unit.
  * @param numIncomingConnections number of incoming connections
  * @param threshold threshold to decide if the values of the incoming connections represent a positive example of the
  *                  target class
  */
class Perceptron {

  /**
    * Train the perceptron, teaching it that the values of the incoming connections provided should produce
    * the expected output answer
    * @param incomingConnectionValues values of incoming connections
    * @param answer correct answer; must be either 1 (positive example of target class) or 0 (negative example)
    */
  def train(decisionUnit: BinaryThresholdNeuron, incomingConnectionValues:Seq[Double], answer:Int): Try[BinaryThresholdNeuron] = {
    if (answer != 1 && answer != 0) {
      Failure(new IllegalArgumentException("answer must be 0 or 1"))
    } else {
        /* Perceptron convergence procedure (as per Hinton et al. in Neural Networks For Machine Learning (Coursera) lecture 2):
        –  If the output unit is correct, leave its weights alone.
        –  If the output unit incorrectly outputs a zero, add the input vector to the weight vector.
        –  If the output unit incorrectly outputs a 1, subtract the input vector from the weight vector.
         */
        decisionUnit.output(incomingConnectionValues) match {
          case Success(output) =>
              if (output != answer) {
                val adjustedWeights = output match {
                  case 0 => adjustWeights(decisionUnit.weights, incomingConnectionValues)
                  case 1 => adjustWeights(decisionUnit.weights, incomingConnectionValues.map(-1 * _))
                }
                adjustedWeights match {
                  case Success(w) => Success(new BinaryThresholdNeuron(w, decisionUnit.threshold))
                  case Failure(ex) => Failure(ex)
                }
              } else {
                Success(decisionUnit)
              }
          case Failure(e) => Failure(e)
        }
    }
  }


  /**
    * Adjust the weights of the input connections to the neuron.
    * @param adjustments weight adjustments; the number of adjustments must equal the number of input connections
    */
  def adjustWeights(weights:Seq[Double], adjustments:Seq[Double]): Try[Seq[Double]] = {
    if (adjustments.lengthCompare(weights.length) != 0) {
      Failure(new IllegalArgumentException("length of adjustments must be equal to length of weights"))
    } else {
      Success((weights, adjustments).zipped.map(_ + _))
    }
  }

}
