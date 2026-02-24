import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;

/**
 * Write a description of class Minimap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Minimap extends Actor
{
    /**
     * Act - do whatever the Minimap wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage image;
    
    private int scrWidth = 1600;
    private int scrHeight = 900;
    
    private int roomD = 25;
    private int corrLen = 15;
    private int corrWid = 5;
    
    public Minimap(){
        image = new GreenfootImage(scrHeight/3, scrHeight/3);
        
        resetMinimap();
    }
    
    public void resetMinimap(){
        image.setColor(Color.LIGHT_GRAY);
        image.fillRect(0, 0, scrWidth/2, scrHeight/3);
        
        drawContour();
    }
    
    public void drawContour(){
        image.setColor(Color.BLACK);
        
        for(int i = 0; i<5; i++){
            image.drawRect(i, i, scrHeight/3-i*2, scrHeight/3-i*2);
        }
        
        setImage(image);
    }
    
    public void drawRoom(int x, int y, Room room){
        GreenfootImage roomImg = new GreenfootImage(roomD, roomD);
        
        
        if(room.isShop) {roomImg.setColor(Color.BLUE);}
        else if(room.isGarden) {roomImg.setColor(Color.GREEN);}
        else if(x == 0 && y == 0){roomImg.setColor(Color.GRAY);}
        else {roomImg.setColor(Color.BLACK);}
        
        
        roomImg.fillRect(0, 0, roomD, roomD);   
        
        
        image.drawImage(roomImg , (scrHeight/6-roomD/2) + x*(roomD + corrLen), (scrHeight/6-roomD/2) + y*(roomD + corrLen));
        
        drawContour();
    }
    
    public void drawCorridor(int xS, int yS, int xE, int yE){
        GreenfootImage corrImg = new GreenfootImage(corrLen, corrLen);
        
        corrImg.setColor(Color.RED);
        
        if(xS != xE){
            //horizontal
            corrImg.fillRect(0, 0, corrLen, corrWid);
            image.drawImage(corrImg , (scrHeight/6)+roomD-(int)(roomD/2) + xS*(roomD + corrLen), (scrHeight/6)-(int)(corrWid/2) + yS*(roomD + corrLen));
        } else{
            //vertical
            corrImg.fillRect(0, 0, corrWid, corrLen);
            image.drawImage(corrImg , (scrHeight/6)-(int)(corrWid/2) + xS*(roomD + corrLen), (scrHeight/6)+roomD-(int)(roomD/2) + yS*(roomD + corrLen));
        }
        
        drawContour();
    }
    
    public void loadRooms(HashMap<Pos, Room> rooms, int offsetX, int offsetY){
        resetMinimap();
        
        for (Pos pos : rooms.keySet()) {
            //System.out.println("x: " + (pos.x - offsetX) + " y: " + (pos.y - offsetY));
            drawRoom(pos.x - offsetX, pos.y - offsetY, rooms.get(new Pos(pos.x, pos.y)));
            
            if(rooms.get(pos).topDoor == true){
                drawCorridor(pos.x - offsetX, pos.y - offsetY-1, pos.x - offsetX, pos.y - offsetY);
            }
            if(rooms.get(pos).rightDoor == true){       
                drawCorridor(pos.x - offsetX, pos.y - offsetY, pos.x - offsetX+1, pos.y - offsetY);
            }
        }
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
