package test2;

import java.awt.Image;

import javax.swing.JLabel;

abstract class EnemyUnit extends JLabel {
	protected int EnemyX;
	protected int EnemyY;
	protected int bulletAppear=0;//nullpointer 예외가 안 나오게 0으로!!!!

	public void movedown() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					bulletAppear++;
					EnemyY++;
				
					setLocation(EnemyX, EnemyY);
					if (EnemyY > 639) {
						System.out.println("movedown 쓰레드 종료");
						break;
					}

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	
	public void moveleft() {

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					EnemyX--;
					setLocation(EnemyX, EnemyY); 

					if (EnemyX < 0) { 
						System.out.println("moveleft 쓰레드 종료");

						break;
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	
	
	public void moveup() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					EnemyY--;
					setLocation(EnemyX, EnemyY);
					if (EnemyY < 0) { 
						System.out.println("moveup 스레드 종료");

						break;
					}

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	
	
	
	public void moveright() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					EnemyX++;
					setLocation(EnemyX, EnemyY); 
					if (EnemyX > 500) { 
						System.out.println("moveright 스레드 종료");

						break;
					}

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
	
	
	
	
	

	
}
