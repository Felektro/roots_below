import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SeedPedestal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SeedPedestal extends Actor
{
    /**
     * Act - do whatever the SeedPedestal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    
    
    boolean firstTime = true;
    public SeedPedestal(){
        image = new GreenfootImage(150, 150);
        image.setColor(Color.GREEN);
        image.fillRect(0, 0, 150, 150);
        
        setImage(image);
        
    }
    
    public void act()
    {
        //
    }
}
