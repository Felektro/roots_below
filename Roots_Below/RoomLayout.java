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
    
    public static ArrayList<Enemy> randomRoomLayout(){
        ArrayList<Enemy> generatedRoom = new ArrayList<>();
        for(int i = 0; i < Greenfoot.getRandomNumber(3) + 3; i++){
            Enemy enemy = new Enemy(Greenfoot.getRandomNumber(1500) + 50, Greenfoot.getRandomNumber(800) + 50);
            enemy.isDead = false;
            generatedRoom.add(enemy);
        }
        
        return generatedRoom;
    }
}
