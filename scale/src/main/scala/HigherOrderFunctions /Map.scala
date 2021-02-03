package HigherOrderFunctions

case class Item (var  name : String , var  price : Int)
case class OrderItem (var  item: Item , var  quantity : Int)
case class Order(var orderItems : List[OrderItem] , var totalPrice : Int)

object Map extends App {


  val getOrderItemTotalPrice = (orderItem : OrderItem) => orderItem.item.price * orderItem.quantity
  var totalPrice  = List(OrderItem(item = Item(name = "item" , price = 10) , quantity = 3)).map(getOrderItemTotalPrice).sum;
  def buyItems( orderItems: List[OrderItem]) : Order = {
    var totalPrice  = orderItems.map(getOrderItemTotalPrice).sum;
    return Order(orderItems , totalPrice)
  }

  List(OrderItem(item = Item(name = "item" , price = 10) , quantity = 3))

  var order : Order = buyItems(List(OrderItem(item = Item(name = "item" , price = 10) , quantity = 3)));

  print(order)


}
