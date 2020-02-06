public class ChatRequest {

    public enum RequestType {
        showOnlineUsers,
        sendMessage,

    }

    String sender;
    String receiver;
    String content;
    RequestType requestType;
    

    public ChatRequest(String sender, String receiver, String content, RequestType requestType) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.requestType = requestType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
