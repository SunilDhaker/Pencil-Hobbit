package dhaker.sunil.mrpenGAME;
import dhaker.sunil.mrpen.framwork.*;
import dhaker.sunil.mrpen.framwork.Input.*;
import java.util.List;



/**
 * Created by Sunil on 28-03-2014.
 */
public class PlayScreen extends Screen {


    public enum GameState {
        Running, Paused, GameOver
    }
    private Game mrPenGame;
    private float timer = 0;
    private Pixmap pencil_deid , pause;
    private Pixmap pencil_3_jumping , tip ,numbers;
    private Pixmap pencil_1 , pencil_2 , z1 , z2 ,pause_button;
    GameState state = GameState.Running;
    World world;
    int oldScore = 0;
    String score = "0";
    private Pixmap background , base , gameOver;
    private Sound tui, warning;
    public PlayScreen(Game mrPenGame) {
        super(mrPenGame);
        tui = mrPenGame.getAudio().newSound("tui.mp3");
        warning = mrPenGame.getAudio().newSound("warning.mp3");
        switch (Settings.background) {
            case Settings.GREEN:
                background = game.getGraphics().newPixmap("background_green.png", Graphics.PixmapFormat.ARGB8888);
                break;
            case Settings.BLUE:
                background =  game.getGraphics().newPixmap("background_blue.png", Graphics.PixmapFormat.ARGB8888);
                break;
            case Settings.GRAY:
                background =  game.getGraphics().newPixmap("background_grey.png", Graphics.PixmapFormat.ARGB8888);
                break;
            case Settings.YELLOW:
                background =  game.getGraphics().newPixmap("background_yellow.png", Graphics.PixmapFormat.ARGB8888);
                break;
            default:
                background = game.getGraphics().newPixmap("background_blue.png", Graphics.PixmapFormat.ARGB8888);
        }
        Graphics g = game.getGraphics();
        pencil_1 = g.newPixmap("run1.png" , Graphics.PixmapFormat.ARGB8888);
        pencil_2  = g.newPixmap("run2.png" , Graphics.PixmapFormat.ARGB8888);
        pencil_3_jumping = g.newPixmap("jump.png" , Graphics.PixmapFormat.ARGB8888);
        base = g.newPixmap("base.png", Graphics.PixmapFormat.ARGB8888);
        tip = g.newPixmap("tip.png", Graphics.PixmapFormat.ARGB8888);
        numbers = g.newPixmap("score.png" , Graphics.PixmapFormat.ARGB8888);
        z1 = g.newPixmap("z1.png", Graphics.PixmapFormat.RGB565);
        z2 = g.newPixmap("z2.png", Graphics.PixmapFormat.RGB565);
        gameOver = g.newPixmap("game_over.png" , Graphics.PixmapFormat.ARGB8888);
        pause = g.newPixmap("pause.png" , Graphics.PixmapFormat.ARGB8888);
        pause_button = g.newPixmap("pause_button.png", Graphics.PixmapFormat.ARGB8888);
        pencil_deid  = g.newPixmap("pause_button.png", Graphics.PixmapFormat.ARGB8888);
        world = new World(game);
        warning.play(1f);
}

    @Override
    public void update(float deltaTime) {

        if (state == GameState.Running)
            oldScore = world.getScore();
        score = "" + oldScore;
        if (oldScore % 6 == 0) ;
        else {
            if (oldScore < 20)
                deltaTime = deltaTime + deltaTime * (float) oldScore / 75f;
            if (oldScore > 20)
                deltaTime = deltaTime + deltaTime;
        }
        timer = (timer + deltaTime) % 0.7f;
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if (state == GameState.Running) {
            updateRunning(deltaTime, touchEvents);
            return;
        }
        if (state == GameState.Paused) {
            updatePaused(deltaTime, touchEvents);
            return;
        }
        if (state == GameState.GameOver) {
            updateGameOver(deltaTime, touchEvents);
            return;
        }


    }


    private void updateRunning(float deltaTime, List<TouchEvent> touchEvents) {

        int size = touchEvents.size();
        List<KeyEvent> keyEvents = game.getInput().getKeyEvents();

        for (int i = 0; i < size; i++) {
            TouchEvent e = touchEvents.get(i);
            if (inBounds(e, 0, 0, 80, 80)) {
                if (Settings.soundEnabled)
                    tui.play(1);
                state = GameState.Paused;
                return;
            }
        }

        if ((size > 0 && world.dearPencil.getState() == Pencil.RUNNING)) {
            if (Settings.soundEnabled)
                tui.play(1);
            world.touchTheWorld();
        }

        world.update(deltaTime);
        if (world.isGameOver) {
            state = GameState.GameOver;
            Settings.addScore(oldScore);
            if (Settings.highscores[0] != 0)
                Settings.save(game.getFileIO());
        }
    }

