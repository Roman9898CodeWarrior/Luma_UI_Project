package pages.shop;

import pages.shop.components.PaymentComponent;
import pages.shop.components.ShippingAddressComponent;

public class OrderDetailsPage {

    public ShippingAddressComponent shippingAddressComponent;
    public PaymentComponent paymentComponent;

    public OrderDetailsPage() {
        shippingAddressComponent = new ShippingAddressComponent();
        paymentComponent = new PaymentComponent();
    }

    public ShippingAddressComponent getShippingAddressComponent() {
        return shippingAddressComponent;
    }

    public PaymentComponent getPaymentComponent() {
        return paymentComponent;
    }
}
