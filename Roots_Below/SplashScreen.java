import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SplashScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SplashScreen extends World
{
    
    
    public SplashScreen()
    {    
        super(1600, 900, 1);
        GreenfootImage image = new GreenfootImage("vanier_Logo.png");
        getBackground().drawImage(image, (1600 - image.getWidth())/2, (900 - image.getHeight())/2);
        showText("Game Programming 1 - 420-141-VA - Winter 2026", 230 , 100);
        showText("Made by: Illia, Yuliia, Richard", 500 ,800);
        try {
            // to sleep 3 seconds
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // recommended because catching InterruptedException clears interrupt flag
            Thread.currentThread().interrupt();
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
