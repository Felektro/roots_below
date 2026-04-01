import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    public GameManager gm;
    
    public int playerSpeed = 5;
    public HoeWeapon hoeWeapon;
    
    int hoeTurn;
    boolean usingHoe;
    int lastSwing = 1;
    int coneAngle = 120;
    int turnAngle = 7;
    int hoeDir = 2;
    int hoeRot = 0;
    
    int hoeOffsetX = 0;
    int hoeOffsetY = 20;
    
    float swingDelay = 0.3f;
    double timeLastSwing;
    
    float animDelay = 0.1f;
    double timeLastFrame;
    int currentFrame = 0;
    
    int wallWidth = 15 * 10; // 15 pixels
    int wallOffset = 10;
    
    GreenfootImage[] playerRight = new GreenfootImage[8];
    GreenfootImage[] playerLeft = new GreenfootImage[8];
    GreenfootImage[] playerUp = new GreenfootImage[8];
    GreenfootImage[] playerDown = new GreenfootImage[8];
    
    
    public Player(HoeWeapon hoe, GameManager gm){
        hoeWeapon = hoe;
        this.gm = gm;
        
        hoeWeapon.turn(90 + coneAngle/2);
        
        setImage(scaleImage("player_front.png"));
        setAnimImages();
    }
    
    public void act()
    {
        movement();
        roomTransition();
        hoeUse();
        
        //System.out.println(getX() + "  " + getY());
    }
    
    public void movement(){
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - playerSpeed, getY());
            animate(playerLeft);
            getWorld().setPaintOrder(Minimap.class, BossHealthBar.class, HoeWeapon.class, Player.class, Door.class);
            hoeOffsetX = -25;
            wallOffset = -10;
            hoeDir = 3;
            if(checkWall()) {setLocation(getX() + playerSpeed, getY());}
            //if(!usingHoe) {hoeWeapon.setRotation(180 + coneAngle/2*lastSwing);}
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + playerSpeed, getY());
            animate(playerRight);
            getWorld().setPaintOrder(Minimap.class, BossHealthBar.class, HoeWeapon.class, Player.class, Door.class);
            hoeOffsetX = 25;
            wallOffset = 10;
            hoeDir = 1;
            if(checkWall()) {setLocation(getX() - playerSpeed, getY());}
            //if(!usingHoe) {hoeWeapon.setRotation(0 + coneAngle/2*lastSwing);}
        }
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - playerSpeed);
            animate(playerUp);
            getWorld().setPaintOrder(Minimap.class, BossHealthBar.class, Player.class, HoeWeapon.class, Door.class);
            hoeOffsetX = 0;
            hoeDir = 4;
            if(checkWall()) {setLocation(getX(), getY() + playerSpeed);}
            //if(!usingHoe) {hoeWeapon.setRotation(270 + coneAngle/2*lastSwing);}
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + playerSpeed);
            animate(playerDown);
            getWorld().setPaintOrder(Minimap.class, BossHealthBar.class, HoeWeapon.class, Player.class, Door.class);
            hoeOffsetX = 2;
            hoeDir = 2;
            if(checkWall()) {setLocation(getX(), getY() - playerSpeed);}
            //if(!usingHoe) {hoeWeapon.setRotation(90 + coneAngle/2*lastSwing);}
        }
    }
    
    public void hoeUse(){
        hoeWeapon.setLocation(getX() + hoeOffsetX, getY() + hoeOffsetY);
        hoeWeapon.setRotation((hoeDir-1)*90 + coneAngle/2*lastSwing + hoeRot);
        
        if (Greenfoot.isKeyDown("space") && !usingHoe && (System.currentTimeMillis() - timeLastSwing)/1000.0 > swingDelay) {
            usingHoe = true;
            hoeWeapon.isUsed = true;
            hoeTurn = coneAngle;
        }
        if(usingHoe){
            //hoeWeapon.turn(turnAngle*-1 * lastSwing);
            
            hoeTurn = hoeTurn - turnAngle;
            hoeRot = hoeRot + turnAngle*-1 * lastSwing;
            if(hoeTurn <= 0){
                usingHoe = false;
                hoeWeapon.isUsed = false;
                lastSwing *= -1;
                timeLastSwing = System.currentTimeMillis();
                if(lastSwing == -1){ hoeWeapon.setImg("hoe2.png"); }
                else { hoeWeapon.setImg("hoe1.png"); }
                hoeRot = 0;
                
            }
        }
        
        //hoeWeapon.setRotation((hoeDir-1)*90 + coneAngle/2*lastSwing + hoeRot);
    }
    
    public void roomTransition(){
        Door.DoorType transition = Door.DoorType.UP;
        boolean switchRooms = false;
        
        int x = getX();
        int y = getY();
        
        if((775 <= x && x <= 825) && y == 95){
            transition = Door.DoorType.UP;
            switchRooms = true;
        }
        if((775 <= x && x <= 825) && y == 685){
            transition = Door.DoorType.DOWN;
            switchRooms = true;
        }
        if(x == 1435 && (375 <= y && y <= 425)){
            transition = Door.DoorType.RIGHT;
            switchRooms = true;
        }
        if(x == 165 && (375 <= y && y <= 425)){
            transition = Door.DoorType.LEFT;
            switchRooms = true;
        }
        //System.out.println(switchRooms);
        if(switchRooms){gm.changeRoom(transition, this); switchRooms = false;}
    }
    
    public void animate(GreenfootImage[] anim){
        setImage(anim[currentFrame]);
        if((System.currentTimeMillis() - timeLastFrame)/1000.0 > animDelay){
            currentFrame++;
            if(currentFrame == 8){currentFrame = 0;}
            timeLastFrame = System.currentTimeMillis();
        }
    }
    
    public void setAnimImages(){
        for (int i = 0; i < 8; i++){
            playerRight[i] = scaleImage("player_right_" + (i+1) + ".png");
        }
        
        for (int i = 0; i < 8; i++){
            playerLeft[i] = scaleImage("player_right_" + (i+1) + ".png");
            playerLeft[i].mirrorHorizontally();
        }
        for (int i = 0; i < 8; i++){
            playerDown[i] = scaleImage("player_front_" + (i+1) + ".png");
        }
        for (int i = 0; i < 8; i++){
            playerUp[i] = scaleImage("player_back_" + (i+1) + ".png");
        }
    }
    
    public GreenfootImage scaleImage(String img){
        GreenfootImage image = new GreenfootImage(img);
        
        image.scale((int)(image.getWidth()*5), (int)(image.getHeight()*5)); 
        
        return image;
         
    }
    
    public boolean checkWall(){
        int x = getX() + wallOffset;
        int y = getY() + 60;
        
        return !((wallWidth < x && x < 1600 - wallWidth) && (wallWidth < y && y < 900 - wallWidth));
    }
}
