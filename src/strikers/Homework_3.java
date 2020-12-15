package strikers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.math.*;

public class Homework_3 {
	public ImageIcon icPlayer;
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
	}
}

class MainFrame extends Frame {
	public static Canvas canvas;

	public MainFrame() {
		/* ���α׷� ���۽� ������ ���� �������� �����. */
		this.setSize(200, 200);
		canvas = new Canvas();
		this.add("Center", canvas);
		Panel p = new Panel();
		Button s = new Button("Start");
		Button c = new Button("Close");
		
		s.addActionListener(new StartBtnClickListener());
		c.addActionListener(new CloseBtnClickListener());
		p.add(s);
		p.add(c);
		this.add("South", p);
		this.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}
}

class StartBtnClickListener implements ActionListener {
	// Start ��ư�� ������ ��, ��Ƣ��� �����Ѵ�.
	public void actionPerformed(ActionEvent evt) {
		// �� 5���� �����ؼ� ���� �ٸ��������� �̵���Ű�� �����.
		for (int i = 0; i < 2; i++) {
			BallManager.balls.add(new Ball(0, 0, 20, i * (Math.PI / 5)));
			BallManager.balls.get(i).start();
		}
	}
}
class BallManager {
	public static ArrayList<Ball> balls = new ArrayList<Ball>();
}
//�Ƹ� �Ⱦ�
class CloseBtnClickListener implements ActionListener {
	// Close ��ư�� �������� ���α׷��� �����Ѵ�.
	public void actionPerformed(ActionEvent evt) {
		System.exit(0);
	}
}

class Ball extends Thread {
	int diameter;
	double radian;
	int x, y;
	double dx = 2, dy;
	Canvas canvas = MainFrame.canvas;
	boolean isRunnable = true;

	// ���� ���鶧 x,y ��ǥ, ����, �̵�����(����)�� �ʱ�ȭ�Ѵ�.
	public Ball(int x, int y, int diameter, double radian) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.radian = radian;
		this.dy = dx * Math.tan(this.radian);
		if (this.radian >= Math.PI / 2 && this.radian < 3 * Math.PI / 2) {
			this.dx = -this.dx;
		}
	}

	public void draw() {
		// ���� �׸���.
		Graphics g = canvas.getGraphics();
		//g.fillOval(this.x, this.y, this.diameter, this.diameter);
		//g.dispose();
		ImageIcon icPlayer = new ImageIcon("image/playerR.png");
		
		//setIcon(icPlayer);
	}

	public void move() {
		// ������ ���� ���ش�.
		Graphics g = this.canvas.getGraphics();
		g.setXORMode(this.canvas.getBackground());
		g.fillOval(this.x, this.y, this.diameter, this.diameter);

		// ���� x, y ��ǥ�� �̵���Ų��
		this.x += dx;
		this.y += dy;

		// ���� �ε����� �Ի簢�� ���� ���� �ݻ��Ų��.
		Dimension dimensionOfBox = canvas.getSize();
		if (this.x < 0) {
			dx = -dx;
		}
		if (this.y < 0) {
			dy = -dy;
		}
		if (this.x > dimensionOfBox.width - diameter) {
			dx = -dx;
		}
		if (this.y > dimensionOfBox.height - diameter) {
			dy = -dy;
		}
		// �ٸ����� �浹�ϸ� �������� ������ ������ �ѷ� �ɰ���.
//		for (int i = 0; i < BallManager.balls.size(); i++) {
//			Ball targetBall = BallManager.balls.get(i);
//			if (this.isCollided(targetBall) && i != BallManager.balls.indexOf(this)) {
//				this.splitTwoBall(this, targetBall);
//			}
//		}
		g.setPaintMode();
		g.fillOval(this.x, this.y, this.diameter, this.diameter);
		g.dispose();
	}

	public boolean isCollided(Ball ball) {
		int distanceX = this.x - ball.x;
		int distanceY = this.y - ball.y;
		double distance = Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

		// �������� ���� �������� �Ÿ����� ũ�ų� ������ ���� �浹�� ������ �����Ѵ�.
		if (distance <= (this.diameter + ball.diameter) / 2) {
			double distanceXPrime = (this.x + this.dx) - (ball.x + ball.dx);
			double distanceYPrime = (this.y + this.dy) - (ball.y + ball.dy);
			double distancePrime = Math.sqrt(distanceXPrime * distanceXPrime + distanceYPrime * distanceYPrime);
			// �������� ���� �������� �Ÿ����� ũ�ų� ������ ó�� ������ ��Ȳ�� �����Ѵ�.
			if (distance > distancePrime) {
				return true;
			}
		}
		return false;
	}

	public void split(Ball ball, double rad) {
		double subtractedRadOfBall = rad - Math.PI / 4;
		double addedRadOfBall = rad + Math.PI / 4;
		// �������� 1���� ũ�� ���� ���� ������� ������ �ѷ� ������.
		if (ball.diameter / 2 > 1) {
			Ball ballOne = new Ball(ball.x, ball.y, ball.diameter / 2, subtractedRadOfBall);
			Ball ballTwo = new Ball(ball.x, ball.y, ball.diameter / 2, addedRadOfBall);
			BallManager.balls.add(ballOne);
			BallManager.balls.add(ballTwo);

			int indexOfBall = BallManager.balls.indexOf(ball);
			ball.isRunnable = false;
			BallManager.balls.remove(indexOfBall);

			ballOne.start();
			ballTwo.start();

		}
		// �������� 1���� ������ �������.
		else {
			int indexOfBall = BallManager.balls.indexOf(ball);
			ball.isRunnable = false;
			BallManager.balls.remove(indexOfBall);
		}

	}

	//�Ƹ� �Ⱦ�
//	public void splitTwoBall(Ball ballOne, Ball ballTwo) {
//		// ballOne �� ballTwo�� �浹 ���� ���� ������ ���ͷ� ����Ͽ���.
//		double newRadOfBallOne = (ballOne.diameter / 2 * Math.sin(ballOne.radian) - ballTwo.y + ballOne.y)
//				/ (ballOne.diameter / 2 * Math.cos(ballOne.radian) - ballTwo.x + ballOne.x);
//		double newRadOfBallTwo = (ballTwo.diameter / 2 * Math.sin(ballTwo.radian) - ballOne.y + ballTwo.y)
//				/ (ballTwo.diameter / 2 * Math.cos(ballTwo.radian) - ballOne.x + ballTwo.x);

		// split(ballOne, newRadOfBallOne);
		// split(ballTwo, newRadOfBallTwo);
	//}

	public void run() {
		this.draw();
		while (this.isRunnable) {
			this.move();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
	}
}


