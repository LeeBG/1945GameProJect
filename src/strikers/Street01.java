package strikers;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Street01 extends JFrame {
	// 장풍 위치
	int x = 340;
	int y = 15;

	Container c;

	public Street01() {
		setTitle("장풍");
		setSize(900, 300);
		setLocationRelativeTo(null);// 모니터 정 중앙에 JFrame 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.add(new GamePanel());
		setVisible(true);
	}

	class GamePanel extends JPanel {

		ImageIcon imgIconPlayer, imgIconJang;
		Image imgPlayer, imgJang;

		// 키이벤트는 jframe이아니라 gamepanel
		public GamePanel() {
			// 이미지 배치
			imgIconPlayer = new ImageIcon("images/player.png");
			imgIconJang = new ImageIcon("images/jang.png");
			imgPlayer = imgIconPlayer.getImage();
			imgJang = imgIconJang.getImage();

			setFocusable(true);
			addKeyListener(new KeyAdapter() {// 추상클래스. 원하는거만 오버라이딩 가능 강제성이 부여가안됨
				@Override
				public void keyPressed(KeyEvent e) {
					System.out.println("x 좌표: " + x);
					if (x >= 340 && x <= 650) {
						if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
							x = x + 10;
						} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
							x = x - 10;
						}
					}
					repaint();
				}
			});
		}

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(imgPlayer, 0, 0, null);
			g.drawImage(imgJang, x, y, null);
		}
	}

	public static void main(String[] args) {
		new Street01();
	}
}