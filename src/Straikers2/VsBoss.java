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

	private VsBoss vsBoss = this; // 항상 컨텍스트를 먼저 쓴다
	private static final String TAG = "VsBoss : "; // 태그

	public static final int SCREEN_WIDTH = 600; // 창 크기 절대값으로 설정
	public static final int SCREEN_HEIGHT = 820;

	Image stageImg = new ImageIcon("images/stage.png").getImage();
	Image bossStageImg = new ImageIcon("images/vsBossStage.png").getImage();
	// 배경 이미지 창 크기에 맞게 사이즈 조절

	private Image bufferImg; // 더블 버퍼링을 위해 선언
	private Graphics screenGraphics;

	JPanel laBackground;

	int stageY = -(stageImg.getHeight(null) - bossStageImg.getHeight(null));
	int bossStageBY1 = -(stageImg.getHeight(null));
	int bossStageBY2 = -(stageImg.getHeight(null) + bossStageImg.getHeight(null));
	
//	int bossStageBY1 = 0;
//	int bossStageBY2 = -bossStageImg.getHeight(null);

	private Player player; // 플레이어의 레퍼런스
	private Boss boss; // 보스의 레퍼런스

	public VsBoss() {
		init();
		setting();
		listener();

		setVisible(true);
	}

	class MyPanel extends JPanel {
		public MyPanel() { // 생성자
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						stageY++;
						bossStageBY1++;
						bossStageBY2++;

						if (stageY > bossStageImg.getHeight(null)) {
							if (bossStageBY1 > (bossStageImg.getHeight(null) - 1)) {
								bossStageBY1 = -(bossStageImg.getHeight(null) - 1);
							}
							if (bossStageBY2 > (bossStageImg.getHeight(null) - 1)) {
								bossStageBY2 = -(bossStageImg.getHeight(null) - 1);
							}
						}
						// 배경 무한루프 도중 중간에 비는 선을 지우기 위해 뺀다
						
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
