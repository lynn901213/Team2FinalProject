package CSE360;
/*
CSE360 Summer 2017
Kyle Sun
Jingyi Li
Lin Sun
*/
import javax.swing.*;
import java.io.*;
import java.util.Random;

public class CompanionPanel extends JLabel implements Runnable {

/** The ghost moves and bounces around the screen.*/
   public int x;
   public int y;
   private int hspeed;
   private int vspeed;
   private int frameHeight;
   private int frameWidth;
   private JLabel companion;
   Random r;   
    /** Create a Ghost with the provided file paths for the ghost image moving
    right and left.*/
    public CompanionPanel(java.lang.String img, int width, int height) throws IOException {
       companion = new JLabel(new ImageIcon(img));
       add(companion);
       frameWidth = width;
       frameHeight = height;
       r = new Random();
       x = r.nextInt(frameWidth - 96) + 48;
       y = r.nextInt(frameHeight - 96) + 48;
       hspeed = r.nextInt(3) + 1;
       vspeed = r.nextInt(3) + 1;
    }
    /** Set the bounds of the frame for the ghost to bounce off of. If bounds
    * are set past visibility, ghost is moved within the frame.
    */
    public void setFrame(int n, int j) {
       frameWidth = n;
       frameHeight = j;
       if (x > frameWidth) {
          x = frameWidth - 33;
       }
       if (y > frameHeight) {
          y = frameHeight - 33;
       }
    }

    /** Move the ghost around the screen and bounces it off the frame boundaries. */
    @Override
    public void run() {
       while(true) {
          try {
             Thread.sleep(25);
          } catch (InterruptedException i) {
             java.lang.System.exit(1);
          }
          drawCompanion();
          x += hspeed;
          y += vspeed;
          if (x < 1 || x + 33 > frameWidth) {
             hspeed *= -1;
          }
          if (y < 1 || y + 33 > frameHeight) {
             vspeed *= -1;
          }
       }
    }
    
    public void setImage(String img) {
        companion.setIcon(new ImageIcon(img));
    }

    public void drawCompanion() {
        companion.setBounds(x, y, 32, 32);
    }

    public void drawMessage() {

    }

}
