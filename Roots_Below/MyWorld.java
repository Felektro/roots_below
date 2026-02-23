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
        Door door = new Door(Door.DoorType.LEFT);
        addObject(door,50,450);
        Door door2 = new Door(Door.DoorType.UP);
        addObject(door2,800,100);
        Door door3 = new Door(Door.DoorType.RIGHT);
        addObject(door3,1550,450);
        Door door4 = new Door(Door.DoorType.DOWN);
        addObject(door4,800,800);
    }
}
