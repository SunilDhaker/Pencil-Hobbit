package dhaker.sunil.mrpenGAME;

import java.util.ArrayList;
import java.util.Random;

import android.util.Log;

public class World {


    public boolean isGameOver = false;

    public Pencil dearPencil;
    public ArrayList<Tip> tips = new ArrayList<Tip>();
    private int score = 0;

    Random rand = new Random();

    private float fireTimeCounter = 0;
    private float delta;


    public World() {

        dearPencil = new Pencil();
        delta = rand.nextFloat();

    }


    public void fireTip() {

        tips.add(new Tip());
    }


    public void update(float deltaT) {

        if (isGameOver){
            return;
        }


        dearPencil.update(deltaT);
        for (int i = 0; i < tips.size(); i++) {

            if (!tips.get(i).isInsideWorld)
                tips.remove(i);
            break;
        }

        //removing  the tip which are outside the world ..
        for (int i = 0 ; i < tips.size() ;i ++) {
            tips.get(i).update(deltaT);
            if (!tips.get(i).isInsideWorld) {
                //tips.remove(tip);
                score += 1;
               if(score % 6 == 0)
            	 Assets.warning.play(1f);
                Tip.increaseSpeed();
            }
        }


        fireTimeCounter += deltaT;
        if (fireTimeCounter > 1  + delta) {
            fireTip();
            fireTimeCounter = 0;
            delta = rand.nextFloat();
        }
        checkCollision();
    }


    private void checkCollision() {

        for (int i = 0 ; i < tips.size() ;i ++) {
            if ( ( tips.get(i).getPositionX())  <  (dearPencil.getPositionX()) + (dearPencil.width -20)  &&  tips.get(i).getPositionX() > dearPencil.getPositionX() - ( tips.get(i).width - 15 )) {
                if(  -dearPencil.getPositionY()  <  ( tips.get(i).height -5) ){
                isGameOver = true ;
                if(Settings.soundEnabled)Assets.collisionSound.play(1.5f);
                }
            }

        }
    }


    public void touchTheWorld() {
        dearPencil.touch();
    }

    public int getScore() {
        return score;
    }
}
