/*  ��ǰ��Ϸֻ����ɼ򵥵Ŀ�ʼ���Ʒֹ��ܣ�������Ҫ��Ľ�����
    *��1������ֹͣ����ͣ�������Ĺ���
    *��2��������Ϸ�ٶȿ��ƵĹ��ܣ����ۼ�x�ֺ��ٶ�����
    *��3����������
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
	JButton startGame=new JButton("(����)��ʼ");
	JButton suspendGame=new JButton("��ͣ");
	JButton resumeGame=new JButton("����");
	JButton stopGame=new JButton("ֹͣ");
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
       ����();
       �춹();
}

    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        g.drawString("����:"+fen, 50, 50);
        g.drawString("�ȼ�:"+level,150,50);
        
        g.drawImage(background,100, 100,this);
        g.drawRect(100,100, 600, 600);
        ��ʾ��(g);
        ��ʾ��(g);
    }

    public void actionPerformed(ActionEvent e) {
    
        if(e.getSource()==startGame)
        {
            this.requestFocus();
            this.repaint();
            ����();
            �춹();
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
  

    void ��()    {
        System.out.println("����!");
    }

    void ��(){
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
        
        �춹();
    }

    boolean �ܳ�������()    {
        if(xs[0]==70||xs[0]==700||ys[0]==70||ys[0]==700)   {
            return true;
        }
        return false;

    }
    void ת��(int �·���)    {
      if(fang%2!=�·���%2)      {
          fang=�·���;
      }
    }
    boolean ���Լ�����()    {
        for (int i = 1; i < chang; i++) {
            if(xs[0]==xs[i]&&ys[0]==ys[i])            {
                return true;
            }
        }
        return false;
    }

    boolean �ܳԶ���()    {

        if(xs[0]==douX&&ys[0]==douY)        {
            return true;
        }
        return false;
    }

    void ����()   {
        fang=2;chang=5;
        for (int i = 0; i < chang; i++) {
            xs[i]=400;
            ys[i]=400+30*i;
        }
    }

    void �춹()    {
       douX=30*(int)(Math.random()*19)+100;
       douY=30*(int)(Math.random()*19)+100;
       for (int i = 0; i < chang; i++) {
           if(xs[i]==douX&&ys[i]==douY)     {
               �춹();
               return;
           }
       }
    }

    void ��ʾ��(Graphics g)   {
        g.setColor(Color.BLUE);
        g.fillOval(douX, douY, 30, 30);
    }

    void ��()    {
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

    void ��ʾ��(Graphics g)

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

                if(�ܳԶ���())

                {

                   ��();

                }

                if(�ܳ�������())

                {

                    ��();
                    
                    JOptionPane.showMessageDialog(null,"Game Over ����");
                    
                    return;

                }

                if(���Լ�����())

                {

                    ��();
                    
                    JOptionPane.showMessageDialog(null,"Game Over ����");

                    return;

                }
                ��();
                	
                	
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

    void ת��()   {     

    }
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

        case KeyEvent.VK_UP:

            ת��(2);

            break;

        case KeyEvent.VK_DOWN:

            ת��(4);

            break;

        case KeyEvent.VK_LEFT:

            ת��(3);
            break;

        case KeyEvent.VK_RIGHT:

            ת��(1);

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