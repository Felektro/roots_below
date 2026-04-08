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
    
    static Pos[] doors = {new Pos(80,450),new Pos(800,80),new Pos(1520,450),new Pos(800,820)};
    
    public void act()
    {
        // Add your action code here.
    }
    
    public static ArrayList<Enemy> randomRoomLayout(Room room){
        ArrayList<Enemy> generatedRoom = new ArrayList<>();
        for(int i = 0; i < Greenfoot.getRandomNumber(3) + 3; i++){
            
            int x = 0;
            int y = 0;
            boolean valid;
            
            do{
                x = Greenfoot.getRandomNumber(1300) + 150;
                y = Greenfoot.getRandomNumber(600) + 150;
                valid = true;
                
                for (Enemy enemy : generatedRoom){
                    valid = valid && dist(x, y, enemy.x, enemy.y) >= 50;
                }
                
                for (Pos door : doors){
                    valid = valid && dist(x, y, door.getX(), door.getY()) >= 300;
                }
                
                
            }while(!valid);
            Enemy enemy = new Enemy(x, y, room, false, Enemy.EnemyType.SLIME);
            enemy.isDead = false;
            generatedRoom.add(enemy);
        }
        
        return generatedRoom;
    }
    
    public static double dist(int x1, int y1, int x2, int y2){
        return Math.sqrt((Math.pow(x1 - x2, 2)+Math.pow(y1 - y2, 2)));
    }
    
    public static Boss bossLayout(Room room){
        return new Boss(room);
    }
}
