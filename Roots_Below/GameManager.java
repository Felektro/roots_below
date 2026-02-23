import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;

/**
 * Write a description of class GameManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameManager extends Actor
{
    
    HashMap<Pos, Room> rooms = new HashMap<>();
    
    public GameManager(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Pos start = new Pos(i, j);
                rooms.put(start, new Room(i, j));
                //System.out.println(i + " "+ j);
            }
        }
        
        for (Room room : rooms.values()) {
            System.out.println("x: " + room.x + " y:" + room.y);
        }
        
    }
    
    
    public void act()
    {
        
    }
}
