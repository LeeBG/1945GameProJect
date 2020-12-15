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
		/* 프로그램 시작시 보여질 메인 프레임을 만든다. */
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
	// Start 버튼을 눌렀을 때, 공튀기기 시작한다.
	public void actionPerformed(ActionEvent evt) {
		// 공 5개를 생성해서 각각 다른방향으로 이동시키게 만든다.
		for (int i = 0; i < 2; i++) {
			BallManager.balls.add(new Ball(0, 0, 20, i * (Math.PI / 5)));
			BallManager.balls.get(i).start();
		}
	}
}
class BallManager {
	public static ArrayList<Ball> balls = new ArrayList<Ball>();
}
//아마 안씀
class CloseBtnClickListener implements ActionListener {
	// Close 버튼을 눌렀을때 프로그램을 종료한다.
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

	// 원을 만들때 x,y 좌표, 지름, 이동방향(각도)를 초기화한다.
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
		// 원을 그린다.
		Graphics g = canvas.getGraphics();
		//g.fillOval(this.x, this.y, this.diameter, this.diameter);
		//g.dispose();
		ImageIcon icPlayer = new ImageIcon("image/playerR.png");
		
		//setIcon(icPlayer);
	}

	public void move() {
		// 기존의 원을 없앤다.
		Graphics g = this.canvas.getGraphics();
		g.setXORMode(this.canvas.getBackground());
		g.fillOval(this.x, this.y, this.diameter, this.diameter);

		// 원의 x, y 좌표를 이동시킨다
		this.x += dx;
		this.y += dy;

		// 벽에 부딪히면 입사각에 따라 원을 반사시킨다.
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
		// 다른원과 충돌하면 반지름을 반으로 나누고 둘로 쪼갠다.
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

		// 반지름의 합이 중점간의 거리보다 크거나 같으면 둘이 충돌한 것으로 간주한다.
		if (distance <= (this.diameter + ball.diameter) / 2) {
			double distanceXPrime = (this.x + this.dx) - (ball.x + ball.dx);
			double distanceYPrime = (this.y + this.dy) - (ball.y + ball.dy);
			double distancePrime = Math.sqrt(distanceXPrime * distanceXPrime + distanceYPrime * distanceYPrime);
			// 반지름의 합이 중점간의 거리보다 크거나 같더라도 처음 생성된 상황은 제외한다.
			if (distance > distancePrime) {
				return true;
			}
		}
		return false;
	}

	public void split(Ball ball, double rad) {
		double subtractedRadOfBall = rad - Math.PI / 4;
		double addedRadOfBall = rad + Math.PI / 4;
		// 반지름이 1보다 크면 기존 원은 사라지고 작은원 둘로 나뉜다.
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
		// 반지름이 1보다 작으면 사라진다.
		else {
			int indexOfBall = BallManager.balls.indexOf(ball);
			ball.isRunnable = false;
			BallManager.balls.remove(indexOfBall);
		}

	}

	//아마 안씀
//	public void splitTwoBall(Ball ballOne, Ball ballTwo) {
//		// ballOne 과 ballTwo의 충돌 이후 진행 방향을 벡터로 계산하였다.
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


