package test1945;

public class GameApp {
	private GameApp gameApp = this;
	private PlayerPlane playerPlane;
	private GameFrame map;

	public GameApp() {
		init();
	}

	private void init() {
		map = new GameFrame();

	}

	public static void main(String[] args) {
//		new Start();		
//		new GameApp();
	}

}
