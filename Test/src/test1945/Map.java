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
		setLocationRelativeTo(null); // �߰�
	}

	private void batch() { // ����� ��ġ
		panel.add(playerPlane);
	}

	public void enemybatch() { // ������� ��ġ 300px���� ������� �� �뾿 ����(���߿� ���Ϳ� ������)
		if (heightStart % 300 == 0) {
			add(new EnemyPlane(playerPlane));
		}
	}
	

	private void listener() { // Ű���� ������
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					playerPlane.moveRight();

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

					playerPlane.moveLeft(); // �޽��� ȣ��, å�� ���� => OOP���α׷�

				} else if (e.getKeyCode() == KeyEvent.VK_UP) {

					playerPlane.moveUp(); // �޽��� ȣ�� , å�� ���� => OOP���α׷�

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

	public void crushBorder() {						//���� �浹 �޼���
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
	
	class MyPanel extends JPanel { // ��� �г�
		private ImageIcon icon = new ImageIcon("images/Stage1_1.png");
		private Image img = icon.getImage(); // �̹��� ��ü

		
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
							crushBorder();							//���� �浹 �޼���		
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
			g.drawImage(img, 0, 0, 480, 620, 0, heightStart, 318, heightEnd, this); // ��溸���ֱ� Ŭ����
			repaint();
		}
	}

	public static void main(String[] args) {
		new Map(); // ���� ������ MAP����
	}

}
