package dhaker.sunil.mrpenGAME;

import dhaker.sunil.mrpen.framwork.Graphics;

public class Tip {

    private float positionX = 1013;
    public static float velocityX = -250;
    private float positionY = 0;
    public boolean isInsideWorld = true;
    int width = 34 ;
    int height = 35 ;

    public Tip() {
    }

    public void update(float deltaT) {

        positionX = positionX + deltaT * velocityX;
        if (positionX < 0)
            isInsideWorld = false;

    }

    public int getPositionX() {
        return (int) positionX;
    }

    public int getPositionY(){
        return (int) positionY;
    }

    public static void increaseSpeed(){

    if(velocityX > -400)
     velocityX = (velocityX - 1) ;
    }

}
