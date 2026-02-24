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
    
    private GameManager gm;
    
    private GreenfootImage image;
    
    public enum DoorType {
        LEFT, RIGHT, UP, DOWN
    }
    
    public DoorType transition;    
    
    public Door(DoorType transition, GameManager gm){
        scaleImage();
        
        this.transition = transition;
        this.gm = gm;
        
        //makeVisible(false);
    }
    public void scaleImage(){
        
        image = getImage();
        image.scale((int)(image.getWidth()*0.3), (int)(image.getHeight()*0.3));
        
        setImage(image);
         
    }
    public void act()
    {
        Actor player = this.getOneIntersectingObject(Player.class);
        
        if(player != null){
            //System.out.println("touched the door " + transition);
            gm.changeRoom(transition, player);
        }
    }
    
    public void makeVisible(boolean bool){
        if(bool) {image.setTransparency(255);}
        else {image.setTransparency(0);}
        setImage(image);
    }
}
