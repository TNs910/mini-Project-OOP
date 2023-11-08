package object;


import entity.Entity;
import main.Gamepanel;

public class OBJ_keyYL extends Entity{


    public OBJ_keyYL(Gamepanel gp){
       
    super(gp) ; 

    name = "keyYL";
    down1 = setup("/res/object/key",gp.tileSize,gp.tileSize) ;

        
   
    }
}
