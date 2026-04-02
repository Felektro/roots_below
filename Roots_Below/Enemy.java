import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    Room room;
    
    public enum EnemyType {
        SKEL, SLIME, TURRET
    }
    
    public EnemyType type;
    public int hp = 30;
    
    public int x;
    public int y;

    public boolean isDead;
    public boolean isBossMinion;
    
    float turretDelay = 1f;
    double timeLastShot;
    
    public Enemy(int x, int y, Room room, boolean boss, EnemyType type){
        image = new GreenfootImage(50, 50);
        
        if(type == EnemyType.SLIME){
            image.setColor(Color.RED);
        }else{
            image.setColor(Color.BLUE);
        }
        image.fillOval(0, 0, 50, 50);
        setImage(image);

        isBossMinion = boss;
        
        this.type = type;
        this.x = x;
        this.y = y;
        this.room = room;
    }
    
    public void act()
    {
        Actor player = getOneIntersectingObject(Player.class);
        
        if(player != null){
            //System.out.println("touching the player");
        }
        
        if(type == EnemyType.TURRET){
            turretShoot();
        }
    }
    
    public void turretShoot(){
        if((System.currentTimeMillis() - timeLastShot)/1000.0 > turretDelay){
            Actor player = (greenfoot.core.WorldHandler.getInstance()).getWorld().getObjects(Player.class).get(0);
            int angle = (int)Math.toDegrees(Math.atan2(player.getY() - getY(),player.getX() -  getX()));
            System.out.println(angle);
            
            getWorld().addObject(new SporeBullet(angle), getX(), getY());
            
            //System.out.println("Attack");
            timeLastShot = System.currentTimeMillis();
        }
    }
    
    public void remove(){
        if(!isDead){
            getWorld().removeObject(this);
            
            isDead = true;
            
            room.openDoors();
        }
    }
    
    public void takeDmg(int dmg){
        hp -= dmg;
        if(hp <= 0){
            remove();
        }
    }
}
