import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Setter
@Getter
class Invoice {

    Integer orderId;
    Date createdDate;
    Float totalPrice;
    String description;
    Float shippingFees;

}

@AllArgsConstructor
@Setter
@Getter
class Order {

    Integer orderId;
    Date createdDate;
    String type;
    List<OrderItem> orderItems;

}

@AllArgsConstructor
@Setter
@Getter
class OrderItem {

    Integer productId;
    String productName;
    Float productPrice;
    Integer quantity;

}

@AllArgsConstructor
@Setter
@Getter
class OrdersProcess {

    List<Order> orders;
    Function<Order , Invoice> process;
    String type;


}



public class OrderToInvoice {


    public static Function<OrdersProcess , List<Invoice>> ordersToInvoices = (OrdersProcess ordersProcess) ->
    (List<Invoice>) ordersProcess.orders.stream().
    filter(ele-> ele.type == ordersProcess.type).map(ordersProcess.process).collect(Collectors.toList());



    public static  Function<Order, Invoice> orderToInvoice = (Order order)-> new Invoice(
                order.orderId,
                order.createdDate,
                order.orderItems.stream().reduce( 0.0f , OrderToInvoice.calculateOrderTotalPrice, Float::sum),
                order.orderItems.stream().reduce( "" , OrderToInvoice.generateOrderDescription, String::concat),
                100f
                );


    public static BiFunction<String, OrderItem, String> generateOrderDescription = (content, orderItem) -> content + orderItem.productName + " : " + orderItem.quantity + " * " + orderItem.productPrice + " $\n";

    public static BiFunction<Float, OrderItem, Float> calculateOrderTotalPrice = (Float subTotal , OrderItem orderItem) -> subTotal + (orderItem.productPrice * orderItem.quantity);

    public static  BiFunction<String, HashMap<String , Float>, Function<List<OrderItem>, Float>> getOrderShippingFeesCalculate = (String type, HashMap<String , Float> feesTypes)->{
        Float fees = feesTypes.get(type);
        return  (List<OrderItem> orderItems)-> fees *  orderItems.stream().reduce( 0.0f , OrderToInvoice.calculateOrderTotalPrice, Float::sum);
    } ;









}
