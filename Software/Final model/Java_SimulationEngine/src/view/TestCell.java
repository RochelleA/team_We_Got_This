package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.math.*;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestCell extends JPanel
{
    private int[] sizeArray = new int [5];  // radius of each circle
    private int[] xArray = new int [5]; //array to store x coordinates of circles
    private int[] yArray = new int [5]; //array to store y coordinates of circles
    private int x1, x2, y1, y2;
    private boolean overlap = false;


    public TestCell()
    {
        Random r = new Random();

        for (int i = 0; i<xArray.length; i++){
            //random numbers from 1 to 20;
            xArray[i] = r.nextInt(200) + 1;
        }
        for (int i = 0; i<yArray.length; i++){
            yArray[i] = r.nextInt(200) + 1;
        }
        for (int i = 0; i<sizeArray.length; i++){
            sizeArray[i] = r.nextInt(100) +1;
        }

        setBackground (Color.black);
        setPreferredSize (new Dimension(300, 200));
    }
    //  generates all of the circles stored in the array.

    public void paintComponent (Graphics page)
    {
        super.paintComponent(page);
        for (int i = 0 ;i<xArray.length; i++) //this is an iterator that draws the circles and checks for overlapping radii
            for (int j = 0 ;j<xArray.length; j++)
            {
                //boolean overlap = false;
                //is supposed to compare radii of circles to check if they overlap
                {
                    if (Math.sqrt((xArray[i]-xArray[j])*(xArray[i]-xArray[j])+(yArray[i]-yArray[j])*(yArray[i]-yArray[j])) >sizeArray[i]-sizeArray[j]);

                    boolean overlap = true;
                    page.fillOval(xArray[i], yArray[i], sizeArray[i], sizeArray[i]);
                    page.setColor (Color.cyan);
                    repaint();


                } //draws the circles that are stored in the array
                page.drawOval(xArray[i], yArray[i], sizeArray[i], sizeArray[i]);//outer for loop
            }
    }
    public static void main (String[] args)
    {
        JFrame frame = new JFrame ("Circles");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add (new TestCell());

        frame.pack();
        frame.setVisible(true);
    }
}