package controller;

import model.SimulationManager;
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private View view;

    public Controller(View view) {
        this.view = view;
        view.addStartBtnListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int clients, queues, interval, minA, maxA, minS, maxS;
                clients = Integer.parseInt(view.getNumberOfClients());
                queues = Integer.parseInt(view.getNumberOfQueues());
                interval = Integer.parseInt(view.getSimulationInterval());
                minA = Integer.parseInt(view.getMinArrrival());
                maxA = Integer.parseInt(view.getMaxArrival());
                minS = Integer.parseInt(view.getMinService());
                maxS = Integer.parseInt(view.getMaxService());
                SimulationManager gen = new SimulationManager(interval, clients, queues, minA, maxA, minS, maxS);
                Thread t = new Thread(gen);
                t.start();
            }
        });
    }
}
