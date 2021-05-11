import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderToInvoiceTest {

    @Test
    @DisplayName("should return one invoice for physical order")
    public void testMultiply() {
        List<Order> orders = Arrays.asList(
                new Order(1, new Date() , "physical" ,Arrays.asList(
                        new  OrderItem(1 , "test" , 100f , 2),
                        new  OrderItem(2 , "test2" , 30f , 1)
                )),
                new Order(1, new Date() , "online" ,Arrays.asList(
                        new  OrderItem(3 , "test" , 100f , 2),
                        new  OrderItem(4 , "test2" , 30f , 1)
                ))
        );
        List<Invoice> physicalInvoices =
                OrderToInvoice.ordersToInvoices.apply(
                        new OrdersProcess(orders ,
                        OrderToInvoice.orderToInvoice,
                        "physical"));
        assertEquals(physicalInvoices.size(), 1);
        Invoice invoice = physicalInvoices.get(0);
        assertEquals(invoice.orderId, java.util.Optional.of(1).get());
        assertEquals(invoice.totalPrice, java.util.Optional.of(230f).get());
        assertEquals(invoice.description, java.util.Optional.of("test : 2 * 100.0 $\n" + "test2 : 1 * 30.0 $\n").get());

    }


}