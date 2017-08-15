import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;


public class ControlPanel extends JPanel {

    JButton random = new JButton("Random");
    JButton auto = new JButton("Auto");
    JTextField amount = new JTextField("0", 10);

    JButton timing = new JButton("Auto Gene");
    JTextField speed = new JTextField("100", 10);
    JButton clear = new JButton("Clear");


    int grid[][];
    UnionFind uf;
    DrawPanel dp;

    public ControlPanel(DrawPanel draw){
        uf = new UnionFind(dp.ROW * dp.ROW, dp.ROW, dp.COL);
        this.dp = draw;
        // 0 Is BLOCK
        // 1 is FILL
        // 2 is NotFill

        // Initialize the grid to Blocks
        grid = new int[dp.ROW][dp.COL];
        for(int i = 0; i < dp.ROW; i++){
            for(int j = 0; j < dp.COL; j++){
                grid[i][j] = 0;
            }
        }


        ActionListener removeBlock = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double amounts = Double.parseDouble(amount.getText());
                double speeds = Double.parseDouble(speed.getText());

                    if(e.getSource() == timing){
                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                // Your database code here
                                randomFunction(draw);

                                if(full()){
                                    System.out.println("END");
                                    clearFunction(draw);
                                    timer.cancel();
                                    timer.purge();
                                }
                            }
                        }, 500,(int)speeds);

                    }

                    if(e.getSource() == clear)
                        clearFunction(draw);


                // Get a position in the grid to be filled
                    if (e.getSource() == random) {
                        randomFunction(draw);
                    }

                // Perform the randomFunction for a specified amount of time
                    if(e.getSource() == auto){
                        for(int i = 0; i < amounts; i ++){
                            randomFunction(draw);
                        }
                    }
            }



        };
        // Press for Random
        this.setLayout(null);
        random.setBounds(30,30,100,30);
        random.addActionListener(removeBlock);
        this.add(random);

        // Press for using Random Function for an amount of times
        auto.setBounds(30,70,100,30);
        auto.addActionListener(removeBlock);
        amount.setBounds(140,70,100,30);
        amount.addActionListener(removeBlock);
        this.add(auto);
        this.add(amount);
        // Press this for setting a timing
        timing.setBounds(30,110,100,30);
        timing.addActionListener(removeBlock);
        speed.setBounds(140,110,100,30);
        speed.addActionListener(removeBlock);
        this.add(speed);
        this.add(timing);
        // Press this to clear all
        clear.setBounds(30,150,100,30);
        clear.addActionListener(removeBlock);
        this.add(clear);

    }

    private boolean full() {
        for(int i = 0; i < dp.ROW; i ++){
            for(int j  = 0; j < dp.COL; j++) {
                if(grid[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    private void clearFunction(DrawPanel draw) {

        for(int i = 0; i < draw.ROW; i ++){
            for(int j  = 0; j < draw.COL; j++){
                grid[i][j] = 0;
                dp.grid[i][j] = draw.BLOCK;
                draw.grid[i][j] = draw.BLOCK;
                draw.repaint();
            }
        }
        uf.initialize();
    }

    public void checkFill(int[][] grid, int y, int x) {
        //    boolean check = false;
        // If any 4 surrounding position is not a block, then fill it in

        if(y == 0){
            // Cannot move top
            if(x == 0){
                // Cannot move left
                if(grid[y][x+1] != 0){
                    uf.union(y, x+1, y, x);
                    // check = true;
                }if(grid[y+1][x] != 0){
                    uf.union(y+1,x,y,x);
                    //    check = true;
                }
            } else if(x == dp.COL - 1){
                // Cannot move right
                if(grid[y][x-1] != 0){
                    uf.union(y,x-1,y,x);
                    //  check = true;
                }
                if(grid[y+1][x] != 0){
                    uf.union(y+1,x,y,x);
                    //  check = true;
                }
            } else{
                // Can move left and right x.
                if(grid[y][x+1] != 0){
                    uf.union(y,x+1,y,x);
                    //   check = true;
                }
                if(grid[y+1][x] != 0){
                    uf.union(y+1,x,y,x);
                    //  check = true;
                }
                if(grid[y][x-1] != 0){
                    uf.union(y,x-1,y,x);
                    //  check = true;
                }
            }
        }
        else if(y == dp.ROW - 1){
            // Cannot move bot
            if(x == 0){
                // Cannot move left
                if(grid[y][x+1] != 0){
                    uf.union(y,x+1,y,x);
                    //  check = true;
                }
                if(grid[y-1][x] != 0){
                    uf.union(y-1,x,y,x);
                    //  check = true;
                }
            } else if(x == dp.COL - 1){
                // Cannot move right
                if(grid[y][x-1] != 0){
                    uf.union(y,x-1,y,x);
                    // check = true;
                }
                if(grid[y-1][x] != 0){
                    uf.union(y-1,x,y,x);
                    //check = true;
                }
            } else{
                // Can move left and right x.
                if(grid[y][x+1] != 0){
                    uf.union(y,x+1, y,x);
                    //check = true;
                }
                if(grid[y-1][x] != 0){
                    uf.union(y-1,x,y,x);
                    //check = true;
                }
                if(grid[y][x-1] != 0){
                    uf.union(y,x-1,y,x);
                    //  check = true;
                }
            }
        }
        else{
            // Can move top or bot
            // Cannot move top
            if(x == 0){
                // Cannot move left
                if(grid[y][x+1] != 0){
                    uf.union(y,x+1,y,x);
                    //   check = true;
                }
                if(grid[y+1][x] != 0){
                    uf.union(y+1,x,y,x);
                    //   check = true;
                }
                if(grid[y-1][x] != 0){
                    uf.union(y-1,x,y,x);
                    //  check = true;
                }
            } else if(x == dp.COL - 1){
                // Cannot move right
                if(grid[y][x-1] != 0){
                    uf.union(y,x+1,y,x);
                    // check = true;
                }
                if(grid[y+1][x] != 0){
                    uf.union(y+1,x,y,x);
                    //    check = true;
                }
                if(grid[y-1][x] != 0){
                    uf.union(y-1,x,y,x);
                    //   check = true;
                }
            } else{
                // Can move left and right x.
                if(grid[y][x+1] != 0){
                    uf.union(y,x+1, y,x);
                    //  check = true;
                }
                if(grid[y+1][x] != 0){
                    uf.union(y+1,x,y,x);
                    //  check = true;
                }
                if(grid[y][x-1] != 0){
                    uf.union(y,x-1,y,x);
                    //   check = true;
                }
                if(grid[y-1][x] != 0){
                    uf.union(y-1,x,y,x);
                    //  check = true;
                }
            }
        }
        //   return check;
        grid[y][x] = 1;
        for(int i = 0; i < dp.ROW; i++){
            for(int j = 0; j < dp.COL; j++){
                if(grid[i][j] == 1 && uf.checkSize(i,j))
                    grid[i][j] = 2;
            }
        }
    }

    private void randomFunction(DrawPanel draw){

        boolean allset = true;
        for(int i = 0; i < dp.ROW; i ++){
            for(int j = 0; j < dp.COL; j++){
                if(grid[i][j] == 0)
                    allset = false;
            }
        }

        if(allset)
            return;

        while (true) {
            int ranNum1 = (int) (Math.random() * dp.COL);
            int ranNum2 = (int) (Math.random() * dp.ROW);
            System.out.println("first:" + ranNum1 + "second:" + ranNum2);
            if (grid[ranNum1][ranNum2] == 0) {
                checkFill(grid, ranNum1, ranNum2);
                break;
            }
        }

        // Need to modify dp's array to this array
        for (int i = 0; i < dp.ROW; i++) {
            for (int j = 0; j < dp.COL; j++) {
                if (grid[i][j] == 0)
                    draw.grid[i][j] = draw.BLOCK;
                else if (grid[i][j] == 1)
                    draw.grid[i][j] = draw.NOFILL;
                else if (grid[i][j] == 2)
                    draw.grid[i][j] = draw.FILL;
            }
        }
        draw.repaint();
    }
}
