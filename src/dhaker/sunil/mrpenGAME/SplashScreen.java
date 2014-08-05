package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.Game;
import dhaker.sunil.mrpen.framwork.Graphics;
import dhaker.sunil.mrpen.framwork.Pixmap;
import dhaker.sunil.mrpen.framwork.Screen;

public class SplashScreen extends Screen {

    public  Pixmap splash;
    float timer = 0;

    public SplashScreen(Game game) {
        super(game);
        Graphics g = game.getGraphics();
        splash = g.newPixmap("flash.png", Graphics.PixmapFormat.ARGB8888);
    }

    @Override
    public void update(float deltaTime) {
        // TODO Auto-generated method stub
        timer += deltaTime;
        if (timer > 3f)
            game.setScreen(new MainMenuScreen(game));

    }

    @Override
    public void present(float deltaTime) {
        // TODO Auto-generated method stub
        game.getGraphics().drawFitTheScreen(splash);

    }


    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        splash.dispose();
    }
}
