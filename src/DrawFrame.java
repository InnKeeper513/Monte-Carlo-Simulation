import javax.swing.*;


/**
 * Created by jerry on 2017/8/14.
 */
public class DrawFrame extends JFrame {

    public DrawFrame(){
        super("Monte Carlo Simulation");
        this.setSize(1000,700);
        this.setLayout(null);

        DrawPanel dp = new DrawPanel();
        dp.setBounds(0,0,700,700);
        dp.setVisible(true);
        this.add(dp);

        ControlPanel cp = new ControlPanel(dp);
        cp.setBounds(700,0,300,700);
        cp.setVisible(true);
        this.add(cp);

    }
}
