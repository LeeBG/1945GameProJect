package test2;

import java.awt.Image;

import javax.swing.JLabel;

abstract class EnemyUnit extends JLabel {
	protected int x;
	protected int y;


	public void movedown() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					y++;
					setLocation(x, y); // 내부에 repaint() 존재 이벤트가 종료되어야 실행되므로, 스레드를 활용
					if (y >639) { //쓰레드 종료조건
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
						x--;
						setLocation(x, y); // 내부에 repaint() 존재 이벤트가 종료되어야 실행되므로, 스레드를 활용
						
						if (x < 0) { //쓰레드 종료조건
							
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
					y--;
					setLocation(x, y); 
					if (y < 0) { //쓰레드 종료조건
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
					x++;
					setLocation(x, y); // 내부에 repaint() 존재 이벤트가 종료되어야 실행되므로, 스레드를 활용
					if (x >500) { //쓰레드 종료조건
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
