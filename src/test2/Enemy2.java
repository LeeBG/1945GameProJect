package test2;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//라벨은 LOMBOK 안 먹음
public class Enemy2 extends EnemyUnit {

	public Enemy2 Enemy1 = this;
	public final static String TAG = "Enemy1: ";

	private ImageIcon icEnemy1;



	Enemy2(int x, int y) {
		this.x =x;
		this.y = y;
		
		icEnemy1 = new ImageIcon("images/PLANE3.png");
		setIcon(icEnemy1);
		setSize(100, 50);
		setLocation(x, y);
	
		this.moveLeftDown();

	}

	public void moveLeftDown() {
		movedown();
		moveleft();

	}

	public Enemy2 getEnemy1() {
		return Enemy1;
	}

	public void setEnemy1(Enemy2 Enemy1) {
		this.Enemy1 = Enemy1;
	}

	public ImageIcon getIcEnemy1() {
		return icEnemy1;
	}

	public void setIcEnemy1(ImageIcon icEnemy1) {
		this.icEnemy1 = icEnemy1;
	}

	public static String getTag() {
		return TAG;
	}

	public Enemy2(Enemy2 Enemy1, ImageIcon icEnemy1) {
		super();
		this.Enemy1 = Enemy1;
		this.icEnemy1 = icEnemy1;
	}
	
	
	

}
