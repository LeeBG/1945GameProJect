package Straikers2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VsBoss extends JFrame implements Initable {

	private VsBoss vsBoss = this; // �׻� ���ؽ�Ʈ�� ���� ����
	private static final String TAG = "VsBoss : "; // �±�

	public static final int SCREEN_WIDTH = 480;		// â ũ�� ���밪���� ����
	public static final int SCREEN_HEIGHT = 620;

	private Image bufferImg;			// ���� ���۸��� ���� ����
	private Graphics screenGraphics;

	private Player player; // �÷��̾��� ���۷���
	private Boss boss;	// ������ ���۷���

	ImageIcon bossStageOriginIcon = new ImageIcon("images/vs Boss stage.png");
	Image bossStageOriginImg = bossStageOriginIcon.getImage();
	Image bossStageImg = bossStageOriginImg.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_SMOOTH);
	// ��� �̹��� â ũ�⿡ �°� ������ ����
	
	public VsBoss() {
		init();
		setting();
		listener();

		setVisible(true);
	}

	public void paint(Graphics g) {			// ���� ���۸��� ���� ����
		bufferImg = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
		screenGraphics = bufferImg.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(bufferImg, 0, 0, null);
	}

	public void screenDraw(Graphics g) {	// ������ �׸��� ��
		g.drawImage(bossStageImg, 0, 0, null);
		player.playerUpdate(g);
		boss.bossUpdate(g);
		repaint();
	}

	public static void main(String[] args) {
		new VsBoss();
	}

	@Override
	public void init() {
//		laBackground = new JLabel();
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
	}

	@Override
	public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					player.setChangeWeppon2(true);		// 1�� ������ ����2�� ���� true�� �ǰ�
					player.setChangeWeppon1(false);		// ���� 1�� ���� false�� �ٲ��
					break;
				case KeyEvent.VK_2:
					player.setChangeWeppon1(true);		// 2�� ������ ����1�� ���� true�� �ǰ�
					player.setChangeWeppon2(false);		// ���� 2�� ���� false�� �ٲ��
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
