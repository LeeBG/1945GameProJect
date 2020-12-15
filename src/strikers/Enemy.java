package strikers;

import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Enemy extends JLabel {
	//implements Initable, 이미지니까 라벨, 이미지니까 imp안해도 됨

	private Enemy enemy = this;
	private final static String TAG = "Player: ";

	private ImageIcon icEnemy;
	private int x = -50;
	private int y = -50;
	
	private boolean isRight, isLeft, isUp, isDown = false;
	private boolean TenUp, TwoUp, FiveDown, SevenDown = false;
	
	Random eny = new Random();
	Random dly = new Random();
	
	//占쏙옙占쏙옙占시곤옙 占쌍댐옙5占쏙옙 
	private int delay = dly.nextInt(5000)+1;
	//占쏙옙占쏙옙 占쏙옙표(x,y占쏙옙) 占쏙옙치. Y占쏙옙 y占쏙옙占싱띰옙 占쏙옙占� 占쏙옙占쏙옙
	private int ranEnemyY300 = eny.nextInt(300)+1;//
	private int ranEnemyY400 = eny.nextInt(400)+1;
	private int ranEnemyY600 = eny.nextInt(600)+1;
	
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public Enemy() {
		icEnemy = new ImageIcon("image/enemy.png");
		setIcon(icEnemy);
		setSize(50, 50);
		setLocation(x, y);
		
		//setIcon(icPlayerR);
	}

	public void EnemymoveRight() {
		this.x = 0;
		this.y = ranEnemyY300;
		
		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					isRight = true;
					while (isRight) {
						x++;
						setLocation(x, y);
						try {
							Thread.sleep(StrikersApp.speed2); //占쌈듸옙
							if(x > StrikersApp.XAXIS) { // StrikersApp.X占쏙옙 = 500
								Thread.sleep(delay);
								x = StrikersApp.ZAXIS; // StrikersApp.Z占쏙옙 = 0
								y = ranEnemyY300;
								System.out.println(TAG + "moveRight");
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start(); 
		}
	}
	
	public void moveLeft() {
		this.x = 1000;
		this.y = ranEnemyY300;
		System.out.println(TAG + "moveLeft");

		if (isLeft == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					isLeft = true;
					while (isLeft) {
						x--;
						setLocation(x, y);
						try {
							Thread.sleep(StrikersApp.speed2);
							if(x<-50) {
								Thread.sleep(delay);
								x = StrikersApp.XAXIS; // StrikersApp.X占쏙옙 = 500
								y = ranEnemyY300;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void moveUp() {
		this.x = 500;
		this.y = 600;
		System.out.println(TAG + "moveUp");

		if (isUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					isUp = true;
					while (isUp) {
						y--;
						setLocation(x, y);
						try {
							Thread.sleep(StrikersApp.speed5); 
							if(y<-50) {
								Thread.sleep(delay);
								x = 500;
								y = 600;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	public void moveDown() {
		this.x = ranEnemyY300;
		this.y = -50;
		System.out.println(TAG + "moveDown");

		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					isDown = true;
					while (isDown) {
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(StrikersApp.speed5);
							if(y>600) {
								Thread.sleep(delay);
								x = ranEnemyY300;
								y = -50;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	//10占시뱄옙占쏙옙 占쏙옙占쏙옙占쏙옙 占시라가댐옙 占쏙옙
	public void moveTenUp() {
		this.x = StrikersApp.XAXIS;
		this.y = ranEnemyY400;
		System.out.println(TAG + "moveDown");

		if (TenUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					TenUp = true;
					while (TenUp) {
						x--;
						y--;
						setLocation(x, y);
						try {
							Thread.sleep(StrikersApp.speed5);
							if(y<-50) {
								Thread.sleep(delay);
								x = StrikersApp.XAXIS; //x占쏙옙 500
								y = ranEnemyY400;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	//2占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占시라가댐옙 占쏙옙
	public void moveTwoUp() {
		this.x = 0;
		this.y = ranEnemyY400;
		System.out.println(TAG + "moveDown");

		if (TwoUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					TwoUp = true;
					while (TwoUp) {
						x++;
						y--;
						setLocation(x, y);
						try {
							Thread.sleep(StrikersApp.speed5);
							if(y<-50) {
								Thread.sleep(delay);
								x = StrikersApp.ZAXIS; // x占쏙옙 0
								y = ranEnemyY400;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	
	//5占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙
	public void moveFiveDown() {
		this.x = ranEnemyY400;
		this.y = -50;
		System.out.println(TAG + "moveDown");

		if (FiveDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					FiveDown = true;
					while (FiveDown) {
						x++;
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(StrikersApp.speed5);
							if(y>600) {
								Thread.sleep(delay);
								x = ranEnemyY400;
								y = -50;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	
	//7占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙
	public void moveSevenDown() {
		this.x = ranEnemyY600;
		this.y = -50;
		System.out.println(TAG + "moveDown");

		if (SevenDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					SevenDown = true;
					while (SevenDown) {
						x--;
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(StrikersApp.speed5);
							if(y>600) {
								Thread.sleep(delay);
								x = ranEnemyY600;
								y = -50;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	

}