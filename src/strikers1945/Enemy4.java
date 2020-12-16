package strikers1945;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy4 extends EnemyUnit {

	private Enemy4 enemy4 = this;
	private static final String TAG = "Enemy4 : ";

	public int count;

	ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy4(PlayerPlane playerPlane, int x, int y, int w, int h) {
		this.playerPlane = playerPlane;
		this.enemyX = x;
		this.enemyY = y;
		this.enemyWidth = w;
		this.enemyHeight = h;
		this.enemyImage = new ImageIcon("images/enemy4.png").getImage();
		this.life = 5;
		this.playerPlane.contextAdd(enemy4);

		this.move();
		this.crush();
	}

	public void move() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				count = 0;
				while (true) {
					try {
						Thread.sleep(5);
						movedown();
						enemyY++; // down 媛��냽

						if (enemyY < 400)
							moveright();

						bulletCreate();
						enemyAttack();
						count++;

						if (enemyY > 1500) {
							System.out.println("enemy4 �벐�젅�뱶 醫낅즺");
							break;
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void crush() { // �쟻鍮꾪뻾湲�-Player 異⑸룎
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (playerPlane.getLifeCount() > 0) {
					if (Math.abs((playerPlane.getX() + playerPlane.getPlayerWidth() / 2) - (enemyX + playerPlane.getPlayerWidth() / 2))
							< (enemyWidth / 2 + playerPlane.getPlayerWidth() / 2)
							&& Math.abs((playerPlane.getY() + playerPlane.getPlayerHeight() / 2) - (enemyY + enemyHeight / 2))
							< (enemyHeight / 2 + playerPlane.getPlayerHeight() / 2)) {
						collision = true;
					} else {
						collision = false;
					}

					try {
						if (collision) {
							explosePlayer(playerPlane, enemy4);
						}
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void bulletCreate() {
		if (count % 100 == 0) {
			enemyAttack = new EnemyAttack(enemy4, playerPlane, enemyX + 20, enemyY + 40, 270, 2, 20, 20);
			enemyAttackkList.add(enemyAttack);

			enemyAttack = new EnemyAttack(enemy4, playerPlane, enemyX + 40, enemyY + 40, 270, 2, 20, 20);
			enemyAttackkList.add(enemyAttack);
		}
	}

	public void enemyUpdate(Graphics g) {
		enemyDraw(g);
	}

	private void enemyAttack() {
		for (int i = 0; i < enemyAttackkList.size(); i++) {
			enemyAttack = enemyAttackkList.get(i);
			enemyAttack.fire();
		}
		if (enemyAttack.bulletX < -10 || enemyAttack.bulletX > GameFrame.SCREEN_WIDTH || enemyAttack.bulletY < -10 || enemyAttack.bulletY > GameFrame.SCREEN_HEIGHT) {
			enemyAttackkList.remove(enemyAttack);
		}
	}

	public void enemyDraw(Graphics g) { // 洹몃┝洹몃━湲�
		g.drawImage(enemyImage, enemyX, enemyY, enemyWidth, enemyHeight, null);
		for (int i = 0; i < enemyAttackkList.size(); i++) {
			enemyAttack = enemyAttackkList.get(i);
			g.drawImage(enemyAttack.bulletImg2, (int) enemyAttack.bulletX, (int) enemyAttack.bulletY, enemyAttack.bulletWidth1, enemyAttack.bulletHeight1, null);
		}
	}

}
