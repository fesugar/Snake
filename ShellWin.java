/*  当前游戏只能完成简单的开始、计分功能，按以下要求改进程序
    *（1）增加停止，暂停，重启的功能
    *（2）增加游戏速度控制的功能，如累计x分后，速度增加
    *（3）界面美化
    **/
package Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.BreakIterator;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


public class ShellWin extends JPanel implements ActionListener,Runnable,KeyListener{
	JButton startGame=new JButton("(重新)开始");
	JButton suspendGame=new JButton("暂停");
	JButton resumeGame=new JButton("继续");
	JButton stopGame=new JButton("停止");
	Image backgroundFile=getToolkit().getImage("bg.jpg");
    Image background=backgroundFile.getScaledInstance(599,599,Image.SCALE_DEFAULT);

	
	int fang;
	int chang;
	int fen=0;
	int level=1;
    int[] xs=new int[400];
    int[] ys=new int[400];
    int douX;int douY;
    Thread nThread;
    public ShellWin(){
    
       this.add(startGame);
       this.add(suspendGame);
       this.add(resumeGame);
       this.add(stopGame);
       
       this.startGame.addActionListener(this);
       this.suspendGame.addActionListener(this);
       this.resumeGame.addActionListener(this);
       this.stopGame.addActionListener(this);
       this.setBackground(Color.PINK);
       
       this.addKeyListener(this);
       造蛇();
       造豆();
}

    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        g.drawString("分数:"+fen, 50, 50);
        g.drawString("等级:"+level,150,50);
        
        g.drawImage(background,100, 100,this);
        g.drawRect(100,100, 600, 600);
        显示蛇(g);
        显示豆(g);
    }

    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource()==startGame)
        {
            this.requestFocus();
            this.repaint();
            造蛇();
            造豆();
            nThread=new Thread(this);
            nThread.start();
            
        }
        else if(e.getSource()==suspendGame){
        	nThread.suspend();
        	
        	}
        	
        else if(e.getSource()==resumeGame){
        	nThread.resume();
        	
        	}
        	
        else if(e.getSource()==stopGame){
        	nThread.stop();
        
        }
       
    }
  

    void 死()    {
        System.out.println("死了!");
    }

    void 吃(){
        chang++;
        this.fen+=100;
        if(this.fen>=500){
        	level=2;
        }
        if(this.fen>=1000){
        	level=3;
        }
        if(this.fen>=1500){
        	level=4;
        }
        if(this.fen>=2000){
        	level=5;
        }
        if(this.fen>=2500){
        	level=6;
        }
        if(this.fen>=3000){
        	level=7;
        }
        if(this.fen>=3500){
        	level=8;
        }
        if(this.fen>=4000){
        	level=9;
        }
        if(this.fen>=4500){
        	level=10;
        }
        
        造豆();
    }

    boolean 能出界死吗()    {
        if(xs[0]==70||xs[0]==700||ys[0]==70||ys[0]==700)   {
            return true;
        }
        return false;

    }
    void 转向(int 新方向)    {
      if(fang%2!=新方向%2)      {
          fang=新方向;
      }
    }
    boolean 吃自己了吗()    {
        for (int i = 1; i < chang; i++) {
            if(xs[0]==xs[i]&&ys[0]==ys[i])            {
                return true;
            }
        }
        return false;
    }

    boolean 能吃豆吗()    {

        if(xs[0]==douX&&ys[0]==douY)        {
            return true;
        }
        return false;
    }

    void 造蛇()   {
        fang=2;chang=5;
        for (int i = 0; i < chang; i++) {
            xs[i]=400;
            ys[i]=400+30*i;
        }
    }

    void 造豆()    {
       douX=30*(int)(Math.random()*19)+100;
       douY=30*(int)(Math.random()*19)+100;
       for (int i = 0; i < chang; i++) {
           if(xs[i]==douX&&ys[i]==douY)     {
               造豆();
               return;
           }
       }
    }

    void 显示豆(Graphics g)   {
        g.setColor(Color.BLUE);
        g.fillOval(douX, douY, 30, 30);
    }

    void 爬()    {
        for (int i = chang-1; i >0; i--) {
            xs[i]=xs[i-1];
            ys[i]=ys[i-1];
        }

        switch (fang) {
        case 1:
            xs[0]=xs[0]+30;
            break;

        case 2:

            ys[0]=ys[0]-30;

            break;

        case 3:

            xs[0]=xs[0]-30;

            break;

        case 4:

            ys[0]=ys[0]+30;

            break;

        default:

            break;

        }

    }

    void 显示蛇(Graphics g)

    {

        for (int i = 0; i < chang; i++) {

            g.setColor(Color.ORANGE);

            g.fillRect(xs[i], ys[i], 30, 30);
            
            g.setColor(Color.WHITE);

            g.drawRect(xs[i], ys[i], 30, 30);

        }

    }
//    @Override

    public void run() {
        while(true)

        {
            try {              

                if(能吃豆吗())

                {

                   吃();

                }

                if(能出界死吗())

                {

                    死();
                    
                    JOptionPane.showMessageDialog(null,"Game Over ！！");
                    
                    return;

                }

                if(吃自己了吗())

                {

                    死();
                    
                    JOptionPane.showMessageDialog(null,"Game Over ！！");

                    return;

                }
                爬();
                	
                	
                	if(fen>=0&&fen<500){
                		
                		Thread.sleep(500);} 
                		
                		else if(fen>=500&&fen<1000){
                			
                		Thread.sleep(400);}
                		
                		else if(fen>=1000&&fen<1500){
                			
                		Thread.sleep(300);}
                		
                		else if(fen>=1500&&fen<2000){
                			
                		Thread.sleep(200);}
                		
                		else if(fen>=2000&&fen<2500){
                			
                		Thread.sleep(100);}
                		
                		else if(fen>=2500){
                			
                		Thread.sleep(50);}
                		
                		repaint();


                } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }

    void 转向()   {     

    }
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

        case KeyEvent.VK_UP:

            转向(2);

            break;

        case KeyEvent.VK_DOWN:

            转向(4);

            break;

        case KeyEvent.VK_LEFT:

            转向(3);
            break;

        case KeyEvent.VK_RIGHT:

            转向(1);

            break;

        default:

            break;

        }

    }

//    @Override

    public void keyReleased(KeyEvent e) {

        // TODO Auto-generated method stub

         

    }

   public void keyTyped(KeyEvent e) {

        // TODO Auto-generated method stub

         

    }

}