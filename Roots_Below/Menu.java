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
    
    public Menu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 900, 1); 
        bgImage = new GreenfootImage("Menu_Final.png");
        setBackground(bgImage);
        
        addObject(new MenuButton("StartButton.png"), 800, 480);
        addObject(new MenuButton("CreditsButton.png"), 800, 560);
        addObject(new MenuButton("QuitButton.png"), 800, 640);
        
    }
}
