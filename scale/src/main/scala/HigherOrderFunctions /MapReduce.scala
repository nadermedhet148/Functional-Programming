package HigherOrderFunctions

object MapReduce extends App {

  val collection = List(1, 5, 7, 8).map(x => (x,1))
  val res = collection.reduce( (a,b) => ( a._1 + b._1,
    a._2 + b._2 ) )
  println(res)

}
