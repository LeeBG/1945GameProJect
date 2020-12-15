package strikers;

import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Enemy extends JLabel {
	//implements Initable, 이미지니까 라벨, 이미지니까 imp안해도 됨
	//private final static String TAG = "Player: ";
	
	private Enemy enemy = this;
	private ImageIcon icEnemy;
	
	private int x, y;
	private boolean isRight, isLeft, isUp, isDown;
	private boolean TenUp, TwoUp, FiveDown, SevenDown;
	
	Random eny = new Random();
	Random dly = new Random();
	
	//占쏙옙占쏙옙占시곤옙 占쌍댐옙5占쏙옙 
	private int delay;
	//占쏙옙占쏙옙 占쏙옙표(x,y占쏙옙) 占쏙옙치. Y占쏙옙 y占쏙옙占싱띰옙 占쏙옙占� 占쏙옙占쏙옙
	private int ranEnemy300;
	private int ranEnemy400;
	private int ranEnemy600;
	
	//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	
	public void init() {
		x = -50;
		y = -50;
		isRight = false; //오른쪽 이동
		isLeft = false; //왼쪽 이동
		isUp = false; //위 이동
		isDown = false; //아래 이동
		TenUp = false; //왼쪽 대각선 위 이동
		TwoUp = false; //오른쪽 대각선 위 이동
		FiveDown = false; //오른쪽 대각선 아래 이동
		SevenDown = false; //왼쪽 대각선 아래 이동
		delay = dly.nextInt(5000)+1; // 사라졌다 나타나기 까지 걸리는 시간
		ranEnemy300 = eny.nextInt(300)+1; //랜덤 위치
		ranEnemy400 = eny.nextInt(400)+1; //랜덤 위치
		ranEnemy600 = eny.nextInt(600)+1; //랜덤 위치
		
		icEnemy = new ImageIcon("image/enemy.png");
		setIcon(icEnemy);
		setSize(50, 50);
		setLocation(x, y);
		
	}
	
	public Enemy() {
		init();
		
	}
	
	public void EnemymoveRight() {
		//System.out.println(TAG + "moveRight");
		x = ranEnemy300;
		y = ranEnemy300;
		
		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icEnemy);
					isRight = true;
					while (isRight) {
						
						
						System.out.println("무브라이트");
						x++;
						setLocation(x, y);
						try {
							Thread.sleep(StrikersApp.speed5); //占쌈듸옙
							if(x > 500) { // StrikersApp.X占쏙옙 = 500
								Thread.sleep(delay);
								x = 30; // StrikersApp.Z占쏙옙 = 0
								y = 30;
								//System.out.println(TAG + "moveRight");
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
		this.y = ranEnemy300;
		//System.out.println(TAG + "moveLeft");

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
								y = ranEnemy300;
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
		//System.out.println(TAG + "moveUp");

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
		this.x = ranEnemy300;
		this.y = -50;
		//System.out.println(TAG + "moveDown");

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
								x = ranEnemy300;
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
		this.y = ranEnemy400;
		//System.out.println(TAG + "moveDown");

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
								y = ranEnemy400;
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
		this.y = ranEnemy400;
		//System.out.println(TAG + "moveDown");

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
								y = ranEnemy400;
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
		this.x = ranEnemy400;
		this.y = -50;
		//System.out.println(TAG + "moveDown");

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
								x = ranEnemy400;
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
		this.x = ranEnemy600;
		this.y = -50;
		//System.out.println(TAG + "moveDown");

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
								x = ranEnemy600;
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