import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.ArrayList;

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
        
        generateRooms(10);
        
        displayRooms();
    }
    
    public void GridRooms(int grid){
        for (int i = 0; i < grid; i++){
            for (int j = 0; j < grid; j++){
                Pos start = new Pos(i, j);
                rooms.put(start, new Room(i, j));
                //System.out.println(i + " "+ j);
            }
        }
        
    }
    
    public void generateRooms(int roomNum){
        Pos start = new Pos(0, 0);
        rooms.put(start, new Room(0, 0));
        
        while(rooms.size() < roomNum){
            ArrayList<Pos> keys = new ArrayList<>(rooms.keySet());
            Pos base = keys.get(Greenfoot.getRandomNumber(keys.size()));
            
            int dir = Greenfoot.getRandomNumber(4);
            
            int nx = base.x;
            int ny = base.y;
            
            if(dir == 0){ny--;}
            if(dir == 1){nx++;}
            if(dir == 2){ny++;}
            if(dir == 3){nx--;}
            
            Pos newPos = new Pos(nx, ny);
            
            System.out.println("Trying to make a room at x: " +nx+ " y: "+ny);
            
            if(!rooms.containsKey(newPos)){
                System.out.println("Making a room at x: " +nx+ " y: "+ny);
                
                rooms.put(newPos, new Room(nx, ny));
            }else{
                System.out.println("There is already a room at x: " +nx+ " y: "+ny);
            }
        }
    }
    
    public void displayRooms(){
        for (Room room : rooms.values()) {
            System.out.println("x: " + (room.x) + " y: " + (room.y));
        }
    }
    
    public void act()
    {
        
    }
}
