package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.Game;
import dhaker.sunil.mrpen.framwork.Graphics;
import dhaker.sunil.mrpen.framwork.Pixmap;
import dhaker.sunil.mrpen.framwork.Screen;
import dhaker.sunil.mrpen.framwork.impl.AndroidGame;

public class FinishScreen extends Screen{
    private Pixmap flash2 ;
	private float timer = 0 ;
	public FinishScreen(Game game) {
		super(game);
        flash2 =  game.getGraphics().newPixmap("flash.png", Graphics.PixmapFormat.ARGB8888);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		 timer = timer + deltaTime;
		if(timer > 2f)
            ((AndroidGame) game).finish();
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
			game.getGraphics().drawFitTheScreen(flash2);
				
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		flash2.dispose();
	}

}
