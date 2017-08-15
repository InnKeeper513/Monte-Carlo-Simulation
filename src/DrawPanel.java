import javax.swing.*;
import java.awt.*;

/**
 * Created by jerry on 2017/8/14.
 */
public class DrawPanel extends JPanel {

   static final int COL = 32;
   static final int ROW = 32;
   static final int PIXEL_SIZE = 20;

    final Color NOFILL = Color.WHITE;
    final Color FILL = Color.CYAN;
    final Color BLOCK = Color.BLACK;

    static Color grid[][] = new Color[ROW][COL];

    public DrawPanel(){
        // Initialize all of the blocks
        for(int i = 0; i < COL; i ++){
            for(int j = 0; j < ROW; j++){
                grid[i][j] = BLOCK;
            }
        }

    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,getWidth(),getHeight());

        // Fill in Blocks
        for(int i = 0; i < ROW; i ++){
            for(int j = 0; j < COL; j++){
                g2.setColor(grid[i][j]);
                g2.fillRect(j * PIXEL_SIZE,i*PIXEL_SIZE,PIXEL_SIZE,PIXEL_SIZE);
            }
        }
        // Draw Grid lines
        g2.setColor(Color.BLACK);
        for(int i = 0; i < ROW; i ++){
            for(int j = 0; j < COL; j++){
                g2.drawLine(0, i * PIXEL_SIZE, PIXEL_SIZE * COL, i * PIXEL_SIZE);
            }
            g2.drawLine(i * PIXEL_SIZE, 0, i * PIXEL_SIZE, ROW * PIXEL_SIZE);
        }

        // Grid Outline
        g2.setColor(Color.BLACK);
        g2.drawRect(0,0,COL * PIXEL_SIZE, ROW * PIXEL_SIZE);

    }
}
