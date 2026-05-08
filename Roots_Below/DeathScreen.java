import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeathScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathScreen extends World
{
    /**
     * Constructor for objects of class DeathScreen.
     * 
     */
    
    float screenDelay = 5f;
    double timeScreenStarted;
    
    private GreenfootSound bgMusic = new GreenfootSound("BackgroundMusic.mp3");
    
    public DeathScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 900, 1); 
        //bgMusic.playLoop();
        timeScreenStarted = System.currentTimeMillis();     
        
        GreenfootImage bgImage = new GreenfootImage(1600, 900);
        bgImage.setColor(Color.DARK_GRAY);
        bgImage.fillRect(0,0,1600,900);
        setBackground(bgImage);
        
        showText("You died on floor " + MyWorld.floorNumber + "!", 500 , 150);
        showText("Thanks for playing!", 500 , 250);
        
        showText("Enemies Slain: " + MyWorld.enemiesSlain, 500 , 450);
        showText("Bosses Slain: " + MyWorld.bossesSlain, 500 , 550);
        showText("Time spent playing: " + ((System.currentTimeMillis() - MyWorld.timeStartedPlaying)/1000), 500 , 650);
        
        MyWorld.floorNumber = 0;
    }
    
    public void act(){
        if((System.currentTimeMillis() - timeScreenStarted)/1000.0 > screenDelay){
            Greenfoot.setWorld(new Menu());
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
