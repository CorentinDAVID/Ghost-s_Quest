package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Arrow extends Entity{
	GamePanel gp;
	KeyHandler keyH;
		public Arrow(GamePanel gameP,int x){
			gp = gameP;
			setDefaultValues(x);
			getEnnemyImage();
		
		}
		public void setDefaultValues(int x) {
			// Initialise les valeurs par d�faut
			// y entre 96 et 768-96=672
			//
			this.x=x;
			this.y=-100;
			speed = 0;
		}
		
		public void setX(int x) {
			this.x=x;
		}
		
		public void getEnnemyImage() {
			try {
						
						idleImage = ImageIO.read(getClass().getResource("/player/arrow.png"));
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				}
		public void update() {
			y+=5;
			
		}
		public void draw(Graphics2D g2) {
			// r�cup�re l'image du joueur
			BufferedImage image = idleImage;
			// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
			g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		}		
}
	
	

