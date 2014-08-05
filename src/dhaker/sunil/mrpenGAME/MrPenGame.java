package dhaker.sunil.mrpenGAME;


import android.app.AlertDialog;
import android.content.DialogInterface;
import dhaker.sunil.mrpen.framwork.Music;
import dhaker.sunil.mrpen.framwork.Screen;
import dhaker.sunil.mrpen.framwork.impl.AndroidAudio;
import dhaker.sunil.mrpen.framwork.impl.AndroidGame;
import dhaker.sunil.mrpenGAME.LoadingScreen;

public class MrPenGame extends AndroidGame {

    Music bg_music;

    @Override
    public Screen getStartScreen() {
        bg_music = getAudio().newMusic("Background_2.ogg");
        bg_music.setLooping(true);
        return new SplashScreen(this);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit Game !")
                .setMessage("Are you sure you want to quit ?")
                .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (Settings.highscores[0] != 0)
                            Settings.save(getFileIO());
                        setScreen();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    public void setScreen() {
        setScreen(new FinishScreen(this));
    }


    @Override
    public void onResume() {
        super.onResume();
        if (bg_music != null) {
            bg_music.play();
        } else {
            bg_music = getAudio().newMusic("Background_2.ogg");
            bg_music.setLooping(true);
            bg_music.play();
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        if (bg_music != null) {
            bg_music.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bg_music != null) {
            bg_music.dispose();
        }
    }


}