import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Actor 
{
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    public String type;
    int maxHp = 100;
    int hp;
    
    public boolean isDead;
    
    BossHealthBar hpBar;
    
    public Boss(){
        image = new GreenfootImage(150, 150);
        image.setColor(Color.RED);
        image.fillOval(0, 0, 150, 150);
        setImage(image);
        
        hp = maxHp;
    }
    
    protected void addedToWorld(World world)
    {
        //System.out.println("added to world");
        hpBar = new BossHealthBar(maxHp);
        world.addObject(hpBar, 800, 850);
    }
    
    public void act()
    {
        Actor player = getOneIntersectingObject(Player.class);
        
        if(player != null){
            //System.out.println("touching the player");
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
        
        hpBar.hp = hp;
        
        if(hp <= 0){
            remove();
        }
    }
}
