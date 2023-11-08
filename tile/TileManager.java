package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;

import main.Gamepanel;
import main.UtilityTool;

public class TileManager {

    Gamepanel gp; 

    public Tile[] tile;

    public int mapTileNum[][][];

    public TileManager(Gamepanel gp) {
        this.gp = gp;

        tile = new Tile[100]; 

        mapTileNum = new int[gp.maxMap][gp.maxWorldcol][gp.maxWorldrow];

        getTileImage();

        loadMap("/res/maps/worldmap01.txt",0);
        loadMap("/res/maps/worldmap02.txt",1);
        loadMap("/res/maps/worldmap03.txt",2);

    }

    public void getTileImage() {
        try {

            // ดิน
            setup(0, "earth", false);
            // ดินมีหญ้า
            setup(1, "grass-1", false);
            // หญ้ามีต้นไม้
            setup(2, "grassflower", false);
            // บล็อคหิน
            setup(3, "rock-floor1", false);
            // ทราย
            setup(4, "sand", false);
            // บล็อคหินสีเหลือง
            setup(5, "sandZ", false);
            // ต้นไม้ ปกติ
            setup(6, "TreeP", true);
            // ต้นไม้ มีใบ 2 หย่อม
            setup(7, "Tree2Green", true);
            // ต้นไม้ มีใบ หย่อม
            setup(8, "TreeAP", true);
            // กำแพงหิน
            setup(9, "rock1-1", true);
            // ดินมีน้ำ
            setup(10, "water and green", true);
            // น้ำ
            setup(11, "water", true);
            // น้ำ1
            setup(12, "water1", true);
            // บล็อคไม้
            setup(13, "Wood", true);
            // บล็อคไม้2
            setup(14, "Wood_1", true);
            // พิ้นไม้
            setup(15, "Wood_BG", false);
            // บล็อคพื้นทราย
            setup(16, "boxsand", true);
            // บล็อคพื้นดอกสีแดง
            setup(17, "flowerred", false);
            //ดินข้างบน น้ำข้างล่าง
            setup(18, "Wateraroud0", true);
            //ดินข้างบน ขวา  น้ำข้างล่าง
            setup(19, "Wateraroud1", true);
            //ดินข้างบน ซ้าย  น้ำข้างล่าง
            setup(20, "Wateraroud2", true);
            //ดินข้างล่าง ซ้าย  น้ำข้างบน
            setup(21, "Wateraroud3", true);
            //ดินข้างล่าง ขวา  น้ำข้างบน
            setup(22, "Wateraroud4", true);
            //ดินข้างขวา น้ำข้างซ้าย
            setup(23, "Wateraroud5", true);
            //ดินข้างซ้าย น้ำข้างขวา
            setup(24, "Wateraroud6", true);
            //น้ำ
            setup(25, "Wateraroud7", true);
            // ดินข้างล่าง น้ำข้างบน
            setup(26, "Wateraroud8", true);
            // ดิน
            setup(27, "Wateraroud9", false);
            // หัวมุมน้ำ
            setup(28, "Wateraroud10", true);
            //ดินมีหญ้า ข้างๆ 
            setup(29, "sand_grass", false);

            setup(30, "Grass_sand_left", false);

            setup(31, "Grass_sand_right", false);

            setup(32, "Grass_sand_up", false);

            setup(33, "Grass_sand_down", false);

            setup(34, "Grass_sand_left_down", false);

            setup(35, "Grass_sand_right_down", false);

            setup(36, "Grass_sand_left_up", false);

            setup(37, "Grass_sand_right_up", false);

            setup(38, "sand_grass_LEFT", false);

            setup(39, "Underwood", true);

            setup(40, "cactus", true);

            setup(41, "Shaw", true);

            setup(42, "small_Shaw", true);

            setup(43, "Shack", true);

            setup(44, "Small_Bush", true);

            setup(45, "plant pot", true);

            setup(46, "grass_new", false);

            setup(47, "Goldtree", true);

            setup(48, "Tree01", true);

            setup(49, "2pum", true);

            setup(50, "grass-2", false);

            setup(51, "wall", true);

            setup(52, "rockfloor", false);

            setup(53, "Wateraroud10-1", true);

            setup(54, "Wateraroud10-2", true);

            setup(55, "Wateraroud10-3", true);

            setup(56, "boxrockUnder", false);

            setup(57, "lava", false);

            setup(58, "Goldtree-1", false);

            setup(59, "Goldtree_big", true);

            setup(60, "mini_demon", true);

            setup(61, "sand_grass - 1", true);

            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setup(int index, String imagePath, boolean collision) {

        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imagePath + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath,int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldcol && row < gp.maxWorldrow) {

                String line = br.readLine();

                if (line == null) {
                    break; // อ่านถึงสิ้นสุดของไฟล์
                }

                while (col < gp.maxWorldcol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldcol) {
                    col = 0;
                    row++;
                }
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldcol && worldRow < gp.maxWorldrow) {
            int num = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

              g2.drawImage(tile[num].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }

            worldCol++;

            if (worldCol == gp.maxWorldcol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

}
