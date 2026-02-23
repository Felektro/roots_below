import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pos extends Actor
{
    /**
     * Act - do whatever the Pos wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public int x;
    public int y;
    
    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public void act()
    {
        // Add your action code here.
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Pos)) return false;
        Pos p = (Pos)o;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode(){
        return x * 31 + y;
    }
    
}
