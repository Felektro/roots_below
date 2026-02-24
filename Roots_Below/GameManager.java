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
    
    Minimap minimap;
    
    Pos currentPos;
    
    public GameManager(Minimap mnmp){
        GreenfootImage image = new GreenfootImage(1, 1);
        setImage(image);
        
        
        minimap = mnmp;
        
        
        //gridRooms(5);
        generateRooms(15);
        
        currentPos = new Pos(0, 0);
        
        //displayRooms();
        
        minimap.loadRooms(rooms, currentPos.x, currentPos.y);
        
        //need to load this once on startup for the doors in the starter room to display properly
        //rooms.get(currentPos).loadRoom();
    }
    
    
    public void changeRoom(Door.DoorType type, Actor player){
        int offset = 100;
        
        switch(type) {
            case Door.DoorType.UP:
                if(rooms.get(currentPos).topDoor){
                    player.setLocation(800,800 - offset);
                    currentPos = new Pos(currentPos.x, currentPos.y - 1);
                }
                break;
            case Door.DoorType.DOWN:
                if(rooms.get(currentPos).botDoor){
                    player.setLocation(800,100 + offset);
                    currentPos = new Pos(currentPos.x, currentPos.y + 1);
                }
                break;
            case Door.DoorType.RIGHT:
                if(rooms.get(currentPos).rightDoor){
                    player.setLocation(50 + offset,450);
                    currentPos = new Pos(currentPos.x + 1, currentPos.y);
                }
                break;
            case Door.DoorType.LEFT:
                if(rooms.get(currentPos).leftDoor){
                    player.setLocation(1550 - offset,450);
                    currentPos = new Pos(currentPos.x - 1, currentPos.y);
                }
                break;
                
        }
        
        minimap.loadRooms(rooms, currentPos.x, currentPos.y);
        //System.out.println(currentPos.x + " " +currentPos.y);
        
        rooms.get(currentPos).loadRoom();
    }
    
    public void gridRooms(int grid){
        int offset = (int)Math.floor(grid/2f);
        
        for (int i = 0; i < grid; i++){
            for (int j = 0; j < grid; j++){
                Pos currPos = new Pos(i - offset, j - offset);
                rooms.put(currPos, new Room(i - offset, j - offset));
                rooms.get(currPos).topDoor = (j != 0);
                rooms.get(currPos).botDoor = (j != grid-1);
                rooms.get(currPos).rightDoor = (i != grid-1);
                rooms.get(currPos).leftDoor = (i != 0);
                
                rooms.get(currPos).isShop = (i == 1 && j == 1);
                rooms.get(currPos).isGarden = (i == 3 && j == 3);
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
            
            //System.out.println("Trying to make a room at x: " +nx+ " y: "+ny);
            
            if(!rooms.containsKey(newPos)){
                //System.out.println("Making a room at x: " +nx+ " y: "+ny);
                rooms.put(newPos, new Room(nx, ny));
                
                if(dir == 0){
                    rooms.get(new Pos(base.x, base.y)).topDoor = true;
                    rooms.get(newPos).botDoor = true;
                }
                if(dir == 1){
                    rooms.get(new Pos(base.x, base.y)).rightDoor = true;
                    rooms.get(newPos).leftDoor = true;
                }
                if(dir == 2){
                    rooms.get(new Pos(base.x, base.y)).botDoor = true;
                    rooms.get(newPos).topDoor = true;
                }
                if(dir == 3){
                    rooms.get(new Pos(base.x, base.y)).leftDoor = true;
                    rooms.get(newPos).rightDoor = true;
                }
            }else{
                //System.out.println("There is already a room at x: " +nx+ " y: "+ny);
            }
        }
        
        boolean shopGenerated = false;
        boolean gardenGenerated = false;
        
        ArrayList<Pos> keys = new ArrayList<>(rooms.keySet());
        
        while (!shopGenerated){
            Pos shop = keys.get(Greenfoot.getRandomNumber(keys.size()));
            if(!shop.equals(new Pos(0, 0))){
                rooms.get(shop).isShop = true;
                shopGenerated = true;
            }
        }
        while (!gardenGenerated){
            Pos garden = keys.get(Greenfoot.getRandomNumber(keys.size()));
            if(!garden.equals(new Pos(0, 0)) && !rooms.get(garden).isShop){
                rooms.get(garden).isGarden = true;
                gardenGenerated = true;
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
