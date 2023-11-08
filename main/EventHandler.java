package main;

public class EventHandler {

    Gamepanel gp;
    EventRect eventRect[][][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(Gamepanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldcol][gp.maxWorldrow];

        int map = 0;
        int col = 0;
        int row = 0;

        while (map < gp.maxMap && col < gp.maxWorldcol && row < gp.maxWorldrow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].evenRectDefaultx = eventRect[map][col][row].x;
            eventRect[map][col][row].evenRectDefaulty = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldcol) {
                col = 0;
                row++;

                if (row == gp.maxWorldrow) {
                    row = 0;
                    map++;

                }
            }
        }

    }

    public void checkEvent() {
        // checkEvent
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent == true) {
            if (hit(0, 43, 28, "any") == true) {
                damagePit(gp.dialogState);
            }

            // else if (hit(0, 44, 3, "up") == true) {
            // healingPool(gp.dialogState);
            // }

            // teleport map1
            else if (hit(0, 24, 49, "any") == true) {
                teleport(1, 24, 2);
            } else if (hit(1, 24, 1, "any") == true) {
                teleport(0, 24, 47);
            }
            // teleport map2
            else if (hit(0, 44, 3, "up") == true) {
                teleportwithEnter(2, 25, 45);
            } else if (hit(2, 25, 48, "any") == true) {
                teleport(0, 44, 4);
            } else if (hit(2, 25, 49, "any") == true)  {
                teleport(0, 44, 4);
            }

            else if (hit(1, 6, 44, "any")
                    || hit(1, 8, 40, "any")
                    || hit(1, 15, 42, "any")
                    || hit(1, 23, 43, "any")
                    || hit(1, 29, 42, "any")
                    || hit(1, 33, 41, "any")
                    || hit(1, 41, 44, "any")
                    || hit(1, 42, 39, "any")
                    || hit(1, 19, 41, "any")) {
                damagePit(gp.gameState);
            } 
             else if (hit(1, 24, 47, "any") == true) {
                endgamewithEnter(1);
            
             }
              else if (hit(1, 24, 44, "any") == true) {
                Demon();
            
             }
        }

    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;

        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if (gp.player.solidArea.intersects(eventRect[map][col][row])
                    && eventRect[map][col][row].eventDone == false) {
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].evenRectDefaultx;
            eventRect[map][col][row].y = eventRect[map][col][row].evenRectDefaulty;

        }

        return hit;
    }

    public void damagePit(int gameState) {

        if (gp.player.invincible == false) {
            gp.playSE(16);
            gp.player.life -= 1;
            gp.player.invincible = true;
            canTouchEvent = false;
        }

    }

    // public void healingPool(int gameState) {

    // if (gp.keyH.enterPressed == true) {
    // gp.gameState = gameState;
    // gp.playSE(11);
    // gp.ui.currentDialog = "This is a sacred tree? feel some power ! ";
    // gp.player.life = gp.player.maxLife;
    // }

    // }

    public void teleportwithEnter(int map, int col, int row) {
        if (gp.keyH.enterPressed == true) {
            gp.currentMap = map;
            gp.player.worldX = col * gp.tileSize;
            gp.player.worldY = row * gp.tileSize;

            previousEventX = gp.player.worldX;
            previousEventY = gp.player.worldY;
            canTouchEvent = false;
            gp.playSE(15);

        }

    }
    public void endgamewithEnter(int map){
        if (gp.keyH.enterPressed == true) {
            gp.currentMap = map;
            
            gp.playSE(18);
            gp.endGame();

        }
    }

    public void teleport(int map, int col, int row) {

        gp.currentMap = map;
        gp.player.worldX = col * gp.tileSize;
        gp.player.worldY = row * gp.tileSize;

        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
        gp.playSE(15);
    }

    public void Demon(){
        if(gp.bossBattleOn == false ){
            gp.csManager.sceneNum = gp.csManager.Demon ; 

        }

    }


}
