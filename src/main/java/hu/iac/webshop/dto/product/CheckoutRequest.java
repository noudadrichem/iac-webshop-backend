package hu.iac.webshop.dto.product;


import java.util.List;

public class CheckoutRequest {

    private String paymentMethod;
    private CustomerRequest customerRequest;
    private AddressRequest addressRequest;
    private List<ProductsCheckoutRequest> productsCheckoutRequests;


    public CheckoutRequest(String paymentMethod, CustomerRequest customerRequest, AddressRequest addressRequest, List<ProductsCheckoutRequest> productsCheckoutRequests) {
        this.paymentMethod = paymentMethod;
        this.customerRequest = customerRequest;
        this.addressRequest = addressRequest;
        this.productsCheckoutRequests = productsCheckoutRequests;
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

    public CustomerRequest getCustomerRequest() {
        return customerRequest;
    }

    public void setCustomerRequest(CustomerRequest customerRequest) {
        this.customerRequest = customerRequest;
    }

    public List<ProductsCheckoutRequest> getProductsCheckoutRequests() {
        return productsCheckoutRequests;
    }

    public void setProductsCheckoutRequests(List<ProductsCheckoutRequest> productsCheckoutRequests) {
        this.productsCheckoutRequests = productsCheckoutRequests;
    }
}
