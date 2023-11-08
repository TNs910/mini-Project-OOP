package main;

import java.awt.Graphics2D;

import object.OBJ_door;

public class CutsceneManager {

    Gamepanel gp ; 
    Graphics2D g2 ; 
    public  int sceneNum ;
    public  int scenePhase ;
    int counter = 0 ; 
    float alpha = 0f ; 
    int y  ;

    public final int  NA = 0 ; 
    public final int Demon = 1 ; 
    public final int ending  = 2 ;

    public CutsceneManager(Gamepanel gp){
        this.gp = gp ; 

    }

    public void draw(Graphics2D g2){
        this.g2 = g2 ; 

        switch (sceneNum) {
            case Demon:
                scene_demon();
                break;
            case ending:
                scene_ending();
                break;
        }   
    }
    public void  scene_demon(){
        if(scenePhase == 0 ){
            gp.bossBattleOn = true ; 
            for( int i = 0 ; i < gp.obj[1].length ; i++){
                if(gp.obj[gp.currentMap][i] == null){
                    gp.obj[gp.currentMap][i] = new OBJ_door(gp) ; 
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 24;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 48;
                    gp.obj[gp.currentMap][i].temp = true   ;

                }

            }
        }
    }

    public void scene_ending(){
        
    }
    
}
