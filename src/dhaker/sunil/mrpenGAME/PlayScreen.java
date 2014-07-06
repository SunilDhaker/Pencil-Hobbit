package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.*;
import dhaker.sunil.mrpen.framwork.Input.*;
import dhaker.sunil.mrpen.framwork.impl.AndroidGame;

import java.util.List;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by Sunil on 28-03-2014.
 */
public class PlayScreen extends Screen {

	private Game mrPenGame;
	private float timer  = 0;

	public enum GameState {
		Running, Paused, GameOver
	}

	GameState state = GameState.Running;
	World world;
	int oldScore = 0;
	String score = "0";
	Pixmap background;

	public PlayScreen(Game mrPenGame) {
		super(mrPenGame);
		world = new World();
		 Assets.warning.play(1f);
	}

	
	
	
	
	
	@Override
	public void update(float deltaTime) {
         
		if(state == GameState.Running)
		oldScore = world.getScore();
		score = "" + oldScore;
		if(oldScore % 6 == 0);
		else{ if(oldScore < 20)
		deltaTime = deltaTime + deltaTime * (float) oldScore / 75f;
		if(oldScore > 20)
	      deltaTime = deltaTime + deltaTime ;
		}

	   
		timer = (timer + deltaTime) % 0.7f ;
		switch (Settings.background) {
		case Settings.GREEN:
			background = Assets.background_green;
			break;
		case Settings.BLUE:
			background = Assets.background_blue;
			break;
		case Settings.GRAY:
			background = Assets.background_gray;
			break;
		case Settings.YELLOW:
			background = Assets.background_yellow;
			break;
		default:
			background = Assets.background_blue;

		}

		deltaTime = deltaTime;
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		if (state == GameState.Running){
			updateRunning(deltaTime, touchEvents);
			return;
		}
		if (state == GameState.Paused){
			updatePaused(deltaTime, touchEvents);
			return;
		}
       if (state == GameState.GameOver){
			updateGameOver(deltaTime, touchEvents);
			return;
	    }

       
		
	}

	
	
	
	private void updateRunning(float deltaTime, List<TouchEvent> touchEvents) {
		
		int size = touchEvents.size();
		List<KeyEvent> keyEvents = game.getInput().getKeyEvents();
		
		for (int i = 0; i < size; i++) {
			TouchEvent e = touchEvents.get(i);
			if (inBounds(e, 0, 0, 80, 80)){
				if(Settings.soundEnabled)
				Assets.tui.play(1);			
			state = GameState.Paused;
			return;
			}
		}

		if ((size > 0 && world.dearPencil.getState() == Pencil.RUNNING )) {
			if(Settings.soundEnabled)
			Assets.tui.play(1);
			world.touchTheWorld();
		}

		world.update(deltaTime);
		if (world.isGameOver){
			state = GameState.GameOver;
			Settings.addScore(oldScore);
			if(Settings.highscores[0] != 0)
			  Settings.save(game.getFileIO());
		}
	}

	private void updatePaused(float deltaTime, List<TouchEvent> touchEvents) {

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent e = touchEvents.get(i);
			if (inBounds(e, 540, 340, 735, 430)) {
				if(Settings.soundEnabled)Assets.tui.play(1);
				game.setScreen(new PlayScreen(game));
				return;
			}
			if (inBounds(e, 270, 475, 465, 560)) {
				if(Settings.soundEnabled)Assets.tui.play(1);
				state = GameState.Running;				
			}
			if (inBounds(e, 810, 475, 1000, 560)) {
				if(Settings.soundEnabled)Assets.tui.play(1);
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
				if(Settings.soundEnabled)Assets.tui.play(1);
				game.setScreen(new PlayScreen(game));
				return;
			}
			if (inBounds(e, 720, 130, 910, 220)) {
				if(Settings.soundEnabled)Assets.tui.play(1);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
			
		}
	}

	@Override
	public void present(float deltaTime) {

		if (state == GameState.Running){
			presentRunning(deltaTime);
			return;
		}
		if (state == GameState.Paused){
			presentPaused(deltaTime);
			return;
		}
		if (state == GameState.GameOver){
			presentGameOver(deltaTime);
			return;
		}

	}

	
	
	
	private void presentGameOver(float deltaTime) {

		Graphics g = game.getGraphics();
		g.drawFitTheScreen(background);
		int basePosition = 490;
		g.drawPixmap(Assets.pencil_deid, 40, 408);
		g.drawPixmap(Assets.base, -5, basePosition);
		g.drawFitTheScreen(Assets.gameOver);
		drawText(g, score, 700, 300);
		drawText(g, Settings.highscores[0] + "", 700, 370);

	}

	private void presentPaused(float deltaTime) {

		Graphics g = game.getGraphics();
		g.drawFitTheScreen(background);
		drawWorld(world, g);
		g.drawFitTheScreen(Assets.pause);
	}

	private void presentRunning(float deltaTime) {
		Graphics g = game.getGraphics();
		// g.drawPixmapInBound(Assets.background, 0, 0, g.getWidth(),
		// g.getHeight());
		g.drawFitTheScreen(background);
		drawWorld(world, g);

	}
	
	private void drawWorld(World world, Graphics g) {

		int basePosition = 490;

		if (world.dearPencil.getState() == Pencil.JUMPING) {
			g.drawPixmap(
					Assets.pencil_3_jumping,
					world.dearPencil.getPositionX(),
					basePosition - world.dearPencil.height
							+ world.dearPencil.getPositionY() + 5);
		} else if (world.dearPencil.frametime > 0.5f) {
			g.drawPixmap(
					Assets.pencil_1,
					world.dearPencil.getPositionX(),
					basePosition - world.dearPencil.height
							+ world.dearPencil.getPositionY() + 5);
		} else {
			g.drawPixmap(
					Assets.pencil_2,
					world.dearPencil.getPositionX(),
					basePosition - world.dearPencil.height
							+ world.dearPencil.getPositionY() + 5);
		}
		
		if(timer > 0.35)
			g.drawPixmap(Assets.z1, 910, 0);
		else
			g.drawPixmap(Assets.z2, 910, 0);

		g.drawPixmap(Assets.pause_button, 030, 30);

		for (int i = 0; i < world.tips.size(); i++) {
			g.drawPixmap(Assets.tip, world.tips.get(i).getPositionX(),
					basePosition - world.tips.get(i).height + 5);

		}

		g.drawPixmap(Assets.base, -5, basePosition);
		drawText(g, score, 870 ,165  );
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

			g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 40);
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
		if (Assets.bgAudio.isPlaying())
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

		if (Assets.bgAudio.isPlaying())
			Assets.bgAudio.pause();

		Settings.addScore(oldScore);

	}
}
