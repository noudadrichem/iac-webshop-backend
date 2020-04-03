package hu.iac.webshop.dto.product;


import java.util.List;

public class CheckoutRequest {

    private String paymentMethod;
    private AddressRequest addressRequest;
    private long orderId;
    private long customerId;


//    private List<ProductsCheckoutRequest> productsCheckoutRequests;
    public CheckoutRequest() {

    }

    public CheckoutRequest(String paymentMethod, AddressRequest addressRequest) {
        this.paymentMethod = paymentMethod;
        this.addressRequest = addressRequest;

    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public AddressRequest getAddressRequest() {
        return addressRequest;
    }

    public void setAddressRequest(AddressRequest addressRequest) {
        this.addressRequest = addressRequest;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    //    public List<ProductsCheckoutRequest> getProductsCheckoutRequests() {
//        return productsCheckoutRequests;
//    }
//
//    public void setProductsCheckoutRequests(List<ProductsCheckoutRequest> productsCheckoutRequests) {
//        this.productsCheckoutRequests = productsCheckoutRequests;
//    }
}
