package Straikers2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VsBoss extends JFrame implements Initable {

	private VsBoss vsBoss = this; // �׻� ���ؽ�Ʈ�� ���� ����
	private static final String TAG = "VsBoss : "; // �±�

	public static final int SCREEN_WIDTH = 600; // â ũ�� ���밪���� ����
	public static final int SCREEN_HEIGHT = 820;

	Image stageImg = new ImageIcon("images/stage.png").getImage();
	Image bossStageImg = new ImageIcon("images/vsBossStage.png").getImage();
	// ��� �̹��� â ũ�⿡ �°� ������ ����

	JPanel laBackground;

	int stageY = -(stageImg.getHeight(null) - bossStageImg.getHeight(null));
	int bossStageBY1 = -(stageImg.getHeight(null));
	int bossStageBY2 = -(stageImg.getHeight(null) + bossStageImg.getHeight(null));

	private Player player; // �÷��̾��� ���۷���
	private Boss boss; // ������ ���۷���

	public VsBoss() {
		init();
		setting();
		batch();
		listener();

		setVisible(true);
	}

	class MyPanel extends JPanel {
		public MyPanel() { // ������
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						stageY++;
						bossStageBY1++;
						bossStageBY2++;

						if (stageY > bossStageImg.getHeight(null)) {
							stageY = bossStageImg.getHeight(null); // ������ ��� �������� ���� ����
							if (bossStageBY1 > (bossStageImg.getHeight(null) - 1)) {
								bossStageBY1 = -(bossStageImg.getHeight(null) - 1);
							}
							if (bossStageBY2 > (bossStageImg.getHeight(null) - 1)) {
								bossStageBY2 = -(bossStageImg.getHeight(null) - 1);
							}
						}
						// ��� ���ѷ��� ���� �߰��� ��� ���� ����� ���� ����

						repaint();

						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(stageImg, 0, stageY, null);
			g.drawImage(bossStageImg, 0, bossStageBY1, null);
			g.drawImage(bossStageImg, 0, bossStageBY2, null);
			player.playerUpdate(g);
			boss.bossUpdate(g);
			repaint();
		}
	}

	public static void main(String[] args) {
		new VsBoss();
	}

	@Override
	public void init() {
		laBackground = new MyPanel();
		player = new Player();
		boss = new Boss();
	}

	@Override
	public void setting() {
		setTitle("VsBoss");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setContentPane(laBackground);
	}

	@Override
	public void batch() {
		laBackground.add(player);
		laBackground.add(boss);
	}

	@Override
	public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					player.setWepponLevelUp(true);
					break;
				case KeyEvent.VK_SPACE:
					player.setAttack(true);
					break;
				case KeyEvent.VK_UP:
					player.setUp(true);
					break;
				case KeyEvent.VK_DOWN:
					player.setDown(true);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(true);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(true);
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					player.setWepponLevelUp(false);
					break;
				case KeyEvent.VK_SPACE:
					player.setAttack(false);
					break;
				case KeyEvent.VK_UP:
					player.setUp(false);
					break;
				case KeyEvent.VK_DOWN:
					player.setDown(false);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				}
			}
		});
	}
}
