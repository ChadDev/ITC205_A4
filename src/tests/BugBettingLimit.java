package tests;

import java.util.List;

import main.Dice;
import main.DiceValue;
import main.Game;
import main.Player;

public class BugBettingLimit {
	public static void main(String[] args){
        Dice d1 = new Dice();
        Dice d2 = new Dice();
        Dice d3 = new Dice();

        Player player = new Player("Fred", 30);
        player.setLimit(0);
        Game game = new Game(d1, d2, d3);
        List<DiceValue> cdv = game.getDiceValues();
        
        int bet = 5;
        
        System.out.println(String.format("%s starts with balance %d, limit %d", 
        		player.getName(), player.getBalance(), player.getLimit()));
        
        while(player.getBalance() > 0){
        	if(player.getBalance()<5){ //make sure to bet every single dollar until reach limit of 0
        		bet = player.getBalance();
        	}
        	
        	DiceValue pick = DiceValue.getRandom();
        	System.out.println("-------------------------------------");
        	System.out.printf("%s bet %d on %s\n",player.getName(), bet, pick); 
        	
        	int winnings = game.playRound(player, pick, bet);
        	
        	System.out.printf("%s won %d, balance now %d\n\n",
            		player.getName(), winnings, player.getBalance());
        }
	}
}
