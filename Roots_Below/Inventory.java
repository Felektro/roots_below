import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory extends Actor
{
    /**
     * Act - do whatever the Inventory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    private boolean isClosed = true;
    
    private int cellSize = 40;
    
    private int invWidth = 200 + 10;
    private int invHeight = cellSize + 10;
    
    public Inventory(){
        image = new GreenfootImage(invWidth, invHeight);
        
        setImage(drawContour(image, Color.BLACK, invWidth, invHeight));
        
        for (int i = 0; i < 5; i++){
            drawCell(cellSize, i * cellSize + 5, 5);
        }
        
        image.setTransparency(0);
        
        setImage(image);
    }
    
    public void act()
    {
        // Add your action code here.
    }
    
    public void openInventory(){
        if(isClosed){ image.setTransparency(255); }
        else{ image.setTransparency(0); }
        
        isClosed = !isClosed;
        setImage(image);
    }

    public GreenfootImage drawContour(GreenfootImage img, Color color, int width, int height){
        img.setColor(color);
        
        for(int i = 0; i<5; i++){
            img.drawRect(i, i, width-i*2-1, height-i*2-1);
        }
        
        return img;
    }
    
    public void drawCell(int size, int xOffset, int yOffset){
        GreenfootImage cell = new GreenfootImage(size, size);
        
        cell.setColor(Color.RED);
        cell.fillRect(0, 0, size, size);
        
        cell.drawImage(drawContour(cell, Color.GRAY, size, size), 0, 0);
        
        image.drawImage(cell, xOffset, yOffset);
    }
    
    
}



