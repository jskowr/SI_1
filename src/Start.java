import java.util.ArrayList;
import java.util.Random;

public class Start {

	public static void main(String[] args) {
		
		Random random = new Random();
		
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
		ArrayList<Osobnik> nowe_osobniki = new ArrayList<Osobnik>();
		
		int generation = 1;
		double eval = 0;
		Osobnik best = null;
		while(generation <= gen) {
			ArrayList<Osobnik> osobniki_eval = populacja.getOsobniki();
			for(Osobnik o : osobniki_eval) {
				double current_eval = ttp.evaluate_function_G(o.getSequence());
				if(current_eval > eval) { eval = current_eval; best = o; } 
			}
			if(!nowe_osobniki.isEmpty()) { populacja.setOsobniki(nowe_osobniki); } 
			nowe_osobniki = new ArrayList<Osobnik>();
			ArrayList<Osobnik> osobniki = populacja.getOsobniki();
			ArrayList<Osobnik> parents = operatory.selection(osobniki, tour);
			
			int liczba_osobnikow = 0;
			while(liczba_osobnikow < pop_size) {
				int first = random.nextInt(parents.size());
				int second = random.nextInt(parents.size());
				while(first==second) {
					second = random.nextInt(parents.size());
				}
				Osobnik potomek = operatory.crossover(parents.get(first), parents.get(second), px);
				if(potomek != null) {
					operatory.mutation(potomek, pm);
					nowe_osobniki.add(potomek);
					liczba_osobnikow++;
				}
			}
			
			populacja.setOsobniki(nowe_osobniki);
			osobniki = populacja.getOsobniki();
			generation++;
		}
		
		System.out.println("najlepszy osobnik: ");
		System.out.println(best);

	}

}
