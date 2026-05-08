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
    public int hoeDir;
    
    public float damage = 10;
    public int slow;
    public float root;
    
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
                
                if(!detectSide(enemy)){
                    return;
                }
                
                enemy.takeDmg(damage, slow, root);
                enemiesHit.add(enemy);
            }
            
            Boss boss = (Boss)getOneIntersectingObject(Boss.class);
            
            if(boss != null && bossHit == null){
                //System.out.println("hit an enemy");
                
                if(!detectSide(boss)){
                    return;
                }
                
                boss.takeDmg(damage);
                bossHit = boss;
            }
        }
        else{
            enemiesHit.clear();
            bossHit = null;
        }
    }
    
    public boolean detectSide(Actor actor){
        switch (hoeDir){
                case 1:
                    if (actor.getX() <= getX()){
                        //System.out.println("on the left");
                        return false;
                    }
                    break;
                case 2:
                    if (actor.getY() <= getY()){
                        //System.out.println("up");
                        return false;
                    }
                    break;
                case 3:
                    if (actor.getX() >= getX()){
                        //System.out.println("on the right");
                        return false;
                    }
                    break;
                 case 4:
                    if (actor.getY() >= getY()){
                        //System.out.println("down");
                        return false;
                    }
                    break;
                
                } 
        return true;
    }
    
    public void setImg(String img){
        GreenfootImage image = new GreenfootImage(img);
        
        image.scale((int)(image.getWidth()*6), (int)(image.getHeight()*6));
        
        setImage(image);
    }
}
