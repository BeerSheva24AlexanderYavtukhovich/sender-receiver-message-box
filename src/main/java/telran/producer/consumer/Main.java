package telran.producer.consumer;

public class Main {
    private static final int N_MESSAGES = 40;
    private static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox evenMessageBox = new SimpleMessageBox();
        MessageBox oddMessageBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, evenMessageBox, oddMessageBox);

        for (int i = 0; i < N_RECEIVERS; i++) {
            new Receiver((i % 2 == 0) ? oddMessageBox : evenMessageBox).start();
        }

        sender.start();
        sender.join();

        Thread.sleep(100);
    }
}
