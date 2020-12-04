package test1945;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerPlane extends JLabel {
	public PlayerPlane player = this;
	private static final String TAG = "Player: ";
	public ImageIcon playerIcon;
	public int x = 200; // 플레이어 라벨의 위치좌표
	public int y = 510; // 플레이어 라벨의 위치좌표
	public int sizeX =79;	//비행기 사이즈X
	public int sizeY =60;	//비행기 사이즈Y
	
	public boolean isRight = false; // 오른쪽으로 움직이는지 아닌지의 상태
	public boolean isLeft = false; // 왼쪽으로 움직이는지 아닌지의 상태
	public boolean isUp = false;
	public boolean isDown = false;

	public PlayerPlane() {
		playerIcon = new ImageIcon("images/PLANE1.png");
		setIcon(playerIcon);
		setLocation(x, y);
		setSize(sizeX, sizeY);
	}

	public void moveLeft() {
		System.out.println(TAG+"좌측이동");
		new Thread(new Runnable() {	
			@Override
			public void run() {
				isLeft = true;
				while(true) {
					
				}
			}
		}).start();
	}

	public void moveRight() {

	}

	public void moveUP() {

	}

	public void moveDown() {

	}

}
