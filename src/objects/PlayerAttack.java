package objects;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PlayerAttack { // 시간없으니까 지금말고, 나중에 스레드로 여기에 총알 충돌 구현
	private PlayerAttack playerAttack = this;
	private EnemyUnit enemyUnit; // 지금은 쓸데없지만 나중에

	Image playerBulletImg1 = new ImageIcon("images/playerBullet1.png").getImage();
	Image playerBulletImg2 = new ImageIcon("images/bullet1.png").getImage();
	Image playerBulletImg3 = new ImageIcon("images/bullet3.png").getImage();

	private boolean collision;
	private int x;
	private int y;
	private double angle; // 총알 각도
	private double speed; // 총알 속도
	private int width;
	private int height;

	public PlayerAttack() {
		// TODO Auto-generated constructor stub
	}

	public PlayerAttack(int x, int y, double bulletAngle, double bulletSpeed) {

		this.x = x;
		this.y = y;
		this.angle = bulletAngle;
		this.speed = bulletSpeed;

		collision = false;

	}

	
	
	public PlayerAttack getPlayerAttack() {
		return playerAttack;
	}

	public void setPlayerAttack(PlayerAttack playerAttack) {
		this.playerAttack = playerAttack;
	}

	public EnemyUnit getEnemyUnit() {
		return enemyUnit;
	}

	public void setEnemyUnit(EnemyUnit enemyUnit) {
		this.enemyUnit = enemyUnit;
	}

	public boolean isCollision() {
		return collision;
	}

	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void Fire() {
		x -= Math.cos(Math.toRadians(angle)) * speed;
		y -= Math.sin(Math.toRadians(angle)) * speed;
	}

}
