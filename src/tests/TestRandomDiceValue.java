package tests;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import main.DiceValue;

public class TestRandomDiceValue {
	public static void main(String args[]){
		Map<String,Integer> test = new HashMap<String,Integer>();
		
		for(int i = 0; i<1000000; i++){
			DiceValue dice = DiceValue.getRandom();
			if(!test.containsKey(dice.toString())){
				test.put(dice.toString(), 1);
			}else{
				test.put(dice.toString(), test.get(dice.toString())+1);
			}
		}
		
		int total = 0;
		for(int val : test.values()){
			total+=val;
		}
		
		int avg = total/test.size();
		System.out.println("Average rolls: "+avg);
		
		int sdtotal = 0;
		Set<String> keys = test.keySet();
		for(String key : keys){
			Integer val = test.get(key);
			int deviation = Math.abs(val-avg);
			
			double percent = (deviation+0.0)/(avg+0.0);
			System.out.println(key +": has a deviation of "+ Math.floor((percent*100)*1000)/1000 +"%");
			
			sdtotal+=deviation;
		}
		
		double sdeviation = ((sdtotal+0.0)/(test.size()+0.0));
		
		System.out.println("Standard deviation: "+sdeviation);
		System.out.println("Standard deviation percent: "+sdeviation/avg);		
		System.out.println("Whole set: "+test);
		
	}
	
}
