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
    
    public boolean isShop, isGarden, isBoss;
    
    public boolean isCleared;
    
    private List<Door> doors;
    
    private ArrayList<Enemy> enemies;
    public Room(int x, int y){
        this.x = x;
        this.y = y;
        if(!(x == 0 && y == 0)){
            enemies = RoomLayout.randomRoomLayout(this);  //REMOVE AFTER TESTING
        }
        //isCleared = true;
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
        if(isBoss){
            enemies.clear();
            enemies = null;
            
            greenfoot.core.WorldHandler.getInstance().getWorld().addObject(RoomLayout.bossLayout(this), 800, 450);
        }
        if(enemies == null  || isShop || isGarden){return;}
        for(int i = 0; i < enemies.size(); i++){
            if(!enemies.get(i).isDead){
                greenfoot.core.WorldHandler.getInstance().getWorld().addObject(enemies.get(i), enemies.get(i).x, enemies.get(i).y);
            }            
        }
    }
    
    public void checkEnemies(){
        if(enemies == null  || isShop || isGarden){return;}
        for(int i = 0; i < enemies.size(); i++){
            if(!enemies.get(i).isDead || enemies.get(i).isBossMinion){
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
    
    public void openDoors(){
        checkEnemies();
        loadDoors();
    }
    
    public void loadDoors(){
        
        doors = (greenfoot.core.WorldHandler.getInstance()).getWorld().getObjects(Door.class);
        
        for (Door door : doors){
            if(topDoor && door.transition == Door.DoorType.UP){
                door.makeVisible(true);
                if(!isCleared) {door.close(true);}
            }else if(botDoor && door.transition == Door.DoorType.DOWN){
                door.makeVisible(true);
                if(!isCleared) {door.close(true);}
            }else if(rightDoor && door.transition == Door.DoorType.RIGHT){
                door.makeVisible(true);
                if(!isCleared) {door.close(true);}
            }else if(leftDoor && door.transition == Door.DoorType.LEFT){
                door.makeVisible(true);
                if(!isCleared) {door.close(true);}
            }else{
                door.makeVisible(false);
            }
            
            nameRoom();
        }
    }
}
