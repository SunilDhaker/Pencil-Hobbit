package dhaker.sunil.mrpenGAME;

import android.util.Log;
import dhaker.sunil.mrpen.framwork.Audio;
import dhaker.sunil.mrpen.framwork.Game;
import dhaker.sunil.mrpen.framwork.Graphics;
import dhaker.sunil.mrpen.framwork.Screen;
import dhaker.sunil.mrpen.framwork.Graphics.PixmapFormat;
import dhaker.sunil.mrpen.framwork.impl.AndroidAudio;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
    	super(game);

    }

    @Override
    public void update(float deltaTime) {
//        Graphics g = game.getGraphics();
//       try{
//        Assets.background_blue = game.getGraphics().newPixmap("background_blue.png",PixmapFormat.ARGB8888);
//        Assets.background_gray = game.getGraphics().newPixmap("background_grey.png",PixmapFormat.ARGB8888);
//        Assets.base = g.newPixmap("base.png" , PixmapFormat.ARGB8888);
//        Assets.pencil_1 = g.newPixmap("run1.png" , PixmapFormat.ARGB8888);
//        Assets.pencil_2  = g.newPixmap("run2.png" , PixmapFormat.ARGB8888);
//        Assets.pencil_3_jumping = g.newPixmap("jump.png" , PixmapFormat.ARGB8888);
//        Assets.base = g.newPixmap("base.png", PixmapFormat.ARGB8888);
//        Assets.tip = g.newPixmap("tip.png", PixmapFormat.ARGB8888);
//        Assets.numbers = g.newPixmap("score.png" , PixmapFormat.ARGB8888);
//        Assets.background_blue = g.newPixmap("background_blue.png",PixmapFormat.ARGB8888);
//        Assets.background_yellow = g.newPixmap("background_yellow.png",PixmapFormat.ARGB8888);
//        Assets.background_green = g.newPixmap("background_green.png", PixmapFormat.ARGB8888);
//        Assets.gameOver = g.newPixmap("game_over.png" ,PixmapFormat.ARGB8888);
//        Assets.pause = g.newPixmap("pause.png" ,PixmapFormat.ARGB8888);
//        Assets.pause_button = g.newPixmap("pause_button.png",PixmapFormat.ARGB8888);
//        Assets.options = g.newPixmap("options.png" ,PixmapFormat.ARGB8888);
//        Assets.mainMenu = g.newPixmap("welcome.png" ,PixmapFormat.ARGB8888);
//        Assets.credits = g.newPixmap("credits.png",PixmapFormat.ARGB8888);
//        Assets.pencil_deid = g.newPixmap("collision.png",PixmapFormat.ARGB8888);
//        Assets.check = g.newPixmap("check.png" , PixmapFormat.ARGB8888);
//        Assets.flash = g.newPixmap("flash.png", PixmapFormat.ARGB8888);
//        Assets.flash2 = g.newPixmap("flash2.png", PixmapFormat.ARGB8888);
//        Assets.warning = game.getAudio().newSound("warning.mp3");
//        Assets.z1 = g.newPixmap("z1.png", PixmapFormat.RGB565);
//        Assets.z2 = g.newPixmap("z2.png", PixmapFormat.RGB565);
//
//        Assets.bgAudio = game.getAudio().newMusic("Background_2.ogg");
//        Assets.bgAudio.setLooping(true);
//        Assets.tui = game.getAudio().newSound("tui.mp3");
//        Assets.collisionSound = game.getAudio().newSound("collision_1.mp3");
//
//
//        //Assets.hit = game.getAudio().newSound("hit.ogg");
//        //Assets.attack = game.getAudio().newSound("eat.ogg");
//        //Assets.jump = game.getAudio().newSound("bitten.ogg");
//        Settings.load(game.getFileIO());
//        if(Settings.soundEnabled)
//        Assets.bgAudio.play();
//        Assets.bgAudio.setVolume(0.6f);
//        game.setScreen(new SplashScreen(game));
//       }
//
//        catch(OutOfMemoryError error)  {
//             System.gc();
//             Runtime.getRuntime().gc();
//             game.setScreen(new LoadingScreen(game));
//
//          }
    }

    @Override
    public void present(float deltaTime) {
            
            
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}