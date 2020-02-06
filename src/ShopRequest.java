package Requests;

public class ShopRequest {

    private String username;
    private RequestType requestType;

    public String getUsername() {
        return username;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public enum RequestType {
        showShop,
        showCollection,
        showMoney,
        moneyCheat,

    }

    public ShopRequest(String username, RequestType requestType) {
        this.username = username;
        this.requestType = requestType;
    }

}