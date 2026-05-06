import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MenuButton extends Actor
{
    /**
     * Act - do whatever the MenuButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    GreenfootImage hoveredImage;
    
    public MenuButton(String imgName){
        image = new GreenfootImage(imgName);
        
        hoveredImage = drawContour(image, new Color(28, 115, 68), image.getWidth(), image.getHeight());
        
        float scale = 1.1f;
        
        hoveredImage.scale((int)(hoveredImage.getWidth() * scale), (int)(hoveredImage.getHeight() * scale));
        
        image = new GreenfootImage(imgName);
    }
    
    public void act()
    {
        if (Greenfoot.mouseMoved(this))
        {
            setImage(hoveredImage);
        }

        if (Greenfoot.mouseMoved(getWorld()))
        {
            setImage(image);
        }

        if (Greenfoot.mouseClicked(this))
        {
            System.out.println("I was clicked");
        }
    }
    
    public GreenfootImage drawContour(GreenfootImage img, Color color, int width, int height){
        img.setColor(color);
        
        for(int i = 0; i<5; i++){
            img.drawRect(i, i, width-i*2-1, height-i*2-1);
        }
        
        return img;
    }
    
}
