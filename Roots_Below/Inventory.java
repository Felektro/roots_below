import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory extends Actor
{
    /**
     * Act - do whatever the Inventory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    public boolean isClosed = true;
    
    private int cellSize = 40;
    
    private int invWidth = 200 + 10;
    private int invHeight = cellSize*2 + 10;
    
    public ItemDrop[] items = new ItemDrop[10];
    public int[] itemCount = new int[10];
    
    int commonR = 50;
    int rareR = 30;
    int epicR = 15;
    int legendR = 5;
    
    public enum rarity{COMMON, RARE, EPIC, LEGEND};

    ArrayList<ItemDrop> commons = new ArrayList();
    ArrayList<ItemDrop> rares = new ArrayList();
    ArrayList<ItemDrop> epics = new ArrayList();
    ItemDrop legend;
    
    boolean firstTime = true;
    
    boolean pedestalMenuOpen = false;
    int pickedItem = 0;
    
    InventoryBackground bg = new InventoryBackground(this); 
    Player player;
    
    float inputDelay = 0.2f;
    double timeLastInput;
    
    private rarity pickRarity(){
        int rarityNum = Greenfoot.getRandomNumber(100);
        rarity itemRar;
        if(rarityNum <= commonR){
            itemRar = rarity.COMMON;
        }else if(commonR < rarityNum && rarityNum < commonR + rareR){
            itemRar = rarity.RARE;
        }else if(commonR + rareR <= rarityNum && rarityNum < commonR + rareR + epicR){
            itemRar = rarity.EPIC;
        } else {
            itemRar = rarity.LEGEND;
        }
        return itemRar;
    }
    
    
    public Inventory(){
        image = new GreenfootImage(invWidth, invHeight);
        
        setImage(drawContour(image, Color.BLACK, invWidth, invHeight));
        
        for (int i = 0; i < 5; i++){
            drawCell(cellSize, i * cellSize + 5, 5, Color.DARK_GRAY, Color.GRAY);
        }
        for (int i = 0; i < 5; i++){
            drawCell(cellSize, i * cellSize + 5, 5 + cellSize, Color.DARK_GRAY, Color.GRAY);
        }
        
        image.setTransparency(0);
        
        setImage(image);
        
        setUpItemPool();
        
        
    }
    
    public void setUpItemPool(){
        commons.add(new ItemDrop("Thorn Seed", "-", ItemDrop.seedType.THORN));
        commons.add(new ItemDrop("Sap Seed", "-", ItemDrop.seedType.SAP));
        commons.add(new ItemDrop("Bloom Seed", "-", ItemDrop.seedType.BLOOM));
        commons.add(new ItemDrop("Root Seed", "-", ItemDrop.seedType.ROOT));
        
        rares.add(new ItemDrop("Spore Seed", "-", ItemDrop.seedType.SPORE));
        rares.add(new ItemDrop("Vine Seed", "-", ItemDrop.seedType.VINE));
        rares.add(new ItemDrop("Flame Seed", "-", ItemDrop.seedType.FLAME));
        
        epics.add(new ItemDrop("Moon Seed", "-", ItemDrop.seedType.MOON));
        epics.add(new ItemDrop("Iron Seed", "-", ItemDrop.seedType.IRON));
        
        legend = new ItemDrop("Electro Seed", "-", ItemDrop.seedType.ZAP);
        
        
        //THORN, SAP, BLOOM, ROOT, SPORE, VINE, FLAME, MOON, IRON, ZAP
    }
    
    
    public void act()
    {
        if(firstTime){
            for(int i = 0; i < 10; i++){
                getWorld().addObject(new ItemDrop(), 200 + i * 50, 200);
            }
            
            firstTime = false;
            
            this.player = getWorld().getObjects(Player.class).get(0);
        }
        
        if(pedestalMenuOpen){
            if (Greenfoot.isKeyDown("a")) {
                moveMenu("left");
            }
            if (Greenfoot.isKeyDown("d")) {
                moveMenu("right");
            }
            if (Greenfoot.isKeyDown("w")) {
                moveMenu("up");
            }
            if (Greenfoot.isKeyDown("s")) {
                moveMenu("down");
            }
            if(Greenfoot.isKeyDown("space")){
                moveMenu("use");
            }
        }
        
    }
    
    public void moveMenu(String dir){
        
        if((System.currentTimeMillis() - timeLastInput)/1000.0 > inputDelay){
            switch(dir){
                case "right":
                    pickedItem++;
                    if(pickedItem > 9){
                        pickedItem = 0;
                    }
                    break;
                case "left":
                    pickedItem--;
                    if(pickedItem < 0){
                        pickedItem = 9;
                    }
                    break;
                case "up":
                    pickedItem -= 5;
                    if(pickedItem < 0){
                        pickedItem += 10;
                    }
                    break;
                case "down":
                    pickedItem += 5;
                    if(pickedItem > 9){
                        pickedItem -= 10;
                    }
                    break;
                case "use":
                    if(itemCount[pickedItem] > 0){
                        player.addBonus(items[pickedItem]);
                        itemCount[pickedItem]--;
                        if(itemCount[pickedItem] == 0){
                            items[pickedItem] = null;
                        }
                    }
                    
                    
                    break;
            }
            
            drawPickedCell(pickedItem);
            
            timeLastInput = System.currentTimeMillis();
        }
        
    }
    
    public void drawPickedCell(int cellID){
        for (int i = 0; i < items.length; i++){
            drawItem(i, Color.GRAY);
        }
        
        drawItem(cellID, Color.RED);
    }
    
    public void itemPickup(){
        rarity itemRar = pickRarity();
        ItemDrop item = null;
        
        switch(itemRar){
            case rarity.COMMON:
                item = commons.get(Greenfoot.getRandomNumber(commons.size()));
                break;
            case rarity.RARE:
                item = rares.get(Greenfoot.getRandomNumber(rares.size()));
                break;
            case rarity.EPIC:
                item = epics.get(Greenfoot.getRandomNumber(epics.size()));
                break;
            case rarity.LEGEND:
                item = legend;
                break;
        }
        
        //System.out.println(item);
        
        addToInv(item);
    }
    
    public void addToInv(ItemDrop item){
        int index = 0;
        
        for (int i = 0; i < items.length; i++){
            if(items[i] == null){
                items[i] = item;
                //System.out.println("added item " + item + " at " + i);
                index = i;
                itemCount[i]++; 
                break;
            }
            
            if(items[i].equals(item)){
                //System.out.println("added item " + item + " at " + i + " because theyre the same type of seed");
                index = i;
                itemCount[i]++;
                break;
            }
        }
        //System.out.println(Arrays.toString(itemCount));
        
        drawItem(index, Color.GRAY);
        
        setImage(image);
    }
    
    public void drawItem(int index, Color cBor){
        
        //System.out.println(Arrays.toString(items));
        //System.out.println(Arrays.toString(itemCount));
        
        Color c = (itemCount[index] != 0) ? items[index].getColor() : Color.DARK_GRAY;
        
        int column = index % 5;
        int row = (index < 5) ? 0 : 1;
        
        drawCell(cellSize, column * cellSize + 5, 5 + row * cellSize, c, cBor);
        
        setImage(image);
    }
    
    public void openInventory(){
        if(pedestalMenuOpen == false){
            for (int i = 0; i < items.length; i++){
                drawItem(i, Color.GRAY);
            }
        }
                
        if(isClosed){ image.setTransparency(255); }
        else{ image.setTransparency(0); }
        
        isClosed = !isClosed;
        setImage(image);
    }
    
    public void openPedestalMenu(){
        getWorld().addObject(bg, 800, 450);
        pedestalMenuOpen = true;
        pickedItem = 0;
        drawPickedCell(pickedItem);
        if(!isClosed){
            bg.close();
            pedestalMenuOpen = false;
        }
        
        openInventory();
    }

    public void closePedestalMenu(){
        player.usingPedestal = false;
        pedestalMenuOpen = false;
        openInventory();
    }
    
    public GreenfootImage drawContour(GreenfootImage img, Color color, int width, int height){
        img.setColor(color);
        
        for(int i = 0; i<5; i++){
            img.drawRect(i, i, width-i*2-1, height-i*2-1);
        }
        
        return img;
    }
    
    public void drawCell(int size, int xOffset, int yOffset, Color c, Color cBor){
        GreenfootImage cell = new GreenfootImage(size, size);
        
        cell.setColor(c);
        cell.fillRect(0, 0, size, size);
        
        cell.drawImage(drawContour(cell, cBor, size, size), 0, 0);
        
        image.drawImage(cell, xOffset, yOffset);
    }
    
    
    
}



