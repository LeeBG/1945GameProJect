package test2;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//라벨은 LOMBOK 안 먹음
public class Enemy3 extends EnemyUnit {

	public Enemy3 enemy3 = this;
	public final static String TAG = "Enemy3: ";
	private ImageIcon icEnemy3;
	
	
	Enemy3(int x, int y) {

		this.EnemyX =x;
		this.EnemyY =y;
		icEnemy3 = new ImageIcon("images/PLANE3.png");
		setIcon(icEnemy3);
		setSize(100, 50);
		setLocation(x, y);

		this.moveRightDown();

	}

	public void leftdown() {
		movedown();
		moveleft();

	}

	public Enemy3 getEnemy2() {
		return enemy3;
	}

	public void setEnemy2(Enemy3 enemy3) {
		this.enemy3 = enemy3;
	}

	public ImageIcon getIcEnemy2() {
		return icEnemy3;
	}

	public void setIcEnemy2(ImageIcon icEnemy3) {
		this.icEnemy3 = icEnemy3;
	}

	public static String getTag() {
		return TAG;
	}

	public Enemy3(Enemy3 enemy3, ImageIcon icEnemy3) {
		super();
		this.enemy3 = enemy3;
		this.icEnemy3 = icEnemy3;
	}
	
	
	public void moveRightDown() {
		moveright();
		movedown();
	}
	
	
	

}
