package model;

import view.View;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class SimulationManager implements Runnable {
    public int simulationInterval;
    public int numberOfClients;
    public int numberOfQueues;
    public int tMinArrival;
    public int tMaxArrival;
    public int tMinService;
    public int tMaxService;
    public double averageWaiting = 0;
    public double averageService;
    public int peakHour = 0;
    public int pk = 0;
    private final Scheduler scheduler;
    private final ArrayList<Client> generatedClients;

    public SimulationManager(int simI, int clients, int queues, int tMinA, int tMaxA, int tMinS, int tMaxS) {
        this.simulationInterval = simI;
        this.numberOfClients = clients;
        this.numberOfQueues = queues;
        this.tMinArrival = tMinA;
        this.tMaxArrival = tMaxA;
        this.tMinService = tMinS;
        this.tMaxService = tMaxS;
        this.scheduler = new Scheduler(this.numberOfQueues, this.numberOfClients);
        this.generatedClients = new ArrayList<>();
        this.generateNRandomClients();
    }

    public void generateNRandomClients() {
        for (int i = 0; i < this.numberOfClients; i++) {
            Client newClient = new Client(this.tMinArrival, this.tMaxArrival, this.tMinService, this.tMaxService);
            this.generatedClients.add(newClient);
        }
        Collections.sort(this.generatedClients);
        double s = 0;
        for (Client c : this.generatedClients)
            s = s + c.getTService();
        this.averageService = s / this.numberOfClients;
    }

    @Override
    public void run() {
        int currentTime = 0, c = 0;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/resources/output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (currentTime <= this.simulationInterval) {
            while (!this.generatedClients.isEmpty() && this.generatedClients.get(0).getTArrival() == currentTime) {
                this.scheduler.dispatchClient(this.generatedClients.get(0));
                this.generatedClients.remove(0);
            }
            int p = 0;
            for (Queue q : scheduler.getQueues()) {
                p = p + q.getClients().size();
                averageWaiting += q.getWaitingTime().doubleValue();
            }
            c = c + p;
            if (p > this.pk) {
                this.pk = p;
                this.peakHour = currentTime;
            }
            View.setOutput(this.tooString(currentTime));
            try {
                fileWriter.write(this.tooString(currentTime));
            } catch (IOException e) {
                e.printStackTrace();
            }
            currentTime++;
            if (this.generatedClients.isEmpty() && scheduler.areAllQueuesClosed()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Queue.stopRunningThreads();
        View.setAverageWait(String.valueOf(Math.round((averageWaiting / c) * 100.0) / 100.0));
        View.setAverageService(String.valueOf(this.averageService));
        View.setPeakHour(String.valueOf(this.peakHour));
        try {
            fileWriter.write("Average waiting time: " + Math.round((averageWaiting / c) * 100.0) / 100.0 + "\n");
            fileWriter.write("Average service time: " + this.averageService + "\n");
            fileWriter.write("Peak hour: " + this.peakHour + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String tooString(int currentTime) {
        String s = "Time: " + currentTime + "\n";
        s = s + "Waiting clients: ";
        ArrayList<Client> g = new ArrayList<>();
        int id = 1;
        while (id <= this.numberOfClients) {
            for (Client cl : this.generatedClients) {
                if (cl.getId() == id) {
                    g.add(cl);
                }
            }
            id++;
        }
        for (Client c : g) {
            s = s + c.toString() + " ";
        }
        s = s + "\n" + scheduler.toString();
        return s;
    }

}
