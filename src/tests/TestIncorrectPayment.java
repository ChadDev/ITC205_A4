package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.*;

public class TestIncorrectPayment {
	/*
	 * Bug 1: Game does not pay out at correct level.
	 * When player wins on 1 match, balance does not increase.
	 */
	
	Player player;
	Dice d1;
	Dice d2;
	Dice d3;
	Game game;
	List<DiceValue> cdv;
	
	int balance;
	String name;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		balance = 100;
		name = "Fred";
		
        player = new Player(name, balance);
        d1 = new Dice();
        d2 = new Dice();
        d3 = new Dice();
        
        game = new Game(d1, d2, d3);
        cdv = game.getDiceValues();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		int bet = 5;
		
		int winnings = 0;
		
		int lostRounds = 0;
		int wonRounds = 0;
		
		while((lostRounds < 5 || wonRounds < 5) && player.balanceExceedsLimitBy(bet)){
			winnings = game.playRound(player, DiceValue.getRandom(), bet);
			
			if(winnings>0){
				wonRounds++;
			}else{
				lostRounds++;
			}
			
			balance+=(winnings-bet);
		}
		
		assertEquals(balance, player.getBalance());
	}
	
	@Test
	public void testLoop() {
		for(int i = 0; i < 100; i++){
			try{
				balance = 100;
				player = new Player(name, balance);
		        d1 = new Dice();
		        d2 = new Dice();
		        d3 = new Dice();
		        
		        game = new Game(d1, d2, d3);
		        cdv = game.getDiceValues();
				test();
			}catch(Exception e){
				System.out.println(i);
				System.out.println(e);
				break;
			}
			
			
		}
	}

}
