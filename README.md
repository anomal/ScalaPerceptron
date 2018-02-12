# ScalaPerceptron
A simple core Scala implementation of a theoretical Perceptron.

To use,
    val numInputs = 3
    val perceptron = new Perceptron(numInputs)
    val inputsGood = Seq(1.1,2.2,3.3)
    perceptron.train(inputsGood,1)
    val inputsBad = Seq(-1.1,-2.2,-3.3)
    perceptron.train(inputsBad,0)
    // more training ...
    // test classification
    val inputs = Seq(3.3,2.2,1.1)
    println(perceptron.classify(inputs)) // either 0 or 1
