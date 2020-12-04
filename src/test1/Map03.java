package test1;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Timestamp;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import test1.Map01.MyPanel;

public class Map03 extends JFrame {
	private PlayerPlane player;
	ImageIcon iconbg = new ImageIcon("images/stage1.png");
	Image imgbg =  iconbg.getImage();
	
	int backY =-5635; 
	//int backY =0; 
	
	int bgHeight = imgbg.getHeight(null);//여기서 setsize 값 빼면됨
	int bgwidth = imgbg.getWidth(null);
	

	public Map03() {
		
		MyLabel labg = new MyLabel();
		player = new PlayerPlane();
		setLayout(null);
		
		setContentPane(labg);
		add(player);
		
		
		System.out.println(bgHeight+"   "+ bgwidth);
		
		
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(700,500);	
		listener();
		setVisible(true);

	}
	
	
	
	class MyLabel extends JLabel{
		public MyLabel() {
			setFocusable(true);
			
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
					
				}
			}).start();
			
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); //배경 이미지 조정이 필요
			
			g.drawImage(imgbg,0,backY,700,imgbg.getHeight(null),this);
			
			
			
		}
		
		
	
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		new Map01();
	}

}



class board{
	int id;
	String username;
	String content;//1gb = 10억바이트          varchar(10)==영어는 10자, 한글은 3자
	//varchar 맥스값 찾기,  대용량 담을 수 있는 타입 찾기
	Timestamp creatDate; //날짜를 저찰
}