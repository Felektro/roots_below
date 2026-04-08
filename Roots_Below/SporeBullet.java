import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SporeBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SporeBullet extends Actor
{
    /**
     * Act - do whatever the SporeBullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    Player playerObject;

    int wallWidth = 15 * 10;
    
    public int hitboxRadius = 15/2;
    
    int speed = 5;
    
    boolean isDead;
    
    protected void addedToWorld(World world)
    {
        //System.out.println("added to world");
        
        playerObject = (greenfoot.core.WorldHandler.getInstance()).getWorld().getObjects(Player.class).get(0);
        
    }
    
    public void act()
    {
        move(speed);
        
        //System.out.println(playerObject.detectHitbox(this, hitboxRadius));
        
        if(!isDead){
        
            if(playerObject.detectHitbox(this, hitboxRadius)){
                playerObject.takeDmg(1);
                isDead = true;
                getWorld().removeObject(this);
                return;
            }
        
            if(checkWall()){
                isDead = true;
                getWorld().removeObject(this);
            }
        }
        
        
    }
    
    public SporeBullet(int angle){
        image = new GreenfootImage(15, 15);
        image.setColor(Color.GREEN);
        image.fillOval(5/2, 5/2, 10, 10);
        
        for (int i = 1; i <= 5; i++){
            int size = 10 + i;
            GreenfootImage img = new GreenfootImage(size, size);
            
            img.setColor(new Color(0, 255, 0, 60-i*10));
            img.fillOval(0, 0, size, size);
            
            image.drawImage(img, (5-i)/2, (5-i)/2);
        }

        setImage(image);
        
        turn(angle);
    }
    
    public boolean checkWall(){
        int x = getX();
        int y = getY();
        
        return !((wallWidth < x && x < 1600 - wallWidth) && (wallWidth < y && y < 900 - wallWidth));
    }
}
