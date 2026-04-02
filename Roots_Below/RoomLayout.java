import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class RoomLayout here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoomLayout extends Actor
{
    /**
     * Act - do whatever the RoomLayout wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public void act()
    {
        // Add your action code here.
    }
    
    public static ArrayList<Enemy> randomRoomLayout(Room room){
        ArrayList<Enemy> generatedRoom = new ArrayList<>();
        for(int i = 0; i < Greenfoot.getRandomNumber(3) + 3; i++){
            Enemy enemy = new Enemy(Greenfoot.getRandomNumber(1300) + 150, Greenfoot.getRandomNumber(600) + 150, room, false, Enemy.EnemyType.SLIME);
            enemy.isDead = false;
            generatedRoom.add(enemy);
        }
        
        return generatedRoom;
    }
    
    public static Boss bossLayout(Room room){
        return new Boss(room);
    }
}
