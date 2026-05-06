import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingDungeon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingDungeon extends World
{
    /**
     * Constructor for objects of class LoadingDungeon.
     * 
     */
    
    float screenDelay = 5f;
    double timeScreenStarted;
    
    public LoadingDungeon()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 900, 1); 
        
        timeScreenStarted = System.currentTimeMillis();        
    }
    
    public void act(){
        if((System.currentTimeMillis() - timeScreenStarted)/1000.0 > screenDelay){
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
