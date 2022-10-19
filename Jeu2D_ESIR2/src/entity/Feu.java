package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Feu extends Entity{
	GamePanel gp;
	KeyHandler keyH;
    int xbase;
		public Feu(GamePanel gameP,int y){
			gp = gameP;
			getEnnemyImage();
            Random random = new Random();
            int value = random.nextInt(10);
            if(value<5){
                xbase=0;
            }
            else{
                xbase=767;
            }
			setDefaultValues(y);
		
		}
		public void setDefaultValues(int y) {
			// Initialise les valeurs par d�faut
			// y entre 96 et 768-96=672
			//
            this.x=xbase;
			this.y=y;
			speed = 0;
		}
		
		public void setX(int x) {
			this.x=x;
		}
		
		public void getEnnemyImage() {
			try {
						
						idleImage = ImageIO.read(getClass().getResource("/player/feu.png"));
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				
				}
		public void update() {
			if(xbase==0){
				x+=5;
			}
			else{
				x-=5;

			}
			
		}
		public void draw(Graphics2D g2) {
			// r�cup�re l'image du joueur
			BufferedImage image = idleImage;
			// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
			g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		}		
}
	
	

