package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public String action;
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// r�cup�re le code du boutton appuy�
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_LEFT) {
			action = "left";
		}
		if(code == KeyEvent.VK_RIGHT) {
			action = "right";
		}
		if(code == KeyEvent.VK_UP) {
			action = "up";
		}
		if(code == KeyEvent.VK_DOWN) {
			action = "down";
		}
		if(code == KeyEvent.VK_SPACE) {
			action = "espace";
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		action = "";
	}

}
