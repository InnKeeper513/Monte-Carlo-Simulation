import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlPanel extends JPanel {

    JButton random = new JButton("Random");
    int grid[][];
    UnionFind uf;
    DrawPanel dp;

    public ControlPanel(DrawPanel dp){
        uf = new UnionFind(dp.ROW * dp.ROW, dp.ROW, dp.COL);
        this.dp = dp;
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

                // Get a position in the grid to be filled

                    if (e.getSource() == random) {
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
                                    dp.grid[i][j] = dp.BLOCK;
                                else if (grid[i][j] == 1)
                                    dp.grid[i][j] = dp.NOFILL;
                                else if (grid[i][j] == 2)
                                    dp.grid[i][j] = dp.FILL;
                            }
                        }
                        dp.repaint();
                    }

            }
        };


        random.addActionListener(removeBlock);
        this.add(random);
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
}
