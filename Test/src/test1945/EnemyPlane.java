package test1945;

import javax.swing.ImageIcon;

import lombok.Data;
@Data
public class EnemyPlane extends AirPlane{
	
	public EnemyPlane enemyPlane = this;
	private static final String TAG = "Enemy: ";
	private ImageIcon enemyIcon;
	private int x; 				// �� ���� ��ġ��ǥ
	private int y; 				// �� ���� ��ġ��ǥ
	private int sizeX; 			// ����� ������X
	private int sizeY; 			// ����� ������Y
	private int rand;

	public boolean isRight; 	// ���������� �����̴��� �ƴ����� ����
	public boolean isLeft; 		// �������� �����̴��� �ƴ����� ����
	public boolean isUp;		// �������� �����̴��� �ƴ����� ����
	public boolean isDown;		// �Ʒ������� �����̴��� �ƴ����� ����
	public boolean isCrush;		// �浹�������� �ƴ��� �Ǻ�
	
	public EnemyPlane(PlayerPlane playerPlane) { 	//������� ������
		init();
		switch (rand) {								//����� �ൿ ������ �ޱ� (init()�� ��������)
		case 0:					
			System.out.println(TAG+"�Ʒ���");
			moveDown();
			break;
		case 1:
			System.out.println(TAG+"���ʿ��� �Ʒ���");
			moveLeftDown();
			break;
		case 2:
			System.out.println(TAG+"�����ʿ��� �Ʒ���");
			moveRightDown();
			break;
		case 3:
			System.out.println(TAG+"�����ʿ��� ����");
			moveRightUp();
			break;
		case 4:
			System.out.println(TAG+"���ʿ��� ����");
			moveLeftUp();
			break;
		default:
			moveLeftDown();
			break;
		}
		crush(playerPlane);									//�浹 ��� ������ �Լ�(�� ����Ⱑ PLAY�� �浹�� ��ٷȴٰ� ����)
	}
	public EnemyPlane(int x,int y,int sizeX,int sizeY) {	//������ġ��ǥ, ������� ������(���� �̻��)
		init();
		
	}

	
	private void init() {								//�ʱ�ȭ
		rand = (int)(Math.random()*6);					//����� �ൿ ������ �ޱ� 
		x = 70*rand;									//X��ǥ�� ����
		y = 20*(int)(Math.random()*7+1);				//���̵� ����
		sizeX = 70;
		sizeY = 65;
		enemyIcon = new ImageIcon("images/PLANE2.png");
		setIcon(playerIcon);
		setLocation(x, y);
		setSize(sizeX, sizeY);
		isRight = false;
		isLeft = false;
		isUp = false;
		isDown = false;
		isCrush = false;
	}

	public void moveLeftDown() {					
		System.out.println(TAG + "���ʾƷ����̵�");
		int endY = getY();
		int endX = getX();
		setY(getY()-400);
		setX(getX()+400);							//�Ϻη� ȭ������� ���� ����
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()<endY) {	//���������� ������ ��ġ�� �������� ������				
						try {
							Thread.sleep(10);
							x--;
							y++;
							setLocation(x, y);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(y>520)
							return;
					}
				}
			}).start();
		}	
	}

	public void moveRightDown() {
		System.out.println(TAG + "�����ʾƷ����̵�");
		int endY = getY();
		int endX = getX();
		setY(getY()-400);
		setX(getX()-400);								//�Ϻη� ȭ������� ���� ����
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()<endY) {		//���������� ������ ��ġ�� �������� ������
						try {
							Thread.sleep(10);
							x++;
							y++;
							setLocation(x, y);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(y>520)
							return;
					}
				}
			}).start();
		}
	}

	public void moveRightUp() {
		System.out.println(TAG + "������ ���� �̵�");
		int endY = getY();
		int endX = getX();
		setY(getY()+400);
		setX(getX()-400);
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()>endY) {						
						try {
							Thread.sleep(10);
							y--;
							x++;
							setLocation(x, y);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(y>520)
							return;
					}
				}
			}).start();
		}
	}

	public void moveLeftUp() {
		System.out.println(TAG + "���� ���� �̵�");
		int endY = getY();
		int endX = getX();
		setY(getY()+400);
		setX(getX()+400);
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()>endY) {						
						try {
							Thread.sleep(10);
							y--;
							x--;
							setLocation(x, y);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(y>520)
							return;
					}
				}
			}).start();
		}
	}
	public void moveDown() {
		System.out.println(TAG + "�Ʒ����̵�");
		int end = getY();
		setY(getY()-200);
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()<end) {						
						try {
							Thread.sleep(10);
							y++;
							setLocation(x, y);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(y>520)
							return;
					}
				}
			}).start();
		}
	}
	public void shotToEnemy() {						//�߻� (�̱���)
//		new Missile(x,y);							//x,y��ǥ�� �޾Ƽ� ���������� ������ ���ư��� �̻��� ����
	}
	
	
	public void explosion(/*�̻��� ��ü*/) {		//���� �̹��� �̱���
		new Thread(new Runnable() {			
			@Override
			public void run() {
				for(int i=0;i<7;i++) {
					break;
				}
				
			}
		}).start();
	}
	
	public void explosionP(PlayerPlane p) {		//���� �̹��� �̱���
		new Thread(new Runnable() {			
			@Override
			public void run() {
				for(int i=0;i<7;i++) {
					p.playerIcon = new ImageIcon("")
				}
				
			}
		}).start();
	}
	
	public void crush(PlayerPlane playerPlane) { 			//�浹�� å���� ������ �� player �浹 ���
		new Thread(new Runnable() {
			@Override
			public void run() {				
				while (playerPlane.getLifecount() > 0) {
					isCrush = playerPlane.getX()+20>=getX()+20 && playerPlane.getX()+20<=getX()+getSizeX()-20&&												// �̹��� �簢���� ��ǥ (0,0)
							playerPlane.getY()+15>=getY()+15 && playerPlane.getY()+15<=getY()+getSizeY()-15 ||
							playerPlane.getX()+playerPlane.getSizeX()-20>=getX()+20 && playerPlane.getX()+playerPlane.getSizeX()-20<=getX()+getSizeX()-20&&	//(1,0)
							playerPlane.getY()+10>=getY()+15 && playerPlane.getY()+15<=getY()+getSizeY()-15 ||
							playerPlane.getX()+20>=getX()+20 && playerPlane.getX()+20<=getX()+getSizeX()-20 &&												//(0,1)
							playerPlane.getY()+playerPlane.getSizeY()-15>=getY()+15 && playerPlane.getY()+playerPlane.getSizeY()-15<=getY()+getSizeY()-15 ||
							playerPlane.getX()+playerPlane.getSizeX()-20>=getX()+20 && playerPlane.getX()+playerPlane.getSizeX()-20<=getX()+getSizeX()-20 &&//(1,1)
							playerPlane.getY()+playerPlane.getSizeY()-15>=getY()+15 && playerPlane.getY()+playerPlane.getSizeY()-15<=getY()+getSizeY()-15;
					try {
						if (isCrush) {								
							playerPlane.setLifecount(playerPlane.getLifecount()-1);
							System.out.println(TAG +"lifecount:"+playerPlane.getLifecount());
							playerPlane.setX(200);
							playerPlane.setY(520);
							playerPlane.repaint();
						}
							Thread.sleep(10);
						if(playerPlane.getLifecount() == 0) {
							Thread.sleep(1000);						//1�� �����
							System.exit(1);							//���α׷� ���� ����
						}
									
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
}
