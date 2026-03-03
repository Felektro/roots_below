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
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1600, 900, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        setPaintOrder(Minimap.class, HoeWeapon.class, Player.class, Door.class);
        
        Minimap minimap = new Minimap();
        addObject(minimap,1500,100);
        
        GameManager gm = new GameManager(minimap);
        addObject(gm, 0, 0);
        
        Door door = new Door(Door.DoorType.LEFT, gm);
        addObject(door,70,450);
        Door door2 = new Door(Door.DoorType.UP, gm);
        addObject(door2,800,70);
        Door door3 = new Door(Door.DoorType.RIGHT, gm);
        addObject(door3,1530,450);
        Door door4 = new Door(Door.DoorType.DOWN, gm);
        addObject(door4,800,830);
        
        HoeWeapon hoe = new HoeWeapon();
        addObject(hoe, 800, 450);
        
        Player player = new Player(hoe);
        addObject(player, 800, 450);
        
        Wall wall1 = new Wall();
        addObject(wall1, 800, 450);
        
    }
}
