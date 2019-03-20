import java.util.ArrayList;

public class Start {

	public static void main(String[] args) {
		
		Loader loader = new Loader();
		loader.choose();
		
		//parametry z pliku
		int weight_max = loader.getKnapsack_capacity();
		int dimension = loader.getDimension();
		int items_count = loader.getItems_count();
		double renting_ratio = loader.getRenting_ratio();
		double v_min = loader.getMin_speed();
		double v_max = loader.getMax_speed();
		
		ArrayList<Miasto> cities = loader.getCities();
		ArrayList<Przedmiot> items = loader.getItems();
		
		//przypisanie przedmiotów do odpowiednich miast
		for(Przedmiot p : items) {
			for(Miasto m : cities) {
				if(m.getIndex() == p.getCity()) {
					m.addItem(p);
				}
			}
		}
		
		//pozosta³e parametry
		int pop_size=100; 
		int gen=100;
		double px=0.7; 
		double pm=0.1;
		int tour=5;
		
		Populacja populacja = new Populacja();
		populacja.initialize(pop_size, cities);
		
		TTP ttp = new TTP(weight_max, v_min, v_max, cities);
		Operatory operatory = new Operatory(ttp);
		
		int generation = 1;
		while(generation <= gen) {
			ArrayList<Osobnik> osobniki = populacja.getOsobniki();
			System.out.println("generacja: "+generation);
			ArrayList<Osobnik> parents = operatory.selection(osobniki, tour);
			for(Osobnik o : parents) {
				System.out.println(o);
			}
			generation++;
			System.out.println("\n\n\n");
		}

	}

}
