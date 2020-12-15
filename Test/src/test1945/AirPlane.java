package test1945;
import javax.swing.JLabel;

public abstract class AirPlane extends JLabel{
	private AirPlane airPlane = this;
	public AirPlane() {};
	public void moveRight() {};
	public void moveLeft() {};
	public void moveDown() {};
	public void moveUp() {};
	public void moveLeftUp() {};
	public void moveRightUp() {};
	public void moveLeftDown() {};
	public void moveRightDown() {};
	public void shotToEnemy() {	};
	public void shotToPlayer() {};
	public void crush() {};
}
