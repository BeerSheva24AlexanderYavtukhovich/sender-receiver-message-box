package telran.producer.consumer;

public class Sender extends Thread {
    private int nMessages;
    private MessageBox evenMessageBox;
    private MessageBox oddMessageBox;

    public Sender(int nMessages, MessageBox evenMessageBox, MessageBox oddMessageBox) {
        this.nMessages = nMessages;
        this.evenMessageBox = evenMessageBox;
        this.oddMessageBox = oddMessageBox;
    }

    @Override
    public void run() {
        for (int i = 0; i < nMessages; i++) {
            try {
                putMessageInMessageBox((i + 1) % 2 == 0 ? evenMessageBox : oddMessageBox, i);
            } catch (InterruptedException e) {
            }
        }
    }

    private void putMessageInMessageBox(MessageBox messageBox, int i) throws InterruptedException {
        messageBox.put("Message" + (i + 1));
    }
}
