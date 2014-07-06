package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.Game;
import dhaker.sunil.mrpen.framwork.Screen;
import dhaker.sunil.mrpen.framwork.impl.AndroidGame;

public class FinishScreen extends Screen{

	private float timer = 0 ;
	public FinishScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		 timer = timer + deltaTime;
		 if(timer > 2f){
		 System.gc();
         Assets.bgAudio.dispose();
         Assets.background_blue.dispose();
         Assets.background_yellow.dispose();
         Assets.background_gray.dispose();
         Assets.mainMenu.dispose();
         Assets.numbers.dispose();
         Assets.pencil_1.dispose();
         Assets.pencil_2.dispose();
         Assets.pencil_3_jumping.dispose();
         Assets.tip.dispose();
         Assets.base.dispose();
         
     
          Assets.background_green.dispose();
          Assets.pencil_deid.dispose();
          Assets.credits.dispose();
          Assets.gameOver.dispose();
          Assets.pause.dispose();
     	  Assets.pause_button.dispose();
     	  Assets.check.dispose();
     	  Assets.tui.dispose();
     	  Assets.collisionSound.dispose();
      	  Assets.flash.dispose();
     	 
     	
     	 Assets.warning.dispose();
     	 Assets.z1.dispose();
     	 Assets.z2.dispose();
     	 System.gc();
     	 ((AndroidGame)game).finish();}
		
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
			game.getGraphics().drawFitTheScreen(Assets.flash2);
				
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
		
	}

}
