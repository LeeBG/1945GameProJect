package test1945;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JFrame{
	private boolean isgame = false;
	private MyPanel panel = new MyPanel();
	private int speed = 1;		//맵올라가는 속도
	private int heightStart=5515-speed, heightEnd=6135-speed;

	
	public Map() {
		init();
		setting();
		batch();
		listener();
		setVisible(true);
	}
	private void init() {}
	private void setting() {
		setTitle("1945_MAP_TEST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480,620);
		setLayout(null);
		setContentPane(panel);
		setLocationRelativeTo(null);
	}
	private void batch() {}
	private void listener() {}
	
	class MyPanel extends JPanel{
		private ImageIcon icon = new ImageIcon("images/Stage1_1.png");
		private Image img = icon.getImage();//이미지 객체
		
		public MyPanel() {
			setFocusable(true);
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(heightStart>0) {	
						try {
							heightStart-=1;
							heightEnd-=1;
							System.out.println(heightStart);
							System.out.println(heightEnd);
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}					
				}
			}).start();		
		}
		
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img,0,0,480,620,0,heightStart,318,heightEnd,this);
			repaint();
		}
	}
	
	public static void main(String[] args) {
		new Map();
	}

}
