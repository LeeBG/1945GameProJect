package Straikers2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class VsBoss extends JFrame implements Initable {

	private VsBoss vsBoss = this; // 항상 컨텍스트를 먼저 쓴다
	private static final String TAG = "VsBoss : "; // 태그

	public static final int SCREEN_WIDTH = 480;		// 창 크기 절대값으로 설정
	public static final int SCREEN_HEIGHT = 620;

	private Image bufferImg;			// 더블 버퍼링을 위해 선언
	private Graphics screenGraphics;

	private Player player; // 플레이어의 레퍼런스
	private Boss boss;	// 보스의 레퍼런스

	ImageIcon bossStageOriginIcon = new ImageIcon("images/vs Boss stage.png");
	Image bossStageOriginImg = bossStageOriginIcon.getImage();
	Image bossStageImg = bossStageOriginImg.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, Image.SCALE_SMOOTH);
	// 배경 이미지 창 크기에 맞게 사이즈 조절
	
	public VsBoss() {
		init();
		setting();
		listener();

		setVisible(true);
	}

	public void paint(Graphics g) {			// 더블 버퍼링을 위해 선언
		bufferImg = createImage(SCREEN_WIDTH, SCREEN_HEIGHT);
		screenGraphics = bufferImg.getGraphics();
		screenDraw(screenGraphics);
		g.drawImage(bufferImg, 0, 0, null);
	}

	public void screenDraw(Graphics g) {	// 실제로 그리는 곳
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
					player.setChangeWeppon2(true);		// 1을 누르면 무기2의 값이 true가 되고
					player.setChangeWeppon1(false);		// 무기 1의 값이 false로 바뀐다
					break;
				case KeyEvent.VK_2:
					player.setChangeWeppon1(true);		// 2를 누르면 무기1의 값이 true가 되고
					player.setChangeWeppon2(false);		// 무기 2의 값이 false로 바뀐다
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
