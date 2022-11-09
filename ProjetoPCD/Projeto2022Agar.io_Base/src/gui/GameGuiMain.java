package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import game.BotPlayer;
import game.Game;
import game.HumanPlayer;
import game.Player;

import javax.swing.JFrame;

public class GameGuiMain implements Observer {
	private JFrame frame = new JFrame("pcd.io");
	private BoardJComponent boardGui;
	private Game game;
	

	public GameGuiMain() {
		super();
		game = new Game();
		game.addObserver(this);

		buildGui();

	}

	private void buildGui() {
		boardGui = new BoardJComponent(game);
		frame.add(boardGui);


		frame.setSize(800,800);
		frame.setLocation(0, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init()  {
		frame.setVisible(true);

		// Demo players, should be deleted
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//teste com 1 jogador humano
		HumanPlayer humanPlayer=new HumanPlayer(0, game, (byte)3,1);
		game.addPlayerToGame(humanPlayer);
		humanPlayer.start();
		
		
		//meter os bots a andar
		for(int i=1;i<game.maxPlayers();i++) {
			Byte strength=null;
			int random=(int) Math.round(Math.random()*2);
			switch(random) {
			case 0:
				strength=(byte)1;
				break;
			case 1:
				strength=(byte)2;
				break;
			case 2:
				strength=(byte)3;
				break;
			}
			BotPlayer botPlayer=new BotPlayer(i, game, strength,random+1);
			game.addPlayerToGame(botPlayer);
			
			
			botPlayer.start();
		}
		//fica a dar scan às teclas do jogador humano
		while(true) {
			
			KeyEvent e = new KeyEvent(boardGui, 1, 20, 1, 10, 'a');
			boardGui.keyPressed(e);
			//se houve uma tecla clicada,o humano anda
			if(boardGui.getLastPressedDirection()!=null) {
				humanPlayer.setNextDirection(boardGui.getLastPressedDirection());
				
				boardGui.clearLastPressedDirection();
			}
			
			
			

		}
		
			
	}
	

	@Override
	public void update(Observable o, Object arg) {
		boardGui.repaint();
	}

	public static void main(String[] args) {
		GameGuiMain game = new GameGuiMain();
		game.init();
	}

}
