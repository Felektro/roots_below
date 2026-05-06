import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

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
    
    int wallWidth = 15 * 10; // 15 pixels
    int wallOffset = 30;
    
    
    public int x;
    public int y;

    public boolean isDead;
    public boolean isBossMinion;
    
    float turretDelay = 2f;
    double timeLastShot;
    
    float slimeJumpDelay = .5f;
    int jumpMult = 5;
    double timeLastSlimeJump;
    boolean isJumpSlime;
    boolean isJumping;
    boolean isWaitingJump;
    Pos jumpPos;
    
    public Enemy(int x, int y, Room room, boolean boss, EnemyType type){
        image = new GreenfootImage(50, 50);
        
        if(type == EnemyType.SLIME){
            if(Greenfoot.getRandomNumber(10) < 1){
                isJumpSlime = true;
                image.setColor(Color.GREEN);
            }else{
                image.setColor(Color.RED);
            }
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
        if(Greenfoot.getRandomNumber(100) < 1 && !isJumping && !isWaitingJump && isJumpSlime){
            isWaitingJump = true;
            timeLastSlimeJump = System.currentTimeMillis();
            
            Actor player = (greenfoot.core.WorldHandler.getInstance()).getWorld().getObjects(Player.class).get(0);
            jumpPos = new Pos( (int)((player.getX() - getX()) * 0.3 + player.getX()) , (int)((player.getY() - getY()) * 0.3 + player.getY()) );
            jumpPos.x = Math.clamp(jumpPos.x, 0, 1600 - 2 * wallWidth) + wallWidth;
            jumpPos.y = Math.clamp(jumpPos.y, 0, 900 - 2 * wallWidth) + wallWidth;
            
            
            //System.out.println("set a new position");
        }

        if(isWaitingJump){
            if((System.currentTimeMillis() - timeLastSlimeJump)/1000.0 > slimeJumpDelay){
                isWaitingJump = false;
                isJumping = true;
                
            }
        }
        
        if(isJumping){
            angle = (int)Math.toDegrees(Math.atan2(jumpPos.y - getY(), jumpPos.x -  getX()));
            setRotation(angle);
            move(speed * jumpMult);
            if(checkWall()){ 
                turnTowards(800, 450);
                move(speed);
            }
            if(dist(getX(), getY(), jumpPos.x, jumpPos.y) < (jumpMult*2)){
                isJumping = false;
            }
        }
        else if(!isWaitingJump){
            angle = (int)Math.toDegrees(Math.atan2(playerObject.getY() - getY(),playerObject.getX() -  getX()));
            setRotation(angle);
            move(speed);
            if(checkWall()){ 
                turnTowards(800, 450);
                move(speed);
            }
        }
        
        pushSlimesAway();
    }
    
    public void pushSlimesAway(){
        List<Enemy> enemies = getObjectsInRange(50, Enemy.class);
        
        int pushX = 0;
        int pushY = 0;
        
        for(Enemy enemy : enemies){
            if(enemy == this){ continue; }
            
            int dx = getX() - enemy.getX();
            int dy = getY() - enemy.getY();
            
            pushX += dx;
            pushY += dy;
        }
        
        if(pushX != 0 || pushY != 0){
            int pushAngle = (int)Math.toDegrees(Math.atan2(pushY, pushX));
            setRotation(pushAngle);
            move(2);
        }
        
    }
    
    public void turretShoot(){
        if((System.currentTimeMillis() - timeLastShot)/1000.0 > turretDelay){
            angle = (int)Math.toDegrees(Math.atan2(playerObject.getY() - getY(),playerObject.getX() -  getX()));
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
    
    public double dist(int x1, int y1, int x2, int y2){
        return Math.sqrt((Math.pow(x1 - x2, 2)+Math.pow(y1 - y2, 2)));
    }
    
    public boolean checkWall(){
        int x = getX();
        int y = getY();
        
        return !((wallWidth + wallOffset < x && x < 1600 - wallWidth - wallOffset) && (wallWidth + wallOffset < y && y < 900 - wallWidth - wallOffset));
    }
    
}
