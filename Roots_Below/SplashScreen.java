import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SplashScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SplashScreen extends World
{
    
    float screenDelay = 3f;
    double timeScreenStarted;
    
    public SplashScreen()
    {    
        super(1600, 900, 1);
        
        GreenfootImage bg = new GreenfootImage(1600, 900);
        bg.setColor(Color.BLACK);
        bg.fillRect(0,0, 1600, 900);
        setBackground(bg);
        
        GreenfootImage logo = new GreenfootImage("vanier_Logo.png");
        
        getBackground().drawImage(logo, (1600 - logo.getWidth())/2, (900 - logo.getHeight())/2);
        showText("Game Programming 1 - 420-141-VA - Winter 2026", 230 , 100);
        showText("Made by: Illia, Yuliia, Richard", 500 ,800);
        
        timeScreenStarted = System.currentTimeMillis(); 
        
    }

    public void showText(String message, int x, int y)
    {
        GreenfootImage bg = getBackground();
        Font font =  new  Font(50);
        bg.setFont(font);
        bg.setColor(Color.WHITE);
        bg.drawString(message, x, y);
    }

    public void act(){
        if((System.currentTimeMillis() - timeScreenStarted)/1000.0 > screenDelay){
            Greenfoot.setWorld(new Menu());
        }
    }
    
    
    public void transitionToMenu(){
        World menu = new Menu();
        Greenfoot.setWorld(menu);
    }
    
}
