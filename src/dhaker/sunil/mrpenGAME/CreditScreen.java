package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.*;

import java.util.List;

/**
 * Created by SONY on 29-03-2014.
 */
public class CreditScreen extends Screen {
    Sound tui;
    Pixmap credits;

    public CreditScreen(Game game) {
        super(game);
        tui = game.getAudio().newSound("tui.mp3");
        credits = game.getGraphics().newPixmap("credits.png", Graphics.PixmapFormat.ARGB8888);
    }

    @Override
    public void update(float deltaTime) {

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            if (inBounds(event, 40, 580, 180, 685)) {
                if (Settings.soundEnabled) tui.play(1);
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

    @Override
    public void present(float deltaTime) {

        Graphics g = game.getGraphics();
        g.drawFitTheScreen(credits);
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

    }


    private boolean inBounds(Input.TouchEvent event, int x, int y, int x2, int y2) {
        if (event.x > x && event.x < x2 - 1 &&
                event.y > y && event.y < y2 - 1)
            return true;
        else
            return false;
    }


}
