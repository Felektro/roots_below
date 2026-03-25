import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wall extends Actor
{
    /**
     * Act - do whatever the Wall wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage image;
    
    public Wall(){
        scaleImage();
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public void scaleImage(){
        image = new GreenfootImage("room_walls.png");
        
        image.scale((int)(image.getWidth()*10), (int)(image.getHeight()*10)); 
        
        setImage(image);
         
    }
}
