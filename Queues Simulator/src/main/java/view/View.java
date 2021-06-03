package view;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JPanel contentPane;
    private JTextField clients;
    private JTextField queues;
    private JTextField interval;
    private JTextField minA;
    private JTextField maxA;
    private JTextField minS;
    private JTextField maxS;
    private static JTextArea output;
    private JButton start;
    private JButton stop;
    private static JLabel aWait;
    private static JLabel aServ;
    private static JLabel peakH;

    public View() {
        setTitle("Queues Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 500);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        JPanel panel11 = new JPanel();
        panel11.setLayout(new FlowLayout());
        JLabel lblNewLabel = new JLabel("Number of clients: ");
        panel11.add(lblNewLabel);
        clients = new JTextField(5);
        panel11.add(clients);
        panel1.add(panel11);

        JPanel panel12 = new JPanel();
        panel11.setLayout(new FlowLayout());
        JLabel lblNewLabel_1 = new JLabel("Number of queues: ");
        panel12.add(lblNewLabel_1);
        queues = new JTextField(5);
        panel12.add(queues);
        panel1.add(panel12);

        JPanel panel13 = new JPanel();
        panel11.setLayout(new FlowLayout());
        JLabel lblNewLabel_2 = new JLabel("Simulation interval: ");
        panel13.add(lblNewLabel_2);
        interval = new JTextField(5);
        panel13.add(interval);
        panel1.add(panel13);

        JPanel panel14 = new JPanel();
        panel11.setLayout(new FlowLayout());
        JLabel lblNewLabel_3 = new JLabel("Minimum arrival time: ");
        panel14.add(lblNewLabel_3);
        minA = new JTextField(5);
        panel14.add(minA);
        panel1.add(panel14);

        JPanel panel15 = new JPanel();
        panel11.setLayout(new FlowLayout());
        JLabel lblNewLabel_4 = new JLabel("Maximum arrival time: ");
        panel15.add(lblNewLabel_4);
        maxA = new JTextField(5);
        panel15.add(maxA);
        panel1.add(panel15);

        JPanel panel16 = new JPanel();
        panel11.setLayout(new FlowLayout());
        JLabel lblNewLabel_5 = new JLabel("Minimum service time: ");
        panel16.add(lblNewLabel_5);
        minS = new JTextField(5);
        panel16.add(minS);
        panel1.add(panel16);

        JPanel panel17 = new JPanel();
        panel11.setLayout(new FlowLayout());
        JLabel lblNewLabel_6 = new JLabel("Maximum service time: ");
        panel17.add(lblNewLabel_6);
        maxS = new JTextField(5);
        panel17.add(maxS);
        panel1.add(panel17);

        output = new JTextArea(15, 40);
        JScrollPane sp = new JScrollPane(output);
        panel1.add(sp);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        start = new JButton("START");
        panel2.add(start);
        panel2.add(new JLabel(" "));
        panel2.add(new JLabel(" "));
        JLabel lblNewLabel_7 = new JLabel("Average waiting time: ");
        panel2.add(lblNewLabel_7);
        aWait = new JLabel(" ");
        panel2.add(aWait);
        JLabel lblNewLabel_8 = new JLabel("Average service time: ");
        panel2.add(lblNewLabel_8);
        aServ = new JLabel(" ");
        panel2.add(aServ);
        JLabel lblNewLabel_9 = new JLabel("Peak hour: ");
        panel2.add(lblNewLabel_9);
        peakH = new JLabel(" ");
        panel2.add(peakH);

        contentPane.add(panel1);
        contentPane.add(panel2);
    }

    public void addStartBtnListener(ActionListener actionListener) {
        this.start.addActionListener(actionListener);
    }

    public String getNumberOfClients() {
        return this.clients.getText();
    }

    public String getNumberOfQueues() {
        return this.queues.getText();
    }

    public String getSimulationInterval() {
        return this.interval.getText();
    }

    public String getMinArrrival() {
        return this.minA.getText();
    }

    public String getMaxArrival() {
        return this.maxA.getText();
    }

    public String getMinService() {
        return this.minS.getText();
    }

    public String getMaxService() {
        return this.maxS.getText();
    }

    public static void setOutput(String result) {
        output.setText(output.getText() + result);
    }

    public static void setAverageWait(String result) {
        aWait.setText(result);
    }

    public static void setAverageService(String result) {
        aServ.setText(result);
    }

    public static void setPeakHour(String result) {
        peakH.setText(result);
    }

}
