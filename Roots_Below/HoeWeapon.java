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
    
    public int damage = 10;
    
    public HoeWeapon(){
        setImg("hoe1.png");
    }
    
    public void act()
    {
        Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        
        if(enemy != null){
            //System.out.println("hit an enemy");
            enemy.takeDmg(damage);
        }
    }
    
    public void setImg(String img){
        GreenfootImage image = new GreenfootImage(img);
        
        image.scale((int)(image.getWidth()*6), (int)(image.getHeight()*6));
        
        setImage(image);
    }
}
