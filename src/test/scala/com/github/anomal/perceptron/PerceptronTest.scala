package com.github.anomal.perceptron

object PerceptronTest {
  def main(args : Array[String]) = {
    var perceptron = new Perceptron(2, 0.0)

    val rand = scala.util.Random
    for (i <- 0 until 100000) {
      val x1 = rand.nextDouble()*10
      val y1 = rand.nextDouble()*10
      //println(x1, y1)
      val y = -x1 + 5

      if (y1 >= y) {
        //println(1)
        perceptron.train(Seq(x1,y1), 1)
      } else {
        //println(0)
        perceptron.train(Seq(x1,y1), 0)
      }
    }

    var numTests = 50
    var numPass = 0
    for (i <- 0 until numTests) {
      val x1 = rand.nextDouble() * 10
      val y1 = rand.nextDouble() * 10
      //println(x1, y1)
      val y = -x1 + 5
      var expected = y1 match {
        case y1 if (y1 >= y) => 1
        case _ => 0
      }

      val actual = perceptron.classify(Seq(x1, y1))

      if (actual == expected) {
        //println("pass")
        numPass += 1
      } else {
        //println("fail")
      }

    }

    println(100.0*numPass/numTests)

    val p = new Perceptron(5, 0)

    for (i <- 0 until 5000) {
      val grades = new Array[Double](5)
      for (i <- 0 until 5) {
        grades(i) = rand.nextInt(101)
      }
      val avg = grades.sum/5.0
      //println(avg)
      if (avg > 80.0) {
        p.train(grades, 1)
      } else {
        p.train(grades, 0)
      }
    }

    var passes2 = 0

    for (i <- 0 until numTests) {
      val grades = new Array[Double](5)
      for (i <- 0 until 5) {
        grades(i) = rand.nextInt(101)
      }
      val avg = grades.sum/5.0
      val expected = avg match {
        case avg if avg > 80 => 1
        case _ => 0
      }
      val actual = p.classify(grades)
      if (actual == expected) {
        passes2 += 1
      }
    }

    println(100.0*passes2/numTests)
  }

}