package Snake;

import javax.swing.JFrame;

public class ShellMain extends JFrame {
    public ShellWin win;
    public ShellMain()
    {
        this.win=new ShellWin();
        this.setTitle("Ã∞≥‘…ﬂ");
        this.setSize(800,800);
        this.setVisible(true);
        this.add(win);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new ShellMain();
   }

}
