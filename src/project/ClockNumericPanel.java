package project;

import javax.swing.*;
import java.awt.*;

public class ClockNumericPanel extends JPanel {

    private ClockMainFrame clockMainFrame;

    private JPanel datePanel = new JPanel();
    private JLabel yearLabel = new JLabel();
    private JLabel monthLabel = new JLabel();
    private JLabel dayLabel = new JLabel();

    private JPanel timePanel = new JPanel();
    private JLabel hoursLabel = new JLabel();
    private JLabel minutesLabel = new JLabel();
    private JLabel secondsLabel = new JLabel();


    public ClockNumericPanel(ClockMainFrame clockMainFrame) {
        this.clockMainFrame = clockMainFrame;
        this.setLayout(new BorderLayout());

        /*============== Date Panel init ==============*/
        Font dateFont = new Font("Verdana", Font.PLAIN, 40);
        this.yearLabel.setText(Integer.toString(this.clockMainFrame.getYear()));
        this.yearLabel.setFont(dateFont);

        this.monthLabel.setText(this.clockMainFrame.getMonthh().toUpperCase());
        this.monthLabel.setFont(dateFont);

        this.dayLabel.setText(Integer.toString(this.clockMainFrame.getDayOfMonth()));
        this.dayLabel.setFont(dateFont);

        this.datePanel.add(dayLabel);
        this.datePanel.add(new JLabel(" "));
        this.datePanel.add(monthLabel);
        this.datePanel.add(new JLabel(" "));
        this.datePanel.add(yearLabel);


        /*============== Time Panel init ==============*/
        Font timeFont = new Font("Verdana", Font.PLAIN, 20);
        this.hoursLabel.setText(Integer.toString(this.clockMainFrame.getHour()));
        this.hoursLabel.setFont(timeFont);

        this.minutesLabel.setText(Integer.toString(this.clockMainFrame.getMin()));
        this.minutesLabel.setFont(timeFont);

        this.secondsLabel.setText(Integer.toString(this.clockMainFrame.getSec()));
        this.secondsLabel.setFont(timeFont);

        this.timePanel.setLayout(new FlowLayout());

        this.timePanel.add(hoursLabel);
        this.timePanel.add(new JLabel(":"));
        this.timePanel.add(minutesLabel);
        this.timePanel.add(new JLabel(":"));
        this.timePanel.add(secondsLabel);


        /*======= Main Panel init (Numeric clock class) =======*/
        this.add(this.datePanel,BorderLayout.NORTH);
        this.add(this.timePanel,BorderLayout.CENTER);
    }


    /*======= my method to update the seconds/minutes/hours labels in real time with the Thread ( clock.increment() calls updateTime() ) =======*/
    public void updateTime() {
        this.hoursLabel.setText(String.format("%02d",this.clockMainFrame.getHour()));
        this.minutesLabel.setText(String.format("%02d",this.clockMainFrame.getMin()));
        this.secondsLabel.setText(String.format("%02d",this.clockMainFrame.getSec()));
    }

}
