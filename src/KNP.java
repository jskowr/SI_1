import java.util.ArrayList;

public class KNP {
	private int weight_max;
	
	public KNP(int weight_max) {
		this.weight_max = weight_max;
	}
	
	public double g_function(int first, ArrayList<Miasto> cities) {
		
		double current_weight = 0;
		double profit = 0;
		
		ArrayList<Przedmiot> items;
		int best = 0;
		boolean one = false;
		int item_weight = 0;
		int item_profit = 0;
		int item_index = -1;
		
		for(Miasto m : cities) {
			if(m.getIndex() != first) {
				one = true;
				items = m.getItems();
				for(Przedmiot p : items) {
				if(one) {
					if(current_weight+item_weight>weight_max) continue;
					best = p.getValue()/p.getWeight();
					item_weight = p.getWeight();
					item_profit = p.getValue();
					item_index = p.getIndex();
					one = false;
				} else {
					if(p.getValue()/p.getWeight() > best) {
						if(current_weight+item_weight>weight_max) continue;
						best = p.getValue()/p.getWeight();
						item_profit = p.getValue();
						item_weight = p.getWeight();
						item_index = p.getIndex();
					}
				}
				}	
				}
			items = m.getItems();
			for(Przedmiot p : items) {
				if(p.getIndex() == item_index) { p.setPicked(true); }
			}
			
			current_weight+=item_weight;
			profit += item_profit;
		}
		
		return profit;
	}
}
