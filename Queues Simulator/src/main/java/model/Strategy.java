package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Strategy {
    public void addClient(ArrayList<Queue> queues, Client client) {
        AtomicInteger minWaitingQueue = queues.get(0).getWaitingTime();
        int idMinQueue = 1;
        for (Queue q : queues) {
            if (q.getWaitingTime().get() < minWaitingQueue.get()) {
                idMinQueue = q.getQueueId();
                minWaitingQueue = q.getWaitingTime();
            }
        }
        queues.get(idMinQueue - 1).addClient(client);
    }
}
