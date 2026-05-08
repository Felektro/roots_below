import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ItemDrop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemDrop extends Actor
{
    /**
     * Act - do whatever the ItemDrop wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    GreenfootImage image;
    
    public String name;
    public String iconName;
    public seedType seed;
    
    
    public enum seedType {THORN, SAP, BLOOM, ROOT, SPORE, VINE, FLAME, MOON, IRON, ZAP};
    
    
    
    public ItemDrop (){
        image = new GreenfootImage(50, 50);
        image.setColor(Color.YELLOW);
        image.fillOval(0, 0, 50, 50);
        setImage(image);
        
        this.name = "item drop";
    }
    
    public ItemDrop(String name, String iconBar, seedType seed){
        this.name = name;   
        this.iconName = iconName;
        this.seed = seed;
    }
    
    public void act()
    {
        Player player = (Player)getOneIntersectingObject(Player.class);
        if (player != null){
            player.addToInv();
            getWorld().removeObject(this);
        }
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public Color getColor(){
        Color c = null;
        switch(seed){
            case seedType.THORN:
                c = new Color(139, 69, 19);
                break;
            case seedType.SAP:
                c = new Color(34, 139, 34);
                break;
            case seedType.BLOOM:
                c = new Color(255, 215, 0);
                break;
            case seedType.ROOT:
                c = new Color(160, 82, 45);
                break;
            case seedType.SPORE:
                c = new Color(128, 0, 128);
                break;
            case seedType.VINE:
                c = new Color(0, 128, 0);
                break;
            case seedType.FLAME:
                c = new Color(255, 69, 0);
                break;
            case seedType.MOON:
                c = new Color(72, 61, 139);
                break;
            case seedType.IRON:
                c = new Color(169, 169, 169);
                break;
            case seedType.ZAP:
                c = new Color(0, 255, 255);
                break;
        }
        
        return c;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return ((ItemDrop)obj).seed == this.seed;
    }
}
