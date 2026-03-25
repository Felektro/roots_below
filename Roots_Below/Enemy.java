import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    public String type;
    public int hp = 10;
    
    public int x;
    public int y;

    public boolean isDead;
    
    public Enemy(int x, int y){
        image = new GreenfootImage(50, 50);
        image.setColor(Color.RED);
        image.fillOval(0, 0, 50, 50);
        setImage(image);

        this.x = x;
        this.y = y;
    }
    
    public void act()
    {
        Actor player = getOneIntersectingObject(Player.class);
        
        if(player != null){
            System.out.println("touching the player");
        }
    }
    
    public void remove(){
        if(!isDead){
            getWorld().removeObject(this);
            
            isDead = true;
            
        }
    }
    
    public void takeDmg(int dmg){
        hp -= dmg;
        if(hp <= 0){
            remove();
        }
    }
}
