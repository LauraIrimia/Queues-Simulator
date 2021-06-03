package model;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Queue> queues;
    private int numberOfQueues;
    private int numberOfClients;
    public Strategy strategy;

    public Scheduler(int numberOfQueues, int numberOfClients) {
        this.numberOfQueues = numberOfQueues;
        this.numberOfClients = numberOfClients;
        this.queues = new ArrayList<Queue>(numberOfQueues);
        this.strategy = new Strategy();
        for (int i = 0; i < this.numberOfQueues; i++) {
            Queue queue = new Queue(numberOfClients);
            this.queues.add(queue);
            Thread tQueue = new Thread(queue);
            tQueue.start();
        }
    }

    public void dispatchClient(Client client) {
        strategy.addClient(this.queues, client);

    }

    public boolean areAllQueuesClosed() {
        int ok = 1;
        for (Queue q : this.queues) {
            if (q.isOpen())
                ok = 0;
        }
        if (ok == 1)
            return true;
        else return false;
    }

    public String toString() {
        String q = "";
        for (Queue qu : this.queues) {
            q = q + "Queue " + qu.getQueueId() + ": " + qu.toString() + "\n";
        }
        return q;
    }

    public void setQueues(ArrayList<Queue> queues) {
        this.queues = queues;
    }

    public ArrayList<Queue> getQueues() {
        return queues;
    }

    public void setNumberOfQueues(int numberOfQueues) {
        this.numberOfQueues = numberOfQueues;
    }

    public int getNumberOfQueues() {
        return numberOfQueues;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }
}
