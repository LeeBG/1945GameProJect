package strikers;

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
	private int speed; // 맵올라가는 속도
	private int heightStart, heightEnd;
	private PlayerPlane playerPlane;
	private EnemyPlane enemyPlane;
	private EnemyPlane enemyPlane2;
	private EnemyPlane enemyPlane3;
	private EnemyPlane enemyPlane4;

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
		heightStart = 5515 - speed;
		heightEnd = 6135 - speed;
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

	private void batch() {
		panel.add(playerPlane);

	}

	public void enemybatch() {

		if (heightStart % 300 == 0) {
			add(new EnemyPlane(playerPlane));
		}
	}

	private void listener() {
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

	class MyPanel extends JPanel {
		private ImageIcon icon = new ImageIcon("images/Stage1_1.png");
		private Image img = icon.getImage();// 이미지 객체

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
			g.drawImage(img, 0, 0, 480, 620, 0, heightStart, 318, heightEnd, this);
			repaint();
		}
	}

	public static void main(String[] args) {
		new Map();
	}

}
