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
    Player playerObject;
    
    public enum EnemyType {
        SKEL, SLIME, TURRET
    }
    
    public EnemyType type;
    public int hp = 30;
    public int hitboxRadius = 25;
    public int speed = 3;
    public int angle;
    
    public int x;
    public int y;

    public boolean isDead;
    public boolean isBossMinion;
    
    float turretDelay = 2f;
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
    
    protected void addedToWorld(World world)
    {
        //System.out.println("added to world");
        
        playerObject = (greenfoot.core.WorldHandler.getInstance()).getWorld().getObjects(Player.class).get(0);
        
    }
    
    public void act()
    {
        //System.out.println(playerObject.detectHitbox(this, hitboxRadius));
        Actor player = (greenfoot.core.WorldHandler.getInstance()).getWorld().getObjects(Player.class).get(0);
        angle = (int)Math.toDegrees(Math.atan2(player.getY() - getY(),player.getX() -  getX()));
        
        if(playerObject.detectHitbox(this, hitboxRadius)){
            playerObject.takeDmg(1);
        }
        
        if(type == EnemyType.TURRET){
            turretShoot();
        }
        if(type == EnemyType.SLIME){
            slimeAttack();
        }
        
    }
    
    public void slimeAttack(){
        setRotation(angle);
        move(speed);
    }
    
    public void turretShoot(){
        if((System.currentTimeMillis() - timeLastShot)/1000.0 > turretDelay){
            getWorld().addObject(new SporeBullet(angle), getX(), getY());

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
        if(type != EnemyType.TURRET){
            if(angle < 0){
                angle += 360;
            }
            
            setRotation(angle-180);
            move(playerObject.knockback);
        }
        
    }
}
