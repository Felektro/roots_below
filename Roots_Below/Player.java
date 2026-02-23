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
    
    public int playerSpeed = 3;
    
    public void act()
    {
        movement();
    }
    
    public void movement(){
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - playerSpeed, getY());
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + playerSpeed, getY());
        }
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - playerSpeed);
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + playerSpeed);
        }
    }
}
