package test1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Map02 extends JFrame{
	private PlayerPlane player;
	private JLabel laBackground;
	private MyLabel laBg;
	int backY =-5635; 
	ImageIcon iconbg = new ImageIcon("images/stage1.png");
	Image imgbg =  iconbg.getImage();
	
	public Map02() {
		
		
		init();
		setting();
		batch();
		listener();
		
		setVisible(true);

		
	}
	
	public void init() {
		laBackground = new JLabel(new ImageIcon("images/stage1.png"));
		laBg = new MyLabel();
		player = new PlayerPlane();
	}

	
	
	public void setting() {
		// TODO Auto-generated method stub
		setTitle("sdf");

		setSize(500,639);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		//setContentPane(laBackground);//label로 대체
		setContentPane(laBg);//label로 대체
	}



	
	public void batch() {
		add(player); //container는 생략가능
		
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
						
	
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.isRight = false; //원래는 getter setter 함수를 이용
				}
				
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.isLeft = false; //원래는 getter setter 함수를 이용
				}
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					player.isUP = false; //원래는 getter setter 함수를 이용
				}
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.isDown = false; //원래는 getter setter 함수를 이용
				}
				
				
				
				
			}
			
			
		});
		
	}
	
	
	
	class MyLabel extends JLabel{

		public MyLabel() {
			
			
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
			super.paintComponent(g); //배경 이미지 조정이 필요
			
			g.drawImage(imgbg,0,backY,700,imgbg.getHeight(null),this);
			
			
			
		}
			
	}
	
	
	public static void main(String[] args) {
		new Map02();
	}
	
	
}
