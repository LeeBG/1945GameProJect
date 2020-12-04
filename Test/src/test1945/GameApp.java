package test1945;

public class GameApp{
	private GameApp gameApp = this;
	private PlayerPlane playerPlane;
	private Map map;
	
	public GameApp() {
		init();
	}
	private void init() {
		map = new Map();
		
	}
	public static void main(String[] args) {
//		new Start();		게임 시작
//		new GameApp();
	}

}

