package telran.producer.consumer;

public class Main {
    private static final int N_MESSAGES = 20;
    private static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox evenMessageBox = new SimpleMessageBox();
        MessageBox oddMessageBox = new SimpleMessageBox();
        Sender sender = new Sender(N_MESSAGES, evenMessageBox, oddMessageBox);
        Receiver[] receivers = new Receiver[N_RECEIVERS];

        for (int i = 0; i < N_RECEIVERS; i++) {
            receivers[i] = new Receiver(null);
            if (isReceiverEven(receivers[i])) {
                receivers[i].setMessageBox(evenMessageBox);
            } else {
                receivers[i].setMessageBox(oddMessageBox);
            }
        }

        for (Receiver receiver : receivers) {
            receiver.start();
        }

        sender.start();
        sender.join();

        Thread.sleep(100);
    }

    private static boolean isReceiverEven(Receiver receiver) {
        return Integer.parseInt(receiver.getName().split("-")[1]) % 2 == 0;
    }
}
