package project;

import javax.swing.*;
import java.awt.*;

class ClockAnalogPanel extends JPanel {

    private ClockMainFrame clockMainFrame;
    private int xc,yc;

    ClockAnalogPanel(ClockMainFrame clockMainFrame) {
        this.clockMainFrame = clockMainFrame;
    }

    /*==== 2nd method, instead of using the canvas ( we saw it fel cours) ====*/
    public void paintComponent (Graphics g) {
        super.paintComponent(g);

        /*==== draw the two circle frames ====*/
        //g.setColor(Color.BLACK);
        g.drawOval(30,40,this.getWidth()-50,this.getWidth()-50);
        //g.setColor(Color.DARK_GRAY);
        g.drawOval(40,50,this.getWidth()-70,this.getWidth()-70);

        /*  xc -> center of the x-axis
            yc -> center of the y-axis
         */
        this.xc = getWidth()/2;
        this.yc = getHeight()/2;
        int radius=Math.min(this.xc,this.yc)*80/100;

        this.clockMainFrame.setFont(new Font("Times New Roman",0,15));
        g.setFont(this.clockMainFrame.getFont());

        /*==== Put the hour numbers on the clock in a circular way ====*/
        for(int i=1;i<=12;i++)
        {
            double angle=i*Math.PI/6.0-Math.PI/2.0;
            double x=this.xc+radius*Math.cos(angle);
            double y=this.yc+radius*Math.sin(angle);
            g.drawString(" "+i,(int)x,(int)y);
        }

        /*==== Seconds needle ====*/
        double secAngle=(this.clockMainFrame.getSec()*((Math.PI)/30.0)-(Math.PI/2.0));
        int xsf=this.xc+(int)(0.7*radius*Math.cos(secAngle));
        int ysf=this.yc+(int)(0.7*radius*Math.sin(secAngle));
        g.setColor(Color.RED);
        g.drawLine(this.xc,this.yc,xsf,ysf);

        /*==== Minutes needle ====*/
        double minAngle=(this.clockMainFrame.getMin()*((Math.PI)/30.0)-(Math.PI/2.0));
        int xmf=this.xc+(int)(0.6*radius*Math.cos(minAngle));
        int ymf=this.yc+(int)(0.6*radius*Math.sin(minAngle));
        g.setColor(Color.BLACK);
        g.drawLine(this.xc,this.yc,xmf,ymf);

        /*==== Hours needle ====*/
        double hoursAngle=(this.clockMainFrame.getHour()*((2*Math.PI)/12.0)-(Math.PI/2.0));
        int xhf=this.xc+(int)(0.4*radius*Math.cos(hoursAngle));
        int yhf=this.yc+(int)(0.4*radius*Math.sin(hoursAngle));
        g.setColor(Color.GREEN);
        g.drawLine(this.xc,this.yc,xhf,yhf);
    }
}