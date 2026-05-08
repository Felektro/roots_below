import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    
    public static int floorNumber = 0;
    public static int enemiesSlain = 0;
    public static int bossesSlain = 0;
    public static double timeStartedPlaying = 0;
    
    private GreenfootSound bgMusic = new GreenfootSound("BackgroundMusic.mp3");
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 900, 1); 
        prepare();
        bgMusic.playLoop();
        
        GreenfootImage bgImage = new GreenfootImage(1600, 900);
        bgImage.setColor(Color.DARK_GRAY);
        bgImage.fillRect(0,0,1600,900);
        setBackground(bgImage);
        
    }
    
    public void changeMusic(String music){
        bgMusic.stop();
        bgMusic = new GreenfootSound(music);
        bgMusic.playLoop();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        setPaintOrder(Inventory.class, InventoryBackground.class, Heart.class, Minimap.class, BossHealthBar.class, HoeWeapon.class, Player.class, Door.class);
        
        Minimap minimap = new Minimap();
        addObject(minimap,1500,100);
        
        GameManager gm = new GameManager(minimap);
        addObject(gm, 0, 0);
        
        Door door = new Door(Door.DoorType.LEFT, gm);
        addObject(door,80,450);
        Door door2 = new Door(Door.DoorType.UP, gm);
        addObject(door2,800,80);
        Door door3 = new Door(Door.DoorType.RIGHT, gm);
        addObject(door3,1520,450);
        Door door4 = new Door(Door.DoorType.DOWN, gm);
        addObject(door4,800,820);
        
        HoeWeapon hoe = new HoeWeapon();
        addObject(hoe, 800, 470);
        
        Inventory inv = inv = new Inventory();
        addObject(inv, 0, 0);      
        
        
        Player player = new Player(hoe, gm, inv);
        addObject(player, 800, 450);
        
        
        
        for (int i = 0; i < player.playerHealth/2; i++){
            player.hearts.add(new Heart());
            addObject(player.hearts.get(i), 50 + i * (32 * 2 + 15), 50);
        }
        
        Wall wall1 = new Wall();
        addObject(wall1, 800, 450);
        
    }
}
