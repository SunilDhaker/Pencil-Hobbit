package dhaker.sunil.mrpenGAME;


import com.google.ads.AdActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import dhaker.sunil.mrpen.framwork.Music;
import dhaker.sunil.mrpen.framwork.Pixmap;
import dhaker.sunil.mrpen.framwork.Screen;
import dhaker.sunil.mrpen.framwork.Sound;
import dhaker.sunil.mrpen.framwork.impl.AndroidGame;
import dhaker.sunil.mrpenGAME.LoadingScreen;

public class MrPenGame extends AndroidGame {
  
	@Override
    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }
	
	



	@Override
	
		public void onBackPressed() {
		    
		    
		    
		    new AlertDialog.Builder(this)
		        .setTitle("Exit Game !")
		        .setMessage("Are you sure you want to quit ?")
		        .setPositiveButton("Quit", new DialogInterface.OnClickListener()
		    {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		             
		        	if(Settings.highscores[0] != 0)
		  			  Settings.save(getFileIO());
		              setScreen();
		        	
		        }

		    })
		    .setNegativeButton("Cancel",new DialogInterface.OnClickListener()
		    {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	
		        }

		    })
		    .show();
		}
	public void setScreen(){
		setScreen(new FinishScreen(this));
	}





	


}