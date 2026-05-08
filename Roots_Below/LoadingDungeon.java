import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LoadingDungeon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LoadingDungeon extends World
{
    /**
     * Constructor for objects of class LoadingDungeon.
     * 
     */
    
    float screenDelay = 3f;
    double timeScreenStarted;

    private GreenfootSound bgMusic = new GreenfootSound("BackgroundMusic.mp3");
    
    public LoadingDungeon()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 900, 1); 
        //bgMusic.playLoop();
        timeScreenStarted = System.currentTimeMillis();     
        
        GreenfootImage bgImage = new GreenfootImage(1600, 900);
        bgImage.setColor(Color.DARK_GRAY);
        bgImage.fillRect(0,0,1600,900);
        setBackground(bgImage);
        
        
        MyWorld.floorNumber++;
        showText("Loading floor " + MyWorld.floorNumber + "...", 600 , 450);
        
    }
    
    public void act(){
        if((System.currentTimeMillis() - timeScreenStarted)/1000.0 > screenDelay){
            Greenfoot.setWorld(new MyWorld());
            bgMusic.pause();
        }
    }
    
    public void showText(String message, int x, int y)
    {
        GreenfootImage bg = getBackground();
        Font font =  new  Font(50);
        bg.setFont(font);
        bg.setColor(Color.WHITE);
        bg.drawString(message, x, y);
    }
    
}
