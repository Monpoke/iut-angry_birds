package angrybirds;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public class Frame extends JFrame implements ComponentListener {
	
	/**
	 * Taille de la fenetre
	 */
	Dimension dimension = new Dimension(800, 450);
	
	/**
	 * crée une fenetre pour afficher le jeu
	 */
	public Frame() {
		this.setTitle("Angry Birds");
		this.setSize(new Dimension(800, 450));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.addComponentListener(this);
	}
	
	/**
	 * renvoie la taille actuelle de la fenetre
	 * @return dimension
	 */
	public Dimension getDimension() {
		return dimension;
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * actualise la taille de la fenetre lors des modifications
	 */
	@Override
	public void componentResized(ComponentEvent e) {
		Component c = e.getComponent();
		dimension = c.getSize();
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		new Frame().setVisible(true);
	}

}
