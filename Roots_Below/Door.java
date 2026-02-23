import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door extends Actor
{
    /**
     * Act - do whatever the Door wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public enum DoorType {
        LEFT, RIGHT, UP, DOWN
    }
    
    public DoorType transition;    
    
    public Door(DoorType transition){
        GreenfootImage image;
        image = getImage();
        image.scale((int)(image.getWidth()*0.3), (int)(image.getHeight()*0.3));
        
        setImage(image);
        
        this.transition = transition;
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
