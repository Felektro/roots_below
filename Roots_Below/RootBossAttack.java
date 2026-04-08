import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RootBossAttack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RootBossAttack extends Actor
{
    /**
     * Act - do whatever the RootBossAttack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    Player playerObject;
    
    boolean isActive;
    boolean isDead;
    
    int startSize = 25;
    int endSize = 75;
    
    double currentTime;
    float attackProgress = 0;
    float sizeChange;
    float attackDelay = 3f;
    float attackTime = 1f;
    double startAttack;
    
    public RootBossAttack(){
        image = new GreenfootImage(endSize, endSize);
        sizeChange = 1/((endSize-startSize)/attackDelay);
        
        draw();
    }
    
    public void scaleImg(){
        currentTime = (System.currentTimeMillis()-startAttack)/1000;
        
        if(currentTime > attackProgress && !isActive){
            startSize++;
            attackProgress += sizeChange;
        }
        
        isActive = startSize >= endSize;
        
        if(isActive && !isDead && currentTime > (attackDelay + attackTime)){
            getWorld().removeObject(this);
            isDead = true;
        }
        
        draw();
    }
    
    public void draw(){
        if(isActive){
            image.setColor(Color.RED);
            image.fillOval((endSize-startSize)/2, (endSize-startSize)/2, endSize, endSize);
        }else {
            image.setColor(Color.BLACK);
            image.fillOval((endSize-startSize)/2, (endSize-startSize)/2, startSize, startSize);            
        }
        
        setImage(image);
    }
    
    public void act()
    {
        scaleImg();
        if(!isDead){
            
            Actor player = getOneIntersectingObject(Player.class);
        
            if(player != null && isActive){
                playerObject.takeDmg(1);
            }
        }
        
        
    }
    
    protected void addedToWorld(World world)
    {
        playerObject = (greenfoot.core.WorldHandler.getInstance()).getWorld().getObjects(Player.class).get(0);
        startAttack = System.currentTimeMillis();
    }
}
