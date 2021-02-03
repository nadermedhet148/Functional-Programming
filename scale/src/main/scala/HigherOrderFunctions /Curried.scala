package HigherOrderFunctions

object Curried extends  App {

  val adderGenerator = (a: Int) => (b: Int)  => a + b ;
  val add2 = adderGenerator(2)
  val add10 = adderGenerator(10)
}
