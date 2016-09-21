/**
 * Created by WesleyLewis on 9/20/16.
 */
public class Message {
    String messages;
    public Message(String messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return String.format("%s\n", messages);
    }
}
