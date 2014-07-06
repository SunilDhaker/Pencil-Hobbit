package dhaker.sunil.mrpenGAME;

import android.util.Log;

public class Pencil {

    public static final int RUNNING = 0;
    public static final int JUMPING = 1;
    public static final float GRAVITY = 90f;
    private float positionY = 0;
    private float velocityY = 0;
    private float positionX  =75 ;
    private int state = RUNNING;
    public float frametime = 0;

    int width = 101;
    int height =130;

    public Pencil() {


    }

    public void update(float deltaT) {

         {
        	 deltaT = 6f * deltaT ;
            velocityY = velocityY +  deltaT  * GRAVITY;
            positionY = positionY + velocityY * deltaT + 0.5f * GRAVITY * deltaT * deltaT ;
            state = JUMPING;
        }
         if (positionY >= 0) {
             positionY = 0;
             velocityY = 0;
             state = RUNNING;
         }
        frametime  = (frametime + deltaT ) % 1f ;
    }

    public void touch() {

        if(state == RUNNING){
          velocityY -= 200;
        }
    }

    public int getState(){

        return  state;
    }

    public int getPositionY(){
        return (int)positionY ;
    }

    public int getPositionX(){
        return (int) positionX ;
    }

}
