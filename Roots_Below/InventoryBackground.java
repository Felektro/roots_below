import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InventoryBackground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InventoryBackground extends Actor
{
    /**
     * Act - do whatever the InventoryBackground wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    Inventory inv;
    
    public InventoryBackground(Inventory inv){
        image = new GreenfootImage(1600, 900);
        image.setColor(Color.DARK_GRAY);
        image.fillRect(0, 0, 1600, 900);
        image.setTransparency(200);
        
        setImage(image);
        
        this.inv = inv;
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)){
            inv.closePedestalMenu();
            
            close();
        }
    }
    
    public void close(){
        getWorld().removeObject(this);
    }
    
}
