package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class ClockMainFrame extends JFrame implements ActionListener {

    private int hour;
    private int min;
    private int sec;
    private int year;
    private int dayOfMonth;
    private String monthh;
    private ClockAnalogPanel clockAnalogPanel;
    private ClockNumericPanel clockNumericPanel;
    private JButton numericalButton = new JButton("Numerical clock");
    private JButton analogButton = new JButton("Analog clock");
    private JPanel buttonsPanel = new JPanel();
    private  GregorianCalendar calendar = new GregorianCalendar();

    public ClockMainFrame() throws ParseException {

        /*============== JFrame (ClockMainFrame class) init ==============*/
        this.setTitle("Clock Project");
        this.setSize(600,700);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        /*============== attributes init ==============*/
        this.hour = calendar.get(GregorianCalendar.HOUR_OF_DAY);
        this.min= calendar.get(GregorianCalendar.MINUTE);
        this.sec= calendar.get(GregorianCalendar.SECOND);
        this.year = calendar.get(GregorianCalendar.YEAR);
        //using the variable month as an intermediate to get the month in string using formatMonth(String string)
        //https://stackoverflow.com/questions/1038570/how-can-i-convert-an-integer-to-localized-month-name-in-java/1038580
        int month = calendar.get(GregorianCalendar.MONTH);
        this.monthh = this.formatMonth(Integer.toString(month + 1));
        this.dayOfMonth = calendar.get(GregorianCalendar.DAY_OF_MONTH);

        /*============== instantiation of my 2 classes : ClockAnalogPanel and ClockNumericPanel ==============*/
        this.clockAnalogPanel =new ClockAnalogPanel(this);
        this.clockNumericPanel = new ClockNumericPanel(this);

        /*============== adding an action listener (this same class) on the buttons ==============*/
        this.numericalButton.addActionListener(this);
        this.analogButton.addActionListener(this);

        /*============== adding the buttons to a panel ==============*/
        this.buttonsPanel.add(numericalButton);
        this.buttonsPanel.add(analogButton);

        /*============== adding the two panels our JFrame class ==============*/
        this.add(buttonsPanel,BorderLayout.NORTH);
        this.add(clockAnalogPanel,BorderLayout.CENTER);

        this.setVisible(true);

        /*============== Declaring and starting our thread (ClockSimpleThread) ==============*/
        ClockSimpleThread clockSimpleThread = new ClockSimpleThread(this);
        clockSimpleThread.start();
    }

    public void increment() {
        sec++;
        if(sec>60) {
            sec=1;
            min++;
            if(min>60) {
                min=1;
                hour++;
                if(hour >12) {
                    hour =1;
                }
            }
        }
        this.clockAnalogPanel.repaint();
        this.clockNumericPanel.updateTime();
    }


    /*============== Failed attempt for updating the attributes, does not work for now ==============*/
    /*public void updateAttributes() throws ParseException {
        this.hour = calendar.get(GregorianCalendar.HOUR_OF_DAY);
        this.min= calendar.get(GregorianCalendar.MINUTE);
        this.sec= calendar.get(GregorianCalendar.SECOND);

        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.monthh = this.formatMonth(Integer.toString(this.month));
        this.dayOfMonth = calendar.get(GregorianCalendar.DAY_OF_MONTH);

        this.clockAnalogPanel.repaint();
        this.clockNumericPanel.updateTime();
    }*/


    /*============== Getters and setters ==============*/
    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }


    public int getSec() {
        return sec;
    }

    public int getYear() {
        return year;
    }


    public int getDayOfMonth() {
        return dayOfMonth;
    }


    public String getMonthh() {
        return monthh;
    }


    /*============== implementing the actionPerformed method to configure what the buttons will do ==============*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.numericalButton) {
            this.remove(this.clockAnalogPanel);
            this.add(this.clockNumericPanel,BorderLayout.CENTER);
        } else if(e.getSource()==this.analogButton) {
            this.remove(this.clockNumericPanel);
            this.add(this.clockAnalogPanel,BorderLayout.CENTER);
        }
    }

    /*============== Method that gets us the Month in String format ==============*/
    public String formatMonth(String month) throws ParseException {
        SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
        return monthDisplay.format(monthParse.parse(month));
    }
}
