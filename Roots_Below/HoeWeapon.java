import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HoeWeapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HoeWeapon extends Actor
{
    /**
     * Act - do whatever the HoeWeapon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage image;
    
    public int damage = 10;
    
    public HoeWeapon(){
        image = new GreenfootImage(250, 30);
        image.setColor(Color.BLUE);
        image.fillRect(125, 0, 125, 30);
        setImage(image);
    }
    
    public void act()
    {
        Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        
        if(enemy != null){
            //System.out.println("hit an enemy");
            enemy.takeDmg(damage);
        }
    }
}
