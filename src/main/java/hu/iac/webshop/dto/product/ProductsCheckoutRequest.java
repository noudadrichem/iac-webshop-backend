package hu.iac.webshop.dto.product;

public class ProductsCheckoutRequest {
    private long productId;
    private int amount;

    ProductsCheckoutRequest(long productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getProductId(){
        return this.productId;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }
}
