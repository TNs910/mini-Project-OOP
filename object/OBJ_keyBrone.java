package object;


import entity.Entity;
import main.Gamepanel;

public class OBJ_keyBrone extends Entity{


    public OBJ_keyBrone (Gamepanel gp){
       
    super(gp) ; 

    name = "keyBrone";
    down1 = setup("/res/object/key-3",gp.tileSize,gp.tileSize) ;

        
   
    }
}