    private void updatePaused(float deltaTime, List<TouchEvent> touchEvents) {

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent e = touchEvents.get(i);
            if (inBounds(e, 540, 340, 735, 430)) {
                if (Settings.soundEnabled) tui.play(1);
                game.setScreen(new PlayScreen(game));
                return;
            }
            if (inBounds(e, 270, 475, 465, 560)) {
                if (Settings.soundEnabled) tui.play(1);
                state = GameState.Running;
            }
            if (inBounds(e, 810, 475, 1000, 560)) {
                if (Settings.soundEnabled) tui.play(1);
                game.setScreen(new MainMenuScreen(game));
                return;
            }
        }
    }

    private void updateGameOver(float deltaTime, List<TouchEvent> touchEvents) {

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent e = touchEvents.get(i);
            if (inBounds(e, 350, 130, 560, 220)) {
                if (Settings.soundEnabled) tui.play(1);
                game.setScreen(new PlayScreen(game));
                return;
            }
            if (inBounds(e, 720, 130, 910, 220)) {
                if (Settings.soundEnabled) tui.play(1);
                game.setScreen(new MainMenuScreen(game));
                return;
            }

        }
    }

    @Override
    public void present(float deltaTime) {

        if (state == GameState.Running) {
            presentRunning(deltaTime);
            return;
        }
        if (state == GameState.Paused) {
            presentPaused(deltaTime);
            return;
        }
        if (state == GameState.GameOver) {
            presentGameOver(deltaTime);
            return;
        }

    }


    private void presentGameOver(float deltaTime) {

        Graphics g = game.getGraphics();
        g.drawFitTheScreen(background);
        int basePosition = 490;
        g.drawPixmap(pencil_deid, 40, 408);
        g.drawPixmap(base, -5, basePosition);
        g.drawFitTheScreen(gameOver);
        drawText(g, score, 700, 300);
        drawText(g, Settings.highscores[0] + "", 700, 370);

    }

    private void presentPaused(float deltaTime) {

        Graphics g = game.getGraphics();
        g.drawFitTheScreen(background);
        drawWorld(world, g);
        g.drawFitTheScreen(pause);
    }

    private void presentRunning(float deltaTime) {
        Graphics g = game.getGraphics();
        // g.drawPixmapInBound(background, 0, 0, g.getWidth(),
        // g.getHeight());
        g.drawFitTheScreen(background);
        drawWorld(world, g);

    }

    private void drawWorld(World world, Graphics g) {

        int basePosition = 490;

        if (world.dearPencil.getState() == Pencil.JUMPING) {
            g.drawPixmap(
                    pencil_3_jumping,
                    world.dearPencil.getPositionX(),
                    basePosition - world.dearPencil.height
                            + world.dearPencil.getPositionY() + 5
            );
        } else if (world.dearPencil.frametime > 0.5f) {
            g.drawPixmap(
                    pencil_1,
                    world.dearPencil.getPositionX(),
                    basePosition - world.dearPencil.height
                            + world.dearPencil.getPositionY() + 5
            );
        } else {
            g.drawPixmap(
                    pencil_2,
                    world.dearPencil.getPositionX(),
                    basePosition - world.dearPencil.height
                            + world.dearPencil.getPositionY() + 5
            );
        }

        if (timer > 0.35)
            g.drawPixmap(z1, 910, 0);
        else
            g.drawPixmap(z2, 910, 0);

        g.drawPixmap(pause_button, 030, 30);

        for (int i = 0; i < world.tips.size(); i++) {
            g.drawPixmap(tip, world.tips.get(i).getPositionX(),
                    basePosition - world.tips.get(i).height + 5);

        }

        g.drawPixmap(base, -5, basePosition);
        drawText(g, score, 870, 165);
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 30;
                srcWidth = 30;
            }

            g.drawPixmap(numbers, x, y, srcX, 0, srcWidth, 40);
            x += srcWidth;
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
    public void pause() {


    }

    @Override
    public void resume() {


    }

    @Override
    public void dispose() {

        Settings.addScore(oldScore);
        pencil_deid.dispose();
        pencil_1.dispose();
        pencil_2.dispose();
        pencil_3_jumping.dispose();
        base.dispose();
        tip.dispose();
        z1.dispose();
        z2.dispose();
        tui.dispose();
        gameOver.dispose();
        pause_button.dispose();
        pause.dispose();
        numbers.dispose();
        background.dispose();



    }
}
