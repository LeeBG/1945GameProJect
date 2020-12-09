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
					setLocation(x, y); // ���ο� repaint() ���� �̺�Ʈ�� ����Ǿ�� ����ǹǷ�, �����带 Ȱ��
					if (y > 639) { // ������ ��������
						System.out.println("movedown ������ ����");
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
					setLocation(x, y); // ���ο� repaint() ���� �̺�Ʈ�� ����Ǿ�� ����ǹǷ�, �����带 Ȱ��

					if (x < 0) { // ������ ��������
						System.out.println("moveleft ������ ����");

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
					if (y < 0) { // ������ ��������
						System.out.println("moveup ������ ����");

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
					setLocation(x, y); // ���ο� repaint() ���� �̺�Ʈ�� ����Ǿ�� ����ǹǷ�, �����带 Ȱ��
					if (x > 500) { // ������ ��������
						System.out.println("moveright ������ ����");

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
