package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.Game;
import dhaker.sunil.mrpen.framwork.Graphics;
import dhaker.sunil.mrpen.framwork.Input;
import dhaker.sunil.mrpen.framwork.Screen;

import java.util.List;

/**
 * Created by SONY on 29-03-2014.
 */
public class OptionScreen extends Screen {

    public OptionScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {


        Graphics g = game.getGraphics();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {

                if (inBounds(event, 745, 430, 820, 495)) {
                	if(Settings.soundEnabled)
                	Assets.tui.play(0.5f);
                    Settings.background = Settings.YELLOW;

                } else if (inBounds(event, 830, 430, 905, 495)) {
                	if(Settings.soundEnabled)
                	Assets.tui.play(0.5f);
                    Settings.background = Settings.GREEN;

                } else if (inBounds(event, 915, 430, 990, 495)) {
                	if(Settings.soundEnabled)
                	Assets.tui.play(0.5f);
                    Settings.background = Settings.BLUE;

                } else if (inBounds(event, 660, 430, 730, 495)) {
                	if(Settings.soundEnabled)
                	Assets.tui.play(0.5f);
                    Settings.background = Settings.GRAY;

                } else if (inBounds(event,660,295 , 735 , 360)) {
                	if(Settings.soundEnabled)
                	Assets.tui.play(0.7f);
                    Settings.soundEnabled = true ;
                    Assets.bgAudio.play();

                } else if (inBounds(event,745,295 ,820 , 360)) {
                	
                    Settings.soundEnabled = false;
                    Assets.bgAudio.pause();
                    if(Settings.soundEnabled)
                    	Assets.tui.play(0.7f);
                } 
                else if (inBounds(event, 40, 580, 180, 685)) {
                	if(Settings.soundEnabled)
                	Assets.tui.play(1);
                    game.setScreen(new MainMenuScreen(game));

                } 

            }
        }

    }

    @Override
    public void present(float deltaTime) {

        Graphics g = game.getGraphics();
        g.drawFitTheScreen(Assets.options);

        if (Settings.background == Settings.GRAY) {
            g.drawPixmap(Assets.check, 660, 430);

        } else if (Settings.background == Settings.YELLOW) {
            g.drawPixmap(Assets.check, 745, 430);

        } else if (Settings.background == Settings.GREEN) {
            g.drawPixmap(Assets.check, 830, 430);


        } else if (Settings.background == Settings.BLUE) {
            g.drawPixmap(Assets.check, 915, 430);

        }
        if(Settings.soundEnabled){
        	g.drawPixmap(Assets.check, 660,295);
        }else if(!Settings.soundEnabled){
        	g.drawPixmap(Assets.check,745,295);
        }


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
