package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.*;

import java.util.List;

/**
 * Created by SONY on 29-03-2014.
 */
public class OptionScreen extends Screen {

    private Pixmap option ;
    private Pixmap check ;
    private Sound tui;
    public OptionScreen(Game game) {
        super(game);
        option = game.getGraphics().newPixmap("options.png", Graphics.PixmapFormat.ARGB8888);
        check = game.getGraphics().newPixmap("check.png" , Graphics.PixmapFormat.ARGB8888);
        tui = game.getAudio().newSound("tui.mp3");
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
                	tui.play(0.5f);
                    Settings.background = Settings.YELLOW;

                } else if (inBounds(event, 830, 430, 905, 495)) {
                	if(Settings.soundEnabled)
                	tui.play(0.5f);
                    Settings.background = Settings.GREEN;

                } else if (inBounds(event, 915, 430, 990, 495)) {
                	if(Settings.soundEnabled)
                	tui.play(0.5f);
                    Settings.background = Settings.BLUE;

                } else if (inBounds(event, 660, 430, 730, 495)) {
                	if(Settings.soundEnabled)
                	tui.play(0.5f);
                    Settings.background = Settings.GRAY;

                } else if (inBounds(event,660,295 , 735 , 360)) {
                	if(Settings.soundEnabled)
                	tui.play(0.7f);
                    Settings.soundEnabled = true ;

                } else if (inBounds(event,745,295 ,820 , 360)) {
                	
                    Settings.soundEnabled = false;
                    if(Settings.soundEnabled)
                    	tui.play(0.7f);
                } 
                else if (inBounds(event, 40, 580, 180, 685)) {
                	if(Settings.soundEnabled)
                	tui.play(1);
                    game.setScreen(new MainMenuScreen(game));

                } 

            }
        }

    }

    @Override
    public void present(float deltaTime) {

        Graphics g = game.getGraphics();
        g.drawFitTheScreen(option);

        if (Settings.background == Settings.GRAY) {
            g.drawPixmap(check, 660, 430);

        } else if (Settings.background == Settings.YELLOW) {
            g.drawPixmap(check, 745, 430);

        } else if (Settings.background == Settings.GREEN) {
            g.drawPixmap(check, 830, 430);


        } else if (Settings.background == Settings.BLUE) {
            g.drawPixmap(check, 915, 430);

        }
        if(Settings.soundEnabled){
        	g.drawPixmap(check, 660,295);
        }else if(!Settings.soundEnabled){
        	g.drawPixmap(check,745,295);
        }


    }

    @Override
    public void pause() {        
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        tui.dispose();
        option.dispose();
        check.dispose();
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int x2, int y2) {
        if (event.x > x && event.x < x2 - 1 &&
                event.y > y && event.y < y2 - 1)
            return true;
        else
            return false;
    }

}
