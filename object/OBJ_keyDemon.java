package object;


import entity.Entity;
import main.Gamepanel;

public class OBJ_keyDemon extends Entity{


    public OBJ_keyDemon (Gamepanel gp){
       
    super(gp) ; 

    name = "keyDemon";
    down1 = setup("/res/object/key-2",gp.tileSize,gp.tileSize) ;

        
   
    }
}
