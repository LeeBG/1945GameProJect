package strikers;

import java.awt.Container;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Street2 extends JFrame {

	Street2 street = this;
	int x = 340;
	int y = 15;
	boolean isRunning = true;

	Container c;
	

	public Street2() {
		setTitle("��ǳ");
		setSize(900, 300);
		setLocationRelativeTo(null);// ����� �� �߾ӿ� JFrame ��ġ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		
		c.add(new GamePanel());
		setVisible(true);
	}

	//�⺻flowlayout
	class GamePanel extends JPanel {

		ImageIcon imgIconPlayer, imgIconJang;
		Image imgPlayer, imgJang;
		JButton btn;
		
		// Ű�̺�Ʈ�� jframe�̾ƴ϶� gamepanel
		public GamePanel() {
			// �̹��� ��ġ
			imgIconPlayer = new ImageIcon("images/player.png");
			imgIconJang = new ImageIcon("images/jang.png");
			imgPlayer = imgIconPlayer.getImage();
			imgJang = imgIconJang.getImage();
			btn = new JButton("����");
			add(btn);
			btn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					isRunning = false;
					//���
					//isRunning = !isRunning;
				}
			});

			setFocusable(true);
			addKeyListener(new KeyAdapter() {// �߻�Ŭ����. ���ϴ°Ÿ� �������̵� ���� �������� �ο����ȵ�
				@Override
				public void keyPressed(KeyEvent e) {
					System.out.println("x ��ǥ: " + x);

					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						new Thread(new Runnable() {

							@Override
							public void run() {
								while (street.isRunning) {
									street.x = street.x + 10;
									street.repaint();
									
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}).start();
					}
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
		new Street2();
	}
}