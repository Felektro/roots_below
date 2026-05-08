import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Menu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Menu extends World
{

    /**
     * Constructor for objects of class Menu.
     * 
     */
    
    GreenfootImage bgImage;
    
    private GreenfootSound bgMusic = new GreenfootSound("MenuMusic.mp3");
    
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 900, 1); 
        bgMusic.playLoop();
        bgImage = new GreenfootImage("Menu_Final.png");
        setBackground(bgImage);
        
        addObject(new MenuButton("StartButton.png", bgMusic), 800, 480);
        addObject(new MenuButton("CreditsButton.png", bgMusic), 800, 560);
        addObject(new MenuButton("QuitButton.png", bgMusic), 800, 640);
        
        MyWorld.floorNumber = 0;
        
    }
}
