# 1945GameProJect
#
# 1945 팀프로젝트 저장소
## PPT   

## 시연영상   

<iframe width="640" height="360" src="https://www.youtube.com/watch?v=OC5RTb8v3-s" frameborder="0" gesture="media" allowfullscreen=""></iframe>

## 조작   
* 위: ↑   
* 아래: ↓   
* 좌: ←   
* 우: →   
* 공격: SPACE   
* 총알강화 : 숫자 1,2,3,4   
* 선택패널 전환 : ENTER

## 패널 전환
```java
//In gameframe.class

public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					change("selectAPL");
					break;
/*....이후생략*/
//------------------------------------------------------------------------
// 패널 바꾸기 함수
	public void change(String panelName) {
		if (panelName.equals("gameTitle")) {
			gameTitle = new GameTitle(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(gameTitle);
			revalidate();
			repaint();
		} else if (panelName.equals("selectAPL")) {
			selectAPL = new SelectAPL(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(selectAPL);
			revalidate();
			repaint();
		} else if (panelName.equals("gameMap")) {
			gamePanel = new GamePanel();
			getContentPane().removeAll();
			getContentPane().add(gamePanel);
			revalidate();
			repaint();
		} else {
			gameTitle = null;
			selectAPL = null;
			gamePanel = null;
			isgame = false;
			getContentPane().removeAll();
			revalidate();
			repaint();
		}
	}
```

## 플레이어
* 게임 시작 시 3개의 비행기를 선택할 가능   
* 숫자 1,2,3,4 버튼으로 무기 강화(아이템 기능 미적용)  
* 플레이어가 발사한 총알과 총알에 맞은 적, 맵에서 벗어난 총알 삭제 기능   
* 충돌 판정으로 인해 적의 체력이 0이되면 적 폭파


## 적
* 게임시작 후 일정 영역에 도달하면 등장  
* 플레이어와 충돌하면 플레이어 폭발
* 플레이어와 충돌하면 자신도 폭발


## 보스
* 맵의 마지막 부분에 등장
* 복잡한 패턴의 공격을 하며 많은 총알을 내뿜는다.


## 총알
* 플레이어가 쏜 총알은 적과 충돌시 적을 폭발시킨다.
* 적이 쏜 총알은 플레이어와의 충돌시 플레이어를 폭발시키고 죽인다.
* 보스에게 최대체력 이상의 횟수로 충돌하면 폭발한다.


## 맵 이동
* 맵은 스레드로 클리핑영역을 실시간으로 바꾸면서 동작한다.
* 보스에 도달하면 맵을 연장한다.
```java
public GamePanel() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						isgame = true;
						setLayout(null);
						// lifeLaInit();

						// 플레이어 생명 표시
						// 이걸 너무 막 넣었네.. 매번 쓰레드 돌아갈 때마다 연산하고 new하고 추가하고, add하면 느려집니다..
//						gamePanel.add(laLifecount);
//						gamePanel.add(laLifecount2);
//						gamePanel.add(laLifecount3);

						stageY++;
						bossStageBY1++;
						bossStageBY2++;

						if (stageY > bossStageImg.getHeight(null)) {
							stageY = bossStageImg.getHeight(null);
							if (bossStageBY1 > (bossStageImg.getHeight(null) - 1)) {
								bossStageBY1 = -(bossStageImg.getHeight(null) - 1);
							}
							if (bossStageBY2 > (bossStageImg.getHeight(null) - 1)) {
								bossStageBY2 = -(bossStageImg.getHeight(null) - 1);
							}
						}
						try {
							// lifeCounting();
							enemyBatch();		  //적 배치 알고리즘
							crushBorder();		//플레이어 끝지점 충돌함수
							appear++;
							repaint();
							Thread.sleep(3);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}
			}).start();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			g.drawImage(stageImg, 0, stageY, null);
			g.drawImage(bossStageImg, 0, bossStageBY1, null);
			g.drawImage(bossStageImg, 0, bossStageBY2, null);

			for (int i = 0; i < enemyUnits.size(); i++) { // null이 아니면 그려라
				if (enemyUnits.get(i) != null) {
					enemyUnits.get(i).enemyUpdate(g);
				}
			}

			if (player != null) {
				player.playerUpdate(g);
			}

			if (boss != null) {
				boss.bossUpdate(g);
			}

			repaint();
		}
```


## 충돌판정 연산
<div>
	<img width="100%" src = "https://user-images.githubusercontent.com/44068819/102435468-03ce0600-405a-11eb-9a81-d2f1b628dbde.png">	
</div>
* 객체이미지의 일정부분이 다른 객체 이미지의 범위안에
* 들어가는 것을 스레드로 지속적으로 관찰한다.
* 충돌이 일어나면 폭발이미지로 잠시 변환되며 사라진다.
* 플레이어의 충돌일 경우 폭발 후 초기의 위치와 이미지로 돌아간다.

```java


//기체 충돌 연산
if (Math.abs(
				((player.getX() - 11) + player.getWidth() / 3) - (x + width / 3)) < (width / 3 + player.getWidth() / 3)
				&& Math.abs(((player.getY() - 5) + player.getHeight() / 3) - (y + height / 3)) < (height / 3
						+ player.getHeight() / 3)) {
			collision = true;
		} else {
			collision = false;
		}
    
    
    
// 플레이어 총알이 적의 비행기에 닿았는지 탐지하는 연산
	static boolean Crash(int x1, int y1, int x2, int y2, int w1, int h1, int w2, int h2) {
		// x,y : 위치값 , w,h : 이미지의 높이와 길이.
		boolean result = false;
		if (Math.abs((x1 + w1 / 2) - (x2 + w2 / 2)) < (w2 / 2 + w1 / 2)
				&& Math.abs((y1 + h1 / 2) - (y2 + h2 / 2)) < (h2 / 2 + h1 / 2))
			result = true;
		else
			result = false;

		return result;
	}

```
