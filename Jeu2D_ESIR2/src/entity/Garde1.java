package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Garde1 extends Entity{
	GamePanel gp;
	KeyHandler keyH;
		public Garde1(GamePanel gameP,KeyHandler keyh,int position_x ,int position_y ) {
			gp = gameP;
			keyH = keyh;
			x=position_x;
			y=position_y;
			getEnnemyImage();
		
		}
		
		public void getEnnemyImage() {
			try {
						
						idleImage = ImageIO.read(getClass().getResource("/player/New Piskel.png"));
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				}
		public void update() {
			
		}
		public void draw(Graphics2D g3) {
			// r�cup�re l'image du joueur
			BufferedImage image = idleImage;
			// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
			g3.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		}		
}
	
	

