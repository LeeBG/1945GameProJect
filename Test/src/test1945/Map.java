package test1945;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JFrame {
	private boolean isgame = false;
	private MyPanel panel;
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
		heightStart = 5515;
		heightEnd = 6135;
		playerPlane = new PlayerPlane();

	}

	private void setting() {
		setTitle("1945_MAP_TEST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 620);
		panel.setLayout(null);
		setContentPane(panel);
		setLocationRelativeTo(null); // 중간
	}

	private void batch() { // 비행기 배치
		panel.add(playerPlane);
	}

	public void enemybatch() { // 적비행기 배치 300px마다 적비행기 한 대씩 출현(나중에 벡터에 담을것)
		if (heightStart % 300 == 0) {
			add(new EnemyPlane(playerPlane));
		}
	}
	

	private void listener() { // 키보드 리스너
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					playerPlane.moveRight();

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

					playerPlane.moveLeft(); // 메시지 호출, 책임 협력 => OOP프로그램

				} else if (e.getKeyCode() == KeyEvent.VK_UP) {

					playerPlane.moveUp(); // 메시지 호출 , 책임 협력 => OOP프로그램

				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

					playerPlane.moveDown();

				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					playerPlane.isRight = false;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					playerPlane.isLeft = false;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					playerPlane.isDown = false;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					playerPlane.isUp = false;
				}
			}

		});
	}

	public void crushBorder() {						//경계면 충돌 메서드
		if(playerPlane.getX()<=0) {
			playerPlane.setX(0);
			repaint();
		}else if(playerPlane.getX()>=395) {
			playerPlane.setX(395);
			repaint();
		}
		if(playerPlane.getY()<=0) {
			playerPlane.setY(0);
			repaint();
		}else if(playerPlane.getY()>=520) {
			playerPlane.setY(520);
			repaint();
		}
	}
	
	class MyPanel extends JPanel { // 배경 패널
		private ImageIcon icon = new ImageIcon("images/Stage1_1.png");
		private Image img = icon.getImage(); // 이미지 객체

		
		public MyPanel() {
//			setFocusable(true);

			new Thread(new Runnable() {
				@Override
				public void run() {
					while (heightStart > 0) {
						try {
							heightStart -= 1;
							heightEnd -= 1;
							Thread.sleep(10);
							enemybatch();
							crushBorder();							//경계면 충돌 메서드		
						}catch (Exception e) {
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
			g.drawImage(img, 0, 0, 480, 620, 0, heightStart, 318, heightEnd, this); // 배경보여주기 클리핑
			repaint();
		}
	}

	public static void main(String[] args) {
		new Map(); // 현재 메인인 MAP실행
	}

}
