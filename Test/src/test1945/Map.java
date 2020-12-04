package test1945;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.SplashScreen;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Map extends JFrame{
	private boolean isgame = false;
	private MyPanel panel;
	private int speed;		//맵올라가는 속도
	private int heightStart, heightEnd;
	private PlayerPlane playerPlane;
	
	public Map() {
		init();
		setting();
		batch();
		listener();
		setVisible(true);
	}
	private void init() {
		panel = new MyPanel();
		speed = 1;
		heightStart=5515-speed;
		heightEnd=6135-speed;
		playerPlane = new PlayerPlane();
	}
	private void setting() {
		setTitle("1945_MAP_TEST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480,620);
		panel.setLayout(null);
		setContentPane(panel);
		setLocationRelativeTo(null);			//중간
	}
	private void batch() {
		add(playerPlane);
	}
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
							Thread.sleep(50);
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
