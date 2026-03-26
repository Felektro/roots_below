import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BoosHealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BossHealthBar extends Actor
{
    /**
     * Act - do whatever the BoosHealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    int width = 1200;
    int height = 50;
    
    GreenfootImage bar;
    GreenfootImage darkBar;
    GreenfootImage cutBar;
    GreenfootImage newBar;
    
    int maxHp;
    int hp;
    
    public BossHealthBar(int maxHp){
        this.maxHp = maxHp;
        hp = maxHp;
        
        bar = new GreenfootImage(width, height);
        bar.setColor(Color.RED);
        
        bar.fillOval(0, 0, height, height);
        bar.fillOval(width-height, 0, height, height);
        
        bar.fillRect(height/2, 0, width-height, height);
        setImage(bar);
        
        darkBar = new GreenfootImage(width, height);
        darkBar.setColor(Color.RED.darker());
        
        darkBar.fillOval(0, 0, height, height);
        darkBar.fillOval(width-height, 0, height, height);
        
        darkBar.fillRect(height/2, 0, width-height, height);
        
        
    }
    
    public void changeHealthBar(){
        if((int)((float)width/maxHp*hp) <= 0){
            getWorld().removeObject(this);
            return;
        }
        
        cutBar = new GreenfootImage((int)((float)width/maxHp*hp), height);
        cutBar.drawImage(bar, 0, 0);
        
        newBar = new GreenfootImage(darkBar);
        newBar.drawImage(cutBar, 0,0);
        setImage(newBar);
    }
    
    public void act()
    {
        changeHealthBar();
    }
}
