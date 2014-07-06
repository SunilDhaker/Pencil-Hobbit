package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.Game;
import dhaker.sunil.mrpen.framwork.Screen;

public class SplashScreen extends Screen{

	
	float timer = 0 ;
	public SplashScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		timer += deltaTime ;
		if(timer > 3f)
			game.setScreen(new MainMenuScreen(game));
		
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		game.getGraphics().drawFitTheScreen(Assets.flash);
		
	}


	@Override
	public void pause() {
		Settings.save(game.getFileIO());
		if (Assets.bgAudio.isPlaying())
			Assets.bgAudio.pause();

	}

	@Override
	public void resume() {

		if (!Assets.bgAudio.isPlaying()) {
			if (Settings.soundEnabled)
				Assets.bgAudio.play();
		}

	}

	@Override
	public void dispose() {
		if (Assets.bgAudio.isPlaying())
			Assets.bgAudio.pause();
	       }
}
