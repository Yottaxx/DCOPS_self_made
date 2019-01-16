public class Message {
    public int sender;
    public int reciver;
    public int value;

    public Message()
    {
        sender=-1;
        reciver=-1;
        value=-1;
    }
    public void re_Message(){
        sender=-1;
        reciver=-1;
        value=-1;
    }
    public int getSender()
    {
        return sender;
    }

    public int getReciver() {
        return reciver;
    }

    public int getValue() {
        return value;
    }

    public void setReciver(int reciver) {
        this.reciver = reciver;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }
}

