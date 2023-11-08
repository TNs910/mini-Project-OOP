package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
  Gamepanel gp;
  public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

  // debug
  boolean showDebugText = false;
  public boolean godModeOn = false;

  // ______________________________________________________________________________________
  
  public KeyHandler(Gamepanel gp) {
    this.gp = gp;
  }

  @Override
  public void keyTyped(KeyEvent e) {

    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();

    // title state
    if (gp.gameState == gp.titleState) {

      if (gp.ui.titleScreenState == 0) {

        if (code == KeyEvent.VK_W) {
          gp.playSE(13);
          gp.ui.commandNum--;
          if (gp.ui.commandNum < 0)
            gp.ui.commandNum = 2;

        }
        if (code == KeyEvent.VK_S) {
          gp.playSE(13);
          gp.ui.commandNum++;
          if (gp.ui.commandNum > 2)
            gp.ui.commandNum = 0;
        }

        if (code == KeyEvent.VK_ENTER) {
          if (gp.ui.commandNum == 0) {
            gp.playSE(14);
            gp.gameState = gp.playState;
            gp.playMusic(0);
            gp.restart();
          }
          if (gp.ui.commandNum == 1) {
            // ลบหน้าต่าง Jframe ออก
            gp.Exit();

          }

        }

      }

    }

    // play state

    else if (gp.gameState == gp.playState) {

      if (code == KeyEvent.VK_W) {
        upPressed = true;

      }
      if (code == KeyEvent.VK_S) {
        downPressed = true;
      }
      if (code == KeyEvent.VK_A) {
        leftPressed = true;
      }
      if (code == KeyEvent.VK_D) {
        rightPressed = true;
      }
      if (code == KeyEvent.VK_P) {
        gp.gameState = gp.pauseState;

      }
      if (code == KeyEvent.VK_ENTER) {
        enterPressed = true;

      }
      // debug
      if (code == KeyEvent.VK_T) {
        if (showDebugText == false) {
          showDebugText = true;
        } else if (showDebugText == true) {
          showDebugText = false;

        }
      }
      if (code == KeyEvent.VK_R) {

        switch (gp.currentMap) {
          case 0:
            gp.tileM.loadMap("/res/maps/worldmap01.txt", 0);
            break;
          case 1:
            gp.tileM.loadMap("/res/maps/worldmap02.txt", 1);
            break;
          case 2:
            gp.tileM.loadMap("/res/maps/worldmap03.txt", 2);
            break;
        }

      }
      if (code == KeyEvent.VK_G) {
        if (godModeOn == false) {
          godModeOn = true;
        } else if (godModeOn == true) {
          godModeOn = false;

        }
      }

    }

    // pause state
    else if (gp.gameState == gp.pauseState) {
      if (code == KeyEvent.VK_P) {
        gp.gameState = gp.playState;

      }

    } else if (gp.gameState == gp.dialogState) {
      if (code == KeyEvent.VK_ENTER) {
        gp.gameState = gp.playState;
      }

    } 
    else if (gp.gameState == gp.gameOverState) {
      if (code == KeyEvent.VK_W) {
        gp.playSE(13);
        gp.ui.commandNum--;
        if (gp.ui.commandNum < 0) {
          gp.ui.commandNum = 1;
        }
      }
      if (code == KeyEvent.VK_S) {
        gp.playSE(13);
        gp.ui.commandNum++;
        if (gp.ui.commandNum > 1) {
          gp.ui.commandNum = 0;
        }
      }
      if (code == KeyEvent.VK_ENTER) {
        if (gp.ui.commandNum == 0) {
          gp.playSE(14);
          gp.gameState = gp.playState;
          gp.retry();
          gp.playMusic(0);
        } else if (gp.ui.commandNum == 1) {
          gp.gameState = gp.titleState;
          gp.playSE(14);
          gp.restart();
        }
      }
    } 
    else if (gp.gameState == gp.gameFinished) {
      
      if (code == KeyEvent.VK_ENTER) {
        if (gp.ui.commandNum == 0) 
        {
          gp.gameState = gp.titleState;
        } 
      }
    }

  }

  // dialog state

  @Override
  public void keyReleased(KeyEvent e) {

    int code = e.getKeyCode();

    if (code == KeyEvent.VK_W) {
      upPressed = false;

    }
    if (code == KeyEvent.VK_S) {
      downPressed = false;
    }
    if (code == KeyEvent.VK_A) {
      leftPressed = false;
    }
    if (code == KeyEvent.VK_D) {
      rightPressed = false;
    }

    throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
  }

}
