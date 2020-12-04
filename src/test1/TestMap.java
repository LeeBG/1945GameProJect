package test1;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestMap extends JFrame {
	
	private TestMap testMap =this;
	private PlayerPlane player;
	ImageIcon iconbg = new ImageIcon("images/stage1.png");
	Image imgbg =  iconbg.getImage();

	
	int backY =-5635; ;
	
	public TestMap() {
		
		MyPanel panel = new MyPanel();
		player = new PlayerPlane();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.add(player);
		listener();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setVisible(true);

	}
	
	
	
	class MyPanel extends JPanel{
		public MyPanel() {
			setFocusable(true); //요거 때문에 이벤트 악 먹힘!!!!!!!!!!!!!!!!!!!!!!!!!!!
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (backY<=0) {
						backY++;
						repaint();
						
						try {
							Thread.sleep(5);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					
				}
			}).start();
			
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(imgbg,0,backY,700,imgbg.getHeight(null),this);
			
			
		}
	
	}
	
	
	public void listener() {
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.moveRight();
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.moveLeft();
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_UP) {
					player.moveUp();
				}
				
			}
						
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.isRight = false; //원래는 getter setter 함수를 이용
				}
				
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.isLeft = false; //원래는 getter setter 함수를 이용
				}
			}
			
			
		});
		
	}
	
	
	
	
	
	

	
	public static void main(String[] args) {
		new TestMap();
	}

}
