package Closures

object Main extends App {
  def closure: Int => Int = {
      val free = 5
      val  closure = (in: Int) => free + in
      return  closure
  }
  println(closure(10));
}
