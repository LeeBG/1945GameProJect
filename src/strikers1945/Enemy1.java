package strikers1945;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy1 extends EnemyUnit {
	// �떒�닚�엳 諛묒쑝濡쒕쭔 �씠�룞�븯�뒗 �쑀�떅
	private Enemy1 enemy1 = this;
	private static final String TAG = "Enemy1 : ";

	public int count;

	static ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy1(PlayerPlane playerPlane, int x, int y, int w, int h) {
		this.playerPlane = playerPlane;
		this.enemyX = x;
		this.enemyY = y;
		this.enemyWidth = w;
		this.enemyHeight = h;
		this.enemyImage = new ImageIcon("images/enemy1.png").getImage();
		this.life = 1;

		this.playerPlane.contextAdd(enemy1);

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
						Thread.sleep(2);

						movedown();

//						if(enemyY>0 && enemyY<50 || enemyY>100 && enemyY<150 || enemyY>200 && enemyY<250)
//							moveleft();
//						if(enemyY>50 && enemyY<100 )
//							moveright();

						count++;

						if (enemyY > 900) {
							System.out.println("enemy1 �벐�젅�뱶 醫낅즺");
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
					if (Math.abs((playerPlane.getX() + playerPlane.getPlayerWidth() / 2)
							- (enemyX + playerPlane.getPlayerWidth() / 2)) < (enemyWidth / 2
									+ playerPlane.getPlayerWidth() / 2)
							&& Math.abs((playerPlane.getY() + playerPlane.getPlayerHeight() / 2)
									- (enemyY + enemyHeight / 2)) < (enemyHeight / 2
											+ playerPlane.getPlayerHeight() / 2)) {
						collision = true;
					} else {
						collision = false;
					}

					try {
						if (collision) {
							explosePlayer(playerPlane, enemy1); // 異⑸룎 �룺諛� 硫붿꽌�뱶
						}
						Thread.sleep(10);
//						if(playerPlane.getLife() <= 0) {
//							Thread.sleep(100);						//1珥덊썑
//							System.exit(1);							//�봽濡쒓렇�옩 醫낅즺
//						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void enemyUpdate(Graphics g) {
		enemyDraw(g);
	}

	public void enemyDraw(Graphics g) { // 洹몃┝洹몃━湲�
		g.drawImage(enemyImage, enemyX, enemyY, enemyWidth, enemyHeight, null);
	}
}
