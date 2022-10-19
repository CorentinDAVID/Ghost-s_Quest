package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
public class key extends Entity{
	
	
	GamePanel gp;
	KeyHandler keyH;
	
	
	public key(GamePanel gameP,KeyHandler keyh,int position_x,int position_y) {
		
		gp = gameP;
		keyH = keyh;
		getKeyImage();
		
		x = position_x;
		y = position_y;
	}
	
	public void getKeyImage() {
		try {
					
					idleImage = ImageIO.read(getClass().getResource("/player/key.png"));
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			}
	public void draw(Graphics2D g3) {
		// r�cup�re l'image du joueur
		BufferedImage image = idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		g3.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
	public void update() {
		
	}
}
