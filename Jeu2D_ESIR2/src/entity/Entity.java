package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
	public int x,y;
	public int speed;
	public BufferedImage idleImage;
	
	public abstract void draw(Graphics2D g3);
}
