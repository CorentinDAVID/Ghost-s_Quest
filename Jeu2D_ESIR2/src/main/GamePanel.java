package main;

import java.awt.Dimension;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import entity.Player;
import entity.arbre;
import entity.coffre;
import entity.key;
import entity.lac;
import entity.Garde1;
import entity.Arrow;
import entity.Feu;
import entity.Coeur;
import entity.Entity;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable{
	//Param�tres de l'�cran
	final int originalTileSize = 16; // une tuile de taille 16x16
	final int scale = 3; // �chelle utilis�e pour agrandir l'affichage
	public final int tileSize = originalTileSize * scale; // 48x48
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12; // ces valeurs donnent une r�solution 4:3
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels

	// FPS : taux de rafraichissement
	int FPS = 60;
	// Cr�ation des diff�rentes instances (Player, KeyHandler, TileManager, GameThread ...)
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	Garde1 G1 = new Garde1(this,keyH,145,350);
	Garde1 G2 = new Garde1(this,keyH,575,450);
	coffre c= new coffre(this,keyH,700,200);
	arbre a2= new arbre(this,keyH,50,50);
    arbre a3= new arbre(this,keyH,100,460);
    arbre a4= new arbre(this,keyH,100,50);
    arbre a5= new arbre(this,keyH,190,140);
    arbre a6= new arbre(this,keyH,390,440);
    arbre a7= new arbre(this,keyH,290,440);
    arbre a8= new arbre(this,keyH,190,240);
    arbre a9= new arbre(this,keyH,340,480);
    arbre a10= new arbre(this,keyH,240,480);
    arbre a11= new arbre(this,keyH,50,460);
    arbre a12= new arbre(this,keyH,350,50);
    lac l=new lac(this,keyH,295,150);
	Coeur heart = new Coeur(this,keyH,0,530);
	Coeur heart1 = new Coeur(this,keyH, 50,530);
	Coeur heart2 = new Coeur(this,keyH, 100,530);
	key k = new key(this,keyH,100,150);
	TileManager tileM = new TileManager(this,"mapmonde");
	List<Entity> entity_static = new ArrayList<>();
	List<Entity> coeurs = new ArrayList<>();
	List<Arrow> arrows = new ArrayList<Arrow>();
	List<Feu> fire = new ArrayList<Feu>();

	int nn = 0;

	BufferedImage game_over;
	BufferedImage you_win;

	int numero_map;

	// Constructeur de la classe
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		numero_map = 0;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public TileManager getTileM() {
		return tileM;
	}

	public List<Entity> getCoeurs(){
		return coeurs;
	}
	
	public List<Arrow> getArrows(){
		return arrows;
	}
	
	public List<Feu> getFeu(){
		return fire;
	}

	public void setListEntity() {
		entity_static.add(G1);
		entity_static.add(G2);
		entity_static.add(c);
		entity_static.add(a2);
		entity_static.add(a3);
		entity_static.add(a4);
		entity_static.add(a5);
		entity_static.add(a6);
		entity_static.add(a7);
		entity_static.add(a8);
		entity_static.add(a9);
		entity_static.add(a10);
		entity_static.add(a11);
		entity_static.add(a12);
		entity_static.add(l);

		coeurs.add(heart);
		coeurs.add(heart1);
		coeurs.add(heart2);
	}

	public void updateFlechesFeux(Graphics2D g2) {
		for(int i=0;i<arrows.size();i++){
			arrows.get(i).draw(g2);
		}
		for(int i=0;i<arrows.size();i++){
			fire.get(i).draw(g2);
		}
	}

	public void updateEntityStatic(Graphics2D g2) {
		for(int i=0;i<entity_static.size();i++) {
			entity_static.get(i).draw(g2);
		}
	}

	public void updateCoeurs(Graphics2D g2) {
		for(int i=0;i<coeurs.size();i++) {
			coeurs.get(i).draw(g2);
		}
	}

	public void setArrowsFeux() {

		Random random = new Random();
		Random random2 = new Random();
		int value = random.nextInt(645)+40;
		int value2 = random2.nextInt(400)+50;
		arrows.add(new Arrow(this,value));
		fire.add(new Feu(this,value2));


	}

	public void run() {

		double drawInterval = 1000000000/FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval; 

		setListEntity();

		while(gameThread != null) { //Tant que le thread du jeu est actif


			//Permet de mettre � jour les diff�rentes variables du jeu
			try {
				update();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//Dessine sur l'�cran le personnage et la map avec les nouvelles informations. la m�thode "paintComponent" doit obligatoirement �tre appel�e avec "repaint()"
			repaint();
			//Calcule le temps de pause du thread


			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;

				if(remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(player.dansLave()) {
				gameThread = null;
			}
			
			if(player.getNb_vie() == 0) {
				gameThread = null;
			}
		}
	}



	public void update() throws IOException {

		player.update();
		if(120<player.getX() && player.getX()<170 && 325<player.getY() && player.getY()<370 && numero_map == 0) {
			numero_map = 1;
		}

		if(player.x>576 && player.x<624 && player.y>432 && player.y<528 && numero_map == 0) {
			numero_map = 2;
		}

		if(numero_map == 2) {

			if(nn%30 == 0) {
				setArrowsFeux();
			}
			for(int i=0;i<arrows.size();i++){
				arrows.get(i).update();
			}
			for(int i=0;i<arrows.size();i++){
				fire.get(i).update();
			}
			player.toucher();
		}
		nn++;
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(numero_map == 1) {
			if(tileM.getNomMonde()=="arene1") {
				int[][] save = tileM.getMapTileNum();
				tileM = new TileManager(this,"arene1");
				tileM.setMapTileNum(save);
			}
			else {
				player.x = 24;
				player.y = 264;
				tileM = new TileManager(this,"arene1");
			}
			tileM.draw(g2);
			player.draw(g2);

			if(player.getNbPieces()==7) {
				numero_map = 0;
				player.x = 171;
				player.y = 371;
				tileM = new TileManager(this,"mapmonde");
			}
		}
		else if(numero_map == 2) {
			tileM = new TileManager(this,"arene2");
			tileM.draw(g2);
			player.draw(g2);
			k.draw(g2);
			updateFlechesFeux(g2);
			if(player.x > 76 && player.x < 124 && player.y > 126 && player.y < 174) {
				numero_map = 0;
				player.x = 650;
				player.y = 450;
				tileM = new TileManager(this,"mapmonde");
			}
		}
		else {
			tileM.draw(g2);
			player.draw(g2);
			updateEntityStatic(g2);
			if(player.x>624 && player.x<720 && player.y>48 && player.y<62) {
				try {
					you_win = ImageIO.read(getClass().getResource("/player/YOUWIN.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g2.drawImage(you_win, 100, 75, 568, 426, null);

				gameThread = null;
			}
		}

		updateCoeurs(g2);
		player.updateVie(g2);

		if(player.dansLave()) {
			gameOver(g2);
		}
		
		if(player.getNb_vie() == 0) {
			gameOver(g2);
		}
		g2.dispose();
	}

	public void gameOver(Graphics2D g2) {
		try {
			game_over = ImageIO.read(getClass().getResource("/player/GAMEOVER.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g2.drawImage(game_over, 100, 75, 568, 426, null);
	}


}



