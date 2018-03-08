package com.github.anomal.perceptron

/**
  * Perceptron neural network.
  * The standard perceptron architecture actually consists of input units connected to hand-coded feature units,
  * which in turn feed into the decision unit. For the purposes of the machine learning, the output of
  * the feature units can be referred to as "input units" relative to the decision unit.
  * @param numIncomingConnections number of incoming connections
  * @param threshold threshold to decide if the values of the incoming connections represent a positive example of the
  *                  target class
  */
class Perceptron (var numIncomingConnections:Int, var threshold:Double) {

  private val decisionUnit = new BinaryThresholdNeuron(numIncomingConnections, threshold)

  /**
    * number of incoming connections
    * @param numIncomingConnections
    */
  def this(numIncomingConnections:Int){
    this(numIncomingConnections, 0)
  }

  /**
    * Train the perceptron, teaching it that the values of the incoming connections provided should produce
    * the expected output answer
    * @param incomingConnectionValues values of incoming connections
    * @param answer correct answer; must be either 1 (positive example of target class) or 0 (negative example)
    */
  def train(incomingConnectionValues:Seq[Double], answer:Int) = {
    if (answer != 1 && answer != 0) {
      throw new IllegalArgumentException("answer must be 0 or 1")
    } else {
      val output = classify(incomingConnectionValues)
      /* Perceptron convergence procedure (as per Hinton et al. in Neural Networks For Machine Learning (Coursera) lecture 2):
      –  If the output unit is correct, leave its weights alone.
      –  If the output unit incorrectly outputs a zero, add the input vector to the weight vector.
      –  If the output unit incorrectly outputs a 1, subtract the input vector from the weight vector.
       */
      if (output != answer) {
        output match {
          case 0 => decisionUnit.adjustWeights(incomingConnectionValues)
          case 1 => decisionUnit.adjustWeights(incomingConnectionValues.map(-1 * _))
        }
      }
    }
  }

  /**
    * Classify the values of the incoming connections as a positive example of the target class (1) or not (0)
    * @param incomingConnectionValues values of incoming connections
    * @return 1 if the provided values of the incoming connections is a positive example of the target class;
    *         otherwise, returns 0
    */
  def classify(incomingConnectionValues:Seq[Double]) : Int = {
    decisionUnit.output(incomingConnectionValues)
  }

  /**
    * Get the learned weights
    * @return the learned weights
    */
  def getWeights(): Seq[Double] = decisionUnit.getWeights

}
