public class Message2 {

    String receiver;
    String sender;
    String content;

    public Message2(String receiver, String sender, String content) {
        this.receiver = receiver;
        this.sender = sender;
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


class Message {

    private Account sender;
    private Account receiver;
    private String content;

    public Message(Account sender, Account receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}