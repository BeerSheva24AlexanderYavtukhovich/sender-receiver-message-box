package telran.producer.consumer;

public class Receiver extends Thread {
    private int id;
    private MessageBox messageBox;

    public Receiver(int id, MessageBox messageBox) {
        this.id = id;
        this.messageBox = messageBox;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String message = messageBox.take();
                if (id % 2 == getMessageNumber(message) % 2) {
                    System.out.printf("Thread: %s, message: %s\n", getName(), message);
                } else {
                    messageBox.put(message);
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    private static int getMessageNumber(String message) {
        return Integer.parseInt(message.replaceAll("\\D+", ""));
    }
}
