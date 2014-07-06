package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.Game;
import dhaker.sunil.mrpen.framwork.Graphics;
import dhaker.sunil.mrpen.framwork.Input;
import dhaker.sunil.mrpen.framwork.Screen;

import java.util.List;

/**
 * Created by SONY on 29-03-2014.
 */
public class CreditScreen extends Screen {
    public CreditScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            if (inBounds(event, 40, 580, 180, 685)) {
            	if(Settings.soundEnabled)Assets.tui.play(1);
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

    @Override
    public void present(float deltaTime) {

        Graphics g = game.getGraphics();
        g.drawFitTheScreen(Assets.credits);
    }

    @Override
    public void pause() {        
        Settings.save(game.getFileIO());
        if(Assets.bgAudio.isPlaying())
    		Assets.bgAudio.pause();

    }

    @Override
    public void resume() {

    	if(!Assets.bgAudio.isPlaying()){
    		if(Settings.soundEnabled)Assets.bgAudio.play();
    	}
    }

    @Override
    public void dispose() {
    	 if(Assets.bgAudio.isPlaying())
     		Assets.bgAudio.pause();
    }


    private boolean inBounds(Input.TouchEvent event, int x, int y, int x2, int y2) {
        if (event.x > x && event.x < x2 - 1 &&
                event.y > y && event.y < y2 - 1)
            return true;
        else
            return false;
    }


}
