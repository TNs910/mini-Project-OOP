package main;

import entity.NPC_Fox;
import entity.NPC_Slime;
import monster.MON_Demon;
import monster.MON_LittleDragon;
import monster.MON_SlimeRed;
import monster.MON_miniboss;
import object.OBJ_door;
import object.OBJ_doorBrone;
import object.OBJ_keyYL;
import object.OBJ_keyDemon;
import object.OBJ_keyBrone;
import object.OBJ_doorYL;
import object.OBJ_honey;
import object.OBJ_goldApple;

public class AssetSetter {

    Gamepanel gp;

    public AssetSetter(Gamepanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // MAP 0
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_doorYL(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 44;
        gp.obj[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.obj[mapNum][i] = new OBJ_keyBrone(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 44;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        gp.obj[mapNum][i] = new OBJ_doorBrone(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 2;
        gp.obj[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        gp.obj[mapNum][i] = new OBJ_keyDemon(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 2;
        gp.obj[mapNum][i].worldY = gp.tileSize * 37;
        i++;
        gp.obj[mapNum][i] = new OBJ_door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 48;
        i++;
        gp.obj[mapNum][i] = new OBJ_honey(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 2;
        i++;
        // MAP 1
        mapNum = 1;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_keyDemon(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 4;
        gp.obj[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.obj[mapNum][i] = new OBJ_door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 25;
        i++;
        gp.obj[mapNum][i] = new OBJ_honey(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 45;
        gp.obj[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.obj[mapNum][i] = new OBJ_door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 44;
        i++;
        gp.obj[mapNum][i] = new OBJ_door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 24;
        gp.obj[mapNum][i].worldY = gp.tileSize * 43;
        i++;
         gp.obj[mapNum][i] = new OBJ_keyDemon(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 45;
        gp.obj[mapNum][i].worldY = gp.tileSize * 33;
        i++;
         gp.obj[mapNum][i] = new OBJ_keyDemon(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 4;
        gp.obj[mapNum][i].worldY = gp.tileSize * 34;
        i++;
       
        // MAP 2
        mapNum = 2;
        i = 0;
        gp.obj[mapNum][i] = new OBJ_keyYL(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 12;
        i++;
        gp.obj[mapNum][i] = new OBJ_goldApple(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 39;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;
    }

    public void setNPC() {
        // MAP 0
        int mapNum = 0;
        int i = 0;
        gp.npc[mapNum][i] = new NPC_Slime(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 42;
        gp.npc[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.npc[mapNum][i] = new NPC_Fox(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 18;
        gp.npc[mapNum][i].worldY = gp.tileSize * 19;
        i++;
    }

    public void setMonster() {
        // MAP 0
        int mapNum = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_SlimeRed(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 22;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        gp.monster[mapNum][i] = new MON_SlimeRed(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 16;
        gp.monster[mapNum][i].worldY = gp.tileSize * 16;
        i++;
        gp.monster[mapNum][i] = new MON_LittleDragon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 16;
        i++;
        gp.monster[mapNum][i] = new MON_LittleDragon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.monster[mapNum][i] = new MON_LittleDragon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 14;
        gp.monster[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        gp.monster[mapNum][i] = new MON_LittleDragon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;
        i++;


        mapNum = 1;
        i = 0;
        gp.monster[mapNum][i] = new MON_SlimeRed(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.monster[mapNum][i] = new MON_SlimeRed(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;
        i++;
        gp.monster[mapNum][i] = new MON_LittleDragon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 14;
        gp.monster[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.monster[mapNum][i] = new MON_LittleDragon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 7;
        gp.monster[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.monster[mapNum][i] = new MON_LittleDragon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 7;
        gp.monster[mapNum][i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[mapNum][i] = new MON_SlimeRed(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 14;
        gp.monster[mapNum][i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[mapNum][i] = new MON_SlimeRed(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 28;
        gp.monster[mapNum][i].worldY = gp.tileSize * 22;
        i++;
        gp.monster[mapNum][i] = new MON_LittleDragon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 33;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;
        i++;
        gp.monster[mapNum][i] = new MON_LittleDragon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 20;
        i++;
        gp.monster[mapNum][i] = new MON_Demon(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;

    
        mapNum = 2;
        i = 0;
        gp.monster[mapNum][i] = new MON_miniboss(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;

    }

}
