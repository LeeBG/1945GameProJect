package test1;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Map01 extends JFrame {
	private PlayerPlane player;
	ImageIcon iconbg = new ImageIcon("images/stage1.png");
	Image imgbg =  iconbg.getImage();
	
	int backY =-5635; 
	//int backY =0; 
	
	int bgHeight = imgbg.getHeight(null);//여기서 setsize 값 빼면됨
	int bgwidth = imgbg.getWidth(null);
	
	
	
	public Map01() {
		
		MyPanel panel = new MyPanel();
		player = new PlayerPlane();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		
		System.out.println(bgHeight+"   "+ bgwidth);
		
		
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(700,500);	

		setVisible(true);

	}
	
	
	
	class MyPanel extends JPanel{
		public MyPanel() {
			setFocusable(true);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (backY<=0) {
						backY++;
						repaint();
						
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					
				}
			}).start();
			
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); //배경 이미지 조정이 필요
			
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
				}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.moveDown();
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
		new Map01();
	}

}
