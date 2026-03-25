import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class Room here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Room extends Actor
{
    public int x;
    public int y;
    
    public boolean topDoor, botDoor, rightDoor, leftDoor;
    
    public boolean isShop, isGarden;
    
    public boolean isCleared;
    
    private List<Door> doors;
    
    private ArrayList<Enemy> enemies;
    public Room(int x, int y){
        this.x = x;
        this.y = y;
        if(!(x == 0 && y == 0)){
            enemies = RoomLayout.randomRoomLayout();
        }
        
    }
    public void act()
    {
        //
    }
    
    public void loadRoom(){
        loadEnemies();
        loadDoors();
    }
    
    public void nameRoom(){
        World world = greenfoot.core.WorldHandler.getInstance().getWorld();
        GreenfootImage bg = world.getBackground();
        
        bg.setColor(Color.WHITE);
        bg.fillRect(0, 0, world.getWidth(), world.getHeight());
        
        bg.setColor(Color.BLACK);
        bg.drawString("This is room x: " + x + " y: " + y, world.getWidth()/2, world.getHeight()/2);
        world.setBackground(bg);
    }
    
    public void loadEnemies(){
        if(enemies == null){return;}
        for(int i = 0; i < enemies.size(); i++){
            if(!enemies.get(i).isDead){
                greenfoot.core.WorldHandler.getInstance().getWorld().addObject(enemies.get(i), enemies.get(i).x, enemies.get(i).y);
            }            
        }
    }
    
    public void checkEnemies(){
        if(enemies == null){return;}
        for(int i = 0; i < enemies.size(); i++){
            if(!enemies.get(i).isDead){
                return;
            }            
        }
        isCleared = true;
    }
    
    public void removeOldEnemies(){
        if(enemies == null){return;}
        for (Enemy enemy : enemies){
            //System.out.println("new enemy removed" + enemy);
            enemy.remove();
        }
    }
    
    public void loadDoors(){
        // System.out.println("====================");
        // System.out.println(topDoor);
        // System.out.println(botDoor);
        // System.out.println(rightDoor);
        // System.out.println(leftDoor);
        
        doors = (greenfoot.core.WorldHandler.getInstance()).getWorld().getObjects(Door.class);
        
        for (Door door : doors){
            if(topDoor && door.transition == Door.DoorType.UP){
                door.makeVisible(true);
            }else if(botDoor && door.transition == Door.DoorType.DOWN){
                door.makeVisible(true);
            }else if(rightDoor && door.transition == Door.DoorType.RIGHT){
                door.makeVisible(true);
            }else if(leftDoor && door.transition == Door.DoorType.LEFT){
                door.makeVisible(true);
            }else{
                door.makeVisible(false);
            }
            
            nameRoom();
        }
    }
}
