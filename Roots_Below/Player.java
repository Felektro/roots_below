import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public int playerSpeed = 5;
    public HoeWeapon hoeWeapon;
    
    int hoeTurn;
    boolean usingHoe;
    int lastSwing = 1;
    int coneAngle = 120;
    
    float swingDelay = 0.3f;
    double timeLastSwing;
    public Player(HoeWeapon hoe){
        hoeWeapon = hoe;
        hoeWeapon.turn(coneAngle/2);
    }
    
    public void act()
    {
        movement();
        hoeUse();
    }
    
    public void movement(){
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - playerSpeed, getY());
            if(!usingHoe) {hoeWeapon.setRotation(180 + coneAngle/2*lastSwing);}
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + playerSpeed, getY());
            if(!usingHoe) {hoeWeapon.setRotation(0 + coneAngle/2*lastSwing);}
        }
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - playerSpeed);
            if(!usingHoe) {hoeWeapon.setRotation(270 + coneAngle/2*lastSwing);}
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + playerSpeed);
            if(!usingHoe) {hoeWeapon.setRotation(90 + coneAngle/2*lastSwing);}
        }
    }
    
    public void hoeUse(){
        hoeWeapon.setLocation(getX(), getY());
        int turnAngle = 5;
        if (Greenfoot.isKeyDown("space") && !usingHoe && (System.currentTimeMillis() - timeLastSwing)/1000.0 > swingDelay) {
            usingHoe = true;
            hoeTurn = coneAngle;
        }
        if(usingHoe){
            hoeWeapon.turn(turnAngle*-1 * lastSwing);
            hoeTurn = hoeTurn - turnAngle;
            if(hoeTurn <= 0){
                usingHoe = false;
                lastSwing *= -1;
                timeLastSwing = System.currentTimeMillis();
            }
        }
    }
}
