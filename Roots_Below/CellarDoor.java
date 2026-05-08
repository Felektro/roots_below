import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CellarDoor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CellarDoor extends Actor
{
    /**
     * Act - do whatever the CellarDoor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    public CellarDoor(){
        image = new GreenfootImage(75, 75);
        image.setColor(Color.LIGHT_GRAY);
        image.fillRect(0, 0, 75, 75);
        
        setImage(image);
    }
    
    public void act()
    {
        Actor player = getOneIntersectingObject(Player.class);
        
        if(player != null){
            Greenfoot.setWorld(new LoadingDungeon());
        }
    }
}
