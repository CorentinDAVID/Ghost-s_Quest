package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
public class lac extends Entity{
	
	
	GamePanel gp;
	KeyHandler keyH;
	
	
	public lac(GamePanel gameP,KeyHandler keyh,int position_x,int position_y) {
		x=position_x;
		y=position_y;
		gp = gameP;
		keyH = keyh;
		getLacImage();
	}
	
	public void getLacImage() {
		try {
					
					idleImage = ImageIO.read(getClass().getResource("/player/lac.png"));
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
	public void draw(Graphics2D g3) {
		// r�cup�re l'image du joueur
		BufferedImage image = idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		g3.drawImage(image, x, y, 190,290, null);
	}
	
	public void update() {
		
	}
}

