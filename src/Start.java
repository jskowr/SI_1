import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

public class Start {

	public static void main(String[] args) {
		
		Random random = new Random();
		
		Loader loader = new Loader();
		String file_name = loader.choose();
		File file = new File("./results/generations_"+file_name+".txt");
		
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
		double eval_count = 0;
		double best_eval = 0;
		double worst_eval = 0;
		boolean f;
		
		PrintWriter writer = null;
		try {
			if(writer == null) writer = new PrintWriter(file, "UTF-8");
			writer.println("generation;best;worst;avarage");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		while(generation <= gen) {
			eval_count = 0;
			f = true;
			ArrayList<Osobnik> osobniki_eval = populacja.getOsobniki();
			for(Osobnik o : osobniki_eval) {
				double current_eval = ttp.evaluate_function_G(o.getSequence());
				eval_count += current_eval;
				if(f) {
					best_eval = current_eval;
					worst_eval = current_eval;
					f = false;
				} else {
					if(current_eval < worst_eval) worst_eval = current_eval;
					if(current_eval > best_eval) best_eval = current_eval;
				}
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
			
			try {
				if(writer == null) writer = new PrintWriter(file, "UTF-8");
				writer.println(generation+";"+Math.round(best_eval)+";"+Math.round(worst_eval)+";"+Math.round(eval_count/pop_size));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			populacja.setOsobniki(nowe_osobniki);
			osobniki = populacja.getOsobniki();
			generation++;
		}
		writer.close();
		System.out.println("najlepszy osobnik: ");
		System.out.println(best);

	}

}
