# ScalaPerceptron
A simple core Scala implementation of a Perceptron neural network. There are only three classes:

1. `Perceptron.scala`, which has the perceptron learning algorithm, and uses
2. `BinaryThresholdNeuron.scala`, which is a neuron that outputs either 0 or 1, and is a wrapper around
3. `LinearNeuron.scala`, which implements the neural activation and weight adjustment formulas.

Perceptrons can only learn to differentiate between classes that are linearly separable.

To use the library,

    val numInputs = 3
    val perceptron = new Perceptron(numInputs)
    val inputsGood = Seq(1.1,2.2,3.3)
    perceptron.train(inputsGood,1)
    val inputsBad = Seq(-1.1,-2.2,-3.3)
    perceptron.train(inputsBad,0)
    // more training ...

    // test perceptron's learning
    val inputs = Seq(3.3,2.2,1.1)
    println(perceptron.classify(inputs)) // either 0 or 1

The unit test class `PerceptronTest.scala` shows a working example.

