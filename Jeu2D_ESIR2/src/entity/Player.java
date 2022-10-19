package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

import java.util.List;
import java.util.Scanner;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	String action_precedente;
	int Position_x;
	int Position_y;
	BufferedImage idleImage;
	int nb_pieces;
	int nb_vie;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		nb_pieces = 0;
		nb_vie = 3;
		setDefaultValues();
		getPlayerImage();
	}
	
	public int getNbPieces() {
		return nb_pieces;
	}
	
	
	public int getNb_vie() {
		return nb_vie;
	}

	public void setNb_vie(int nb_vie) {
		this.nb_vie = nb_vie;
	}

	public void setDefaultValues() {
		// Initialise les valeurs par d�faut
		x = 24;
		y = 62;
		Position_x = x+16;
		Position_y = y+16;
		speed = 4;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void getPlayerImage() {
		try {
			
			idleImage = ImageIO.read(getClass().getResource("/player/SLIME.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void toucher() {
		List<Arrow> arrows = this.gp.getArrows();
		List<Feu> fire = this.gp.getFeu();

		for(int i=0;i<arrows.size();i++) {
			if(contact(arrows.get(i))&&nb_vie>0) {
				arrows.remove(i);
				nb_vie = nb_vie - 1;
			}
		}
		
		for(int i=0;i<fire.size();i++) {
			if(contact(fire.get(i))&&nb_vie>0) {
				nb_vie = nb_vie - 1;
			}
		}
	}
	
	public boolean contact(Entity e) {
		if(x < e.x+10 && x > e.x-10 && y < e.y+10 && y > e.y-10) {
			
			return true;
		}
		return false;
	}
	
	public void verifPosition() {
		if(Position_x<16) {
			x = 5;
		}
		if(Position_y<16) {
			y = 5;
		}
		if(Position_x>752) {
			x = 760;
		}
		if(Position_y>560) {
			y = 570;
		}
	}
	
	public boolean dansLave() {
		return gp.getTileM().getMapTileNum()[Position_x/48][Position_y/48] == 3;
	}
	
	public void updatePosition() {
		Position_x = x+16;
		Position_y = y+16;
	}
	
	public void update() throws IOException {
		updatePosition();
		verifPosition();
		String new_info = keyH.action;
		
		move(new_info);
		
		if(gp.getTileM().getMapTileNum()[Position_x/48][Position_y/48] == 7) {
			gp.getTileM().getMapTileNum()[Position_x/48][Position_y/48] = 0;
			nb_pieces++;
		}
		
		action_precedente = new_info;
		
	}
	
	public void move(String info) throws IOException {
		if(info == "left" && caseValide(info)) {
			x = x - speed;
			idleImage = ImageIO.read(getClass().getResource("/player/SLIME_LEFT.png"));
		}
		
		if(info == "right" && caseValide(info)) {
			x = x + speed;
			idleImage = ImageIO.read(getClass().getResource("/player/SLIME_RIGHT.png"));
		}
		
		if(info == "up" && caseValide(info)) {
			y = y - speed;
			idleImage = ImageIO.read(getClass().getResource("/player/SLIME_UP.png"));
		}
		
		if(info == "down" && caseValide(info)) {
			y = y + speed;
			idleImage = ImageIO.read(getClass().getResource("/player/SLIME.png"));
		}
		
		if(info == "espace") {
			if(action_precedente == "left") {
				setX(getX()-60);
			}
			if(action_precedente == "right") {
				setX(getX()+60);
			}
			if(action_precedente == "up") {
				setY(getY()-60);
			}
			if(action_precedente == "down") {
				setY(getY()+60);
			}
		}
	}
	
	public boolean caseValide(String direction) {
		if(direction == "left") {
			int type_case = gp.getTileM().getMapTileNum()[(Position_x/48)][Position_y/48];
			if(type_case == 1 | type_case == 2 | type_case == 6) {
				return false;
			}
		}
		
		if(direction == "right") {
			int type_case = gp.getTileM().getMapTileNum()[(Position_x/48)+1][Position_y/48];
			if(type_case == 1 | type_case == 2 | type_case == 6) {
				return false;
			}
		}
		
		if(direction == "up") {
			int type_case = gp.getTileM().getMapTileNum()[Position_x/48][(Position_y/48)];
			if(type_case == 1 | type_case == 2 | type_case == 6) {
				return false;
			}
		}
		
		if(direction == "down") {
			int type_case = gp.getTileM().getMapTileNum()[Position_x/48][(Position_y/48)+1];
			if(type_case == 1 | type_case == 2 | type_case == 6) {
				return false;
			}
		}
		return true;
	}
	
	public void updateVie(Graphics2D g2) {
		if(nb_vie == 2 && this.gp.getCoeurs().size() == 3) {
			this.gp.getCoeurs().remove(2);
		}
		else if(nb_vie == 1 && this.gp.getCoeurs().size() == 2) {
			this.gp.getCoeurs().remove(1);
		}
		else if(nb_vie == 0 && this.gp.getCoeurs().size() == 1) {
			this.gp.getCoeurs().remove(0);
		}
	}
	
	public void draw(Graphics2D g2) {
		// r�cup�re l'image du joueur
		BufferedImage image = idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
	
	
}
