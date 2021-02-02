package PureFunctions

// https://medium.com/better-programming/what-is-a-pure-function-3b4af9352f6f ( for learning)
case class Item (var  name : String , var  price : Int)


case class Order(var items : List[Item] , var totalPrice : Int)

object Main extends App{
  // items -> order
  def buyItems(orderItems : List[Item]) : Order = {
    var totalPrice : Int = orderItems.map(_.price).sum;
    return Order(orderItems , totalPrice)
  }

  var order : Order = buyItems(List(Item(name = "item1" , price = 10 ) , Item(name = "item2" , price = 16 )))

}
