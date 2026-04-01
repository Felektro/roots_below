import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

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
    
    public boolean isUsed;
    
    ArrayList<Enemy> enemiesHit = new ArrayList<>();
    Boss bossHit;
    
    public HoeWeapon(){
        setImg("hoe1.png");
    }
    
    public void act()
    {
        if(isUsed){
            Enemy enemy = (Enemy)getOneIntersectingObject(Enemy.class);
        
            if(enemy != null && !enemiesHit.contains(enemy)){
                //System.out.println("hit an enemy");
                enemy.takeDmg(damage);
                enemiesHit.add(enemy);
            }
            
            Boss boss = (Boss)getOneIntersectingObject(Boss.class);
            
            if(boss != null && bossHit == null){
                //System.out.println("hit an enemy");
                boss.takeDmg(damage);
                bossHit = boss;
            }
        }
        else{
            enemiesHit.clear();
            bossHit = null;
        }
    }
    
    public void setImg(String img){
        GreenfootImage image = new GreenfootImage(img);
        
        image.scale((int)(image.getWidth()*6), (int)(image.getHeight()*6));
        
        setImage(image);
    }
}
