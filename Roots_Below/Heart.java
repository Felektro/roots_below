import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Heart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heart extends Actor
{
    /**
     * Act - do whatever the Heart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage full;
    GreenfootImage half;
    GreenfootImage empty;
    
    public Heart(){
        full = scaleImage("heart_full.png");
        half = scaleImage("heart_half.png");
        empty = scaleImage("heart_empty.png");
        
        setFull(2);
    }

    public GreenfootImage scaleImage(String img){
        GreenfootImage image = new GreenfootImage(img);
        
        image.scale((int)(image.getWidth()*2), (int)(image.getHeight()*2)); 
        
        return image;
         
    }
    
    public void setFull(int hp){
        if(hp == 0){
            setImage(empty);
        }else if (hp == 1){
            setImage(half);
        }else{
            setImage(full);
        }
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
