import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class point here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class point extends Actor
{
    /**
     * Act - do whatever the point wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    public point(){
        image = new GreenfootImage(5, 5);
        image.setColor(Color.RED);
        image.fillRect(0, 0, 5, 5);
        
        setImage(image);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
