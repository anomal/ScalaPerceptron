package com.github.anomal.perceptron

/**
  * Perceptron neural network
  * @param numInputs number of inputs to the perceptron; input vector size
  * @param threshold threshold to decide that the input vector is a positive example of the target class
  */
class Perceptron (var numInputs:Int, var threshold:Double) {

  private val decisionUnit = new BinaryThresholdNeuron(numInputs, threshold)

  /**
    * Train the perceptron, teaching it that the given inputs should produce the expected output answer
    * @param inputs input values
    * @param answer correct answer; must be either 1 (positive example of target class) or 0 (negative example)
    */
  def train(inputs:Seq[Double], answer:Int) = {
    if (answer != 1 && answer != 0) {
      throw new IllegalArgumentException("answer must be 0 or 1")
    } else {
      val output = classify(inputs)
      if (output != answer) {
        output match {
          case 0 => decisionUnit.adjustWeights(inputs)
          case 1 => decisionUnit.adjustWeights(inputs.map(-1 * _))
        }
      }
    }
  }

  /**
    * Classify the given inputs as a positive example of the target class (1) or not (0)
    * @param inputs input vector
    * @return 1 if it is a positive example of the target class; otherwise, 0
    */
  def classify(inputs:Seq[Double]) = {
    decisionUnit.output(inputs)
  }

  /**
    * Get the learned weights
    * @return the learned weights
    */
  def getWeights(): Seq[Double] = decisionUnit.getWeights

}
