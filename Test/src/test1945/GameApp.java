package test1945;

public class GameApp {
	private GameApp gameApp = this;
	private PlayerPlane playerPlane;
	private GameFrame map;

	public GameApp() {
		init();
	}

	private void init() {
		new GameFrame();

	}

	public static void main(String[] args) {
		new GameApp();
	}

}
