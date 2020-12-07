package strikers;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel {
//implements Initable{ //�̹����ϱ� ��, �̹����ϱ� imp���ص� ��

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

	public Enemy() {
		icEnemy = new ImageIcon("image/enemy.png");
		setIcon(icEnemy);
		setSize(50, 50);
		setLocation(x, y);
	}

	public void moveRight() {
		this.x = 0;
		this.y = 0;
		System.out.println(TAG + "moveRight");
		
		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					isRight = true;
					while (isRight) {
						x++;
						setLocation(x, y);// ���ο� repaint()�� ����
						
						try {
							Thread.sleep(2);
							if(x>1000) {
								//setIcon(null);
								Thread.sleep(3000);
								x = 0;
								y = 0;
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
		this.y = 0;
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
								Thread.sleep(3000);
								x = 1000;
								y = 0;
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
								Thread.sleep(3000);
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
		this.x = 500;
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
								Thread.sleep(3000);
								x = 500;
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
	//10�ù��� ������ �ö󰡴� ��
	public void moveTenUp() {
		this.x = 1000;
		this.y = 400;
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
								Thread.sleep(3000);
								x = 1000;
								y = 400;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	//2�� �������� �ö󰡴� ��
	public void moveTwoUp() {
		this.x = 0;
		this.y = 400;
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
								Thread.sleep(3000);
								x = 0;
								y = 400;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
	
	//5�� �������� �������� ��
	public void moveFiveDown() {
		this.x = 200;
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
								Thread.sleep(3000);
								x = 200;
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
	
	//7�� �������� �������� ��
	public void moveSevenDown() {
		this.x = 600;
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
								Thread.sleep(3000);
								x = 600;
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