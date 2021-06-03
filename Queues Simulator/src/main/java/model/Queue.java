package model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingTime;
    private int queueId;
    private static int i = 1;
    private boolean open;
    private static boolean running = true;

    public Queue(int numberOfClients) {
        this.clients = new ArrayBlockingQueue<>(numberOfClients);
        this.waitingTime = new AtomicInteger();
        this.waitingTime.set(0);
        this.queueId = Queue.getI();
        Queue.setI(Queue.getI() + 1);
        this.open = false;
    }

    public void addClient(Client newClient) {
        this.open = true;
        this.clients.add(newClient);
        this.waitingTime.set(newClient.getTService() + this.getWaitingTime().get());
    }

    @Override
    public void run() {
        while (running) {
            while (clients.peek() != null) {
                try {
                    int t = clients.peek().getTService();
                    Thread.sleep(1000);
                    waitingTime.decrementAndGet();
                    t = t - 1;
                    clients.peek().setTService(t);
                    if (t == 0) {
                        clients.poll();
                    }
                    if (clients.isEmpty()) {
                        waitingTime.set(0);
                        this.open = false;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void stopRunningThreads() {
        running = false;
    }

    public String toString() {
        String c = "";
        if (!this.open) {
            c = "closed";
        } else {
            for (Client client : this.clients) {
                c = c + client + " ";
            }
        }
        return c;
    }

    public void setClients(BlockingQueue<Client> clients) {
        this.clients = clients;
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public void setWaitingTime(AtomicInteger waitingTime) {
        this.waitingTime = waitingTime;
    }

    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public int getQueueId() {
        return queueId;
    }

    public static void setI(int i) {
        Queue.i = i;
    }

    public static int getI() {
        return i;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }
}
