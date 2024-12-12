package telran.producer.consumer;

public class Sender extends Thread {
    private int nMessages;

    public Sender(int nMessages, MessageBox messageBox) {
        this.nMessages = nMessages;
        this.messageBox = messageBox;
    }

    private MessageBox messageBox;

    @Override
    public void run() {
        for (int i = 0; i < nMessages; i++) {
            try {
                messageBox.put("Message" + (i + 1));
            } catch (InterruptedException e) {

            }
        }
    }
}
