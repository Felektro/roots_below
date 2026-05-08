import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;


/**
 * Write a description of class Boss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Boss extends Actor 
{
    /**
     * Act - do whatever the Boss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    public String type;
    int maxHp = 25 + MyWorld.floorNumber*25;
    float hp;
    
    public int hitboxRadius = 75;
    
    public boolean isDead;
    
    BossHealthBar hpBar;
    
    Room room;

    Player playerObject;
    
    int amountAttacks = 3;
    int lastAttack = 0;
    
    float attackDelay = 5f;
    double timeLastAttack;
    
    public Boss(Room room){
        image = new GreenfootImage(150, 150);
        image.setColor(Color.RED);
        image.fillOval(0, 0, 150, 150);
        setImage(image);
        
        hp = maxHp;
        this.room = room;
    }
    
    protected void addedToWorld(World world)
    {
        //System.out.println("added to world");
        hpBar = new BossHealthBar(maxHp);
        world.addObject(hpBar, 800, 850);
        
        playerObject = (greenfoot.core.WorldHandler.getInstance()).getWorld().getObjects(Player.class).get(0);
        
        ((MyWorld)getWorld()).changeMusic("BossFightMusic.mp3");
        
    }
    
    public void act()
    {
        
        if(playerObject.detectHitbox(this, hitboxRadius)){
            playerObject.takeDmg(2);
        }
        
        if((System.currentTimeMillis() - timeLastAttack)/1000.0 > attackDelay){
            chooseAttack();
            //System.out.println("Attack");
            timeLastAttack = System.currentTimeMillis();
        }
        
    }
    
    
    public void chooseAttack(){
        int attackNum = lastAttack;
        while(attackNum == lastAttack){
            attackNum = Greenfoot.getRandomNumber(amountAttacks);
        }
        lastAttack = attackNum;
        
        switch(attackNum){
            case 0:
                rootAttack();
                //System.out.println("Attack 1 was picked!");
                break;
            case 1:
                spawnAttack();
                //System.out.println("Attack 2 was picked!");
                break;
            case 2:
                thirdAttack();
                //System.out.println("Attack 3 was picked!");
                break;
            
        }
        
    }
    
    public void rootAttack(){
        ArrayList<RootBossAttack> roots = new ArrayList<>();
        
        for (int i = 0; i < 3; i++){
            int x = 0;
            int y = 0;
            boolean valid;
            do {
                x = Greenfoot.getRandomNumber(1300) + 150;
                y = Greenfoot.getRandomNumber(600) + 150;
                valid = true;
                //dist(x, y, player.getX(), player.getY()) <= 300
                for (RootBossAttack root : roots){
                    valid = valid && dist(x, y, root.getX(), root.getY()) >= 300;
                }
                valid = valid && dist(x, y, playerObject.getX(), playerObject.getY()) >= 300;
                valid = valid && dist(x, y, this.getX(), this.getY()) >= 300;
            } while (!valid);
            RootBossAttack root = new RootBossAttack();
            roots.add(root);
            getWorld().addObject(root, x, y);
        }
    }
    
    public void spawnAttack(){
        ArrayList<Enemy> enemies = new ArrayList<>();
        
        for (int i = 0; i < 3; i++){
            int x = 0;
            int y = 0;
            boolean valid;
            do {
                x = Greenfoot.getRandomNumber(1300) + 150;
                y = Greenfoot.getRandomNumber(600) + 150;
                valid = true;
                //dist(x, y, player.getX(), player.getY()) <= 300
                for (Enemy enemy : enemies){
                    valid = valid && dist(x, y, enemy.x, enemy.y) >= 300;
                }
                valid = valid && dist(x, y, playerObject.getX(), playerObject.getY()) >= 300;
                valid = valid && dist(x, y, this.getX(), this.getY()) >= 300;
            } while (!valid);
            Enemy.EnemyType type;
            if(Greenfoot.getRandomNumber(2) == 0){
                type = Enemy.EnemyType.TURRET;
            }else{
                type = Enemy.EnemyType.SLIME;
            }
            Enemy enemy = new Enemy(x, y, room, true, type);
            enemies.add(enemy);
            getWorld().addObject(enemy, x, y);
        }
        
    }
    
    public void thirdAttack(){
        
    }
    
    
    
    public double dist(int x1, int y1, int x2, int y2){
        return Math.sqrt((Math.pow(x1 - x2, 2)+Math.pow(y1 - y2, 2)));
    }
    
    public void remove(){
        if(!isDead){
            
            List<Enemy> enemies = getWorld().getObjects(Enemy.class);   
            
            for(Enemy enemy : enemies){
                enemy.remove();
            }
            
            getWorld().addObject(new CellarDoor(), 800, 550);
            getWorld().removeObject(this);
            
            isDead = true;
            room.isCleared = true;
            room.openDoors();
        
        }
    }
    
    public void takeDmg(float dmg){
        hp -= dmg;
        
        hpBar.hp = (int)hp;
        
        
        
        if(hp <= 0){
            ((MyWorld)getWorld()).changeMusic("BackgroundMusic.mp3");
            remove();
            MyWorld.bossesSlain++;
            Greenfoot.playSound("enemy_sound.mp3");
            Greenfoot.playSound("victory_sound.mp3");
        }
    }
}
