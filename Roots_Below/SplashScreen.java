import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SplashScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SplashScreen extends World
{
<<<<<<< HEAD
    
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
        
=======

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
            transitionToMenu();
        } catch (InterruptedException e) {
            // recommended because catching InterruptedException clears interrupt flag
            Thread.currentThread().interrupt();
        }
        prepare();
>>>>>>> d5067bd56cea73ae37f08d63fd12a3a9fb77af4c
    }

    public void showText(String message, int x, int y)
    {
        GreenfootImage bg = getBackground();
        Font font =  new  Font(50);
        bg.setFont(font);
        bg.setColor(Color.WHITE);
        bg.drawString(message, x, y);
    }

<<<<<<< HEAD
    public void act(){
        if((System.currentTimeMillis() - timeScreenStarted)/1000.0 > screenDelay){
            Greenfoot.setWorld(new Menu());
        }
    }
    
    
=======
>>>>>>> d5067bd56cea73ae37f08d63fd12a3a9fb77af4c
    public void transitionToMenu(){
        World menu = new Menu();
        Greenfoot.setWorld(menu);
    }
<<<<<<< HEAD
    
=======
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
>>>>>>> d5067bd56cea73ae37f08d63fd12a3a9fb77af4c
}
