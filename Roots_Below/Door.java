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
    private GreenfootImage closed;
    
    public enum DoorType {
        LEFT, RIGHT, UP, DOWN
    }
    
    public DoorType transition;    
    
    public Door(DoorType transition, GameManager gm){
        this.transition = transition;
        this.gm = gm;
        
        //System.out.println(transition);
        
        scaleImage();
        //makeVisible(false);
    }
    public void scaleImage(){
        switch (transition){
            case DoorType.UP:
                image = new GreenfootImage("door_top.png");
                closed = new GreenfootImage("door_top_closed.png");
                break;
            case DoorType.RIGHT:
                image = new GreenfootImage("door_right.png");
                closed = new GreenfootImage("door_right_closed.png");
                break;
            case DoorType.DOWN:
                image = new GreenfootImage("door_bot.png");
                closed = new GreenfootImage("door_bot_closed.png");
                break;
            case DoorType.LEFT:
                image = new GreenfootImage("door_left.png");
                closed = new GreenfootImage("door_left_closed.png");
                break;
        }
        
        image.scale((int)(image.getWidth()*10), (int)(image.getHeight()*10));
        closed.scale((int)(closed.getWidth()*10), (int)(closed.getHeight()*10));
        
        setImage(image);
         
    }
    public void act()
    {
        /*
        Actor player = this.getOneIntersectingObject(Player.class);
        
        if(player != null){
            //System.out.println("touched the door " + transition);
            gm.changeRoom(transition, player);
        }
        */
    }
    
    public void makeVisible(boolean bool){
        if(bool) {image.setTransparency(255);}
        else {image.setTransparency(0);}
        setImage(image);
    }
    public void close(boolean bool){
        if(bool) {setImage(closed);}
        else {setImage(image);}
    }
}
