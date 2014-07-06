package dhaker.sunil.mrpenGAME;

import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import dhaker.sunil.mrpen.framwork.Game;
import dhaker.sunil.mrpen.framwork.Graphics;
import dhaker.sunil.mrpen.framwork.Input;
import dhaker.sunil.mrpen.framwork.Screen;
import dhaker.sunil.mrpen.framwork.Input.TouchEvent;
import dhaker.sunil.mrpen.framwork.impl.AndroidGame;

public class MainMenuScreen extends Screen {
	public MainMenuScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 520, 320, 745, 520)) {
					if(Settings.soundEnabled)Assets.tui.play(1);
					game.setScreen(new PlayScreen(game));
				}
				if (inBounds(event, 440, 595, 595, 665)) {
					if(Settings.soundEnabled)Assets.tui.play(1);
					game.setScreen(new OptionScreen(game));
				}
				if (inBounds(event, 685, 595, 845, 665)) {
					if(Settings.soundEnabled)Assets.tui.play(1);
					game.setScreen(new CreditScreen(game));

				}
				if(inBounds(event, 1050,  530  ,1250, 680)){
					Uri uri = Uri.parse("market://details?id=dhaker.sunil.mrpenGAME");
					Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
					try {
					  ((AndroidGame)game).startActivity(goToMarket);
					} catch (ActivityNotFoundException e2) {
						((AndroidGame)game).startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=dhaker.sunil.mrpenGAME" )));
					}
				 return ;
				}

			}
		}
	}

	private boolean inBounds(Input.TouchEvent event, int x, int y, int x2,
			int y2) {
		if (event.x > x && event.x < x2 - 1 && event.y > y && event.y < y2 - 1)
			return true;
		else
			return false;
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawFitTheScreen(Assets.mainMenu);

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
