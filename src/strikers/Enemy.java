package strikers;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel {
//implements Initable{ //이미지니까 라벨, 이미지니까 imp안해도 됨

	public Enemy enemy = this;
	public final static String TAG = "Player: ";

	public ImageIcon icEnemy;
	public int x = 1000;
	public int y = 0;
	
	public boolean isRight = false;
	public boolean isLeft = false;
	public boolean isUp = false;
	public boolean isDown = false;
	public boolean TenUp = false;
	public boolean TwoUp = false;
	public boolean FiveDown = false;
	public boolean SevenDown = false;

	public int floor = 1; 
	
	Random enemyY = new Random();
	Random delay = new Random();

	public Enemy() {
		icEnemy = new ImageIcon("image/enemy.png");
		setIcon(icEnemy);
		setSize(50, 50);
		setLocation(x, y);
	}

	public void moveRight() {
		
		this.x = 0;
		this.y = enemyY.nextInt(300)+1;
		System.out.println(TAG + "moveRight");
		
		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					isRight = true;
					while (isRight) {
						x++;
						setLocation(x, y);// 내부에 repaint()가 있음
						
						try {
							Thread.sleep(2);
							if(x>1000) {
								//setIcon(null);
								Random r = new Random();
								
								Thread.sleep(delay.nextInt(5000)+1);
								x = 0;
								y = enemyY.nextInt(300)+1;
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start(); //new Thread(new Runnable() {
		}
	}
	
	public void moveLeft() {
		this.x = 1000;
		this.y = enemyY.nextInt(300)+1;
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
							Thread.sleep(2);
							if(x<-50) {
								//setIcon(null);
								Thread.sleep(delay.nextInt(5000)+1);
								x = 1000;
								y = enemyY.nextInt(300)+1;
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
							Thread.sleep(5);
							if(y<-50) {
								//setIcon(null);
								Thread.sleep(delay.nextInt(5000)+1);
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
		this.x = enemyY.nextInt(300)+1;
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
							Thread.sleep(5);
							if(y>600) {
								//setIcon(null);
								Thread.sleep(delay.nextInt(5000)+1);
								x = enemyY.nextInt(300)+1;
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
	//10시방향 쪽으로 올라가는 거
	public void moveTenUp() {
		this.x = 1000;
		this.y = enemyY.nextInt(400)+1;
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
							Thread.sleep(5);
							if(y<-50) {
								//setIcon(null);
								Thread.sleep(delay.nextInt(5000)+1);
								x = 1000;
								y = enemyY.nextInt(400)+1;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	//2시 방향으로 올라가는 거
	public void moveTwoUp() {
		this.x = 0;
		this.y = enemyY.nextInt(400)+1;
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
							Thread.sleep(5);
							if(y<-50) {
								//setIcon(null);
								Thread.sleep(delay.nextInt(5000)+1);
								x = 0;
								y = enemyY.nextInt(400)+1;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	
	//5시 방향으로 내려가는 거
	public void moveFiveDown() {
		this.x = enemyY.nextInt(400)+1;
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
							Thread.sleep(5);
							if(y>600) {
								//setIcon(null);
								Thread.sleep(delay.nextInt(5000)+1);
								x = enemyY.nextInt(400)+1;
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
	
	//7시 방향으로 내려가는 거
	public void moveSevenDown() {
		this.x = enemyY.nextInt(600)+1;
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
							Thread.sleep(5);
							if(y>600) {
								//setIcon(null);
								Thread.sleep(delay.nextInt(5000)+1);
								x = enemyY.nextInt(600)+1;
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

	public void attack() {
		System.out.println(TAG + "attack");
	}

	public void moveJump() {
		System.out.println(TAG + "jump");
	}
}