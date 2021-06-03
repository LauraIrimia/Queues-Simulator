package model;

import java.util.Random;

public class Client implements Comparable<Client> {
    private int id;
    private static int i = 1;
    private int tArrival;
    private int tService;

    public Client(int tMinArrival, int tMaxArrival, int tMinService, int tMaxService) {
        this.setId(Client.getI());
        Client.setI(Client.getI() + 1);
        Random r = new Random();
        this.setTArrival(r.nextInt((tMaxArrival - tMinArrival) + 1) + tMinArrival);
        this.setTService(r.nextInt((tMaxService - tMinService) + 1) + tMinService);
    }

    @Override
    public int compareTo(Client otherClient) {
        return Integer.compare(this.tArrival, otherClient.tArrival);
    }

    public String toString() {
        return "(" + getId() + ", " + this.getTArrival() + ", " + this.getTService() + ");";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        Client.i = i;
    }

    public int getTArrival() {
        return tArrival;
    }

    public void setTArrival(int tArrival) {
        this.tArrival = tArrival;
    }

    public int getTService() {
        return tService;
    }

    public void setTService(int tService) {
        this.tService = tService;
    }

}
