import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Operatory {

	private TTP ttp;
	
	public Operatory(TTP ttp) {
		this.ttp = ttp;
	}
	
	public ArrayList<Osobnik> selection_tournament(ArrayList<Osobnik> osobniki, int tours) {
		
		Random random = new Random();
		
		ArrayList<Osobnik> parents = new ArrayList<Osobnik>();
		ArrayList<Osobnik> tour_members = new ArrayList<Osobnik>();
		int itemsForOneTour = osobniki.size()/tours;
		int size = osobniki.size();
		
		for(int i=0; i<tours; i++) {
			if(i!=tours-1) {
			for(int j=0; j<itemsForOneTour; j++) {
			int osobnik_index = random.nextInt(osobniki.size());
			tour_members.add(osobniki.remove(osobnik_index));
			}	
			}else {
				int c = osobniki.size();
				for(int j=0; j<c; j++) {
					int osobnik_index = random.nextInt(osobniki.size());
					tour_members.add(osobniki.remove(osobnik_index));
				}
			}
			
			if(!tour_members.isEmpty()) {
				Osobnik parent = null;
			double max = ttp.evaluate_function_G(tour_members.get(0).getSequence());
			parent = tour_members.get(0);
			for(Osobnik o: tour_members) {
				double current = ttp.evaluate_function_G(o.getSequence());
				if(current > max) { 
					max = current;
					parent = o;
				}
			}
			parents.add(parent);
			tour_members.clear();
			}
			}
		
		return parents;
	}
	
	
	
public ArrayList<Osobnik> selection_roulete(ArrayList<Osobnik> osobniki) {
		
		Random random = new Random();
		
		ArrayList<Osobnik> parents = new ArrayList<Osobnik>();
		int size = osobniki.size();
		
		long total = 0;
		
		for(int i=0; i<size; i++) {
			total += ttp.evaluate_function_G(osobniki.get(i).getSequence());
		}
		
		long current = 0;
		
		for(int i=0; i<size; i++) {
			long rand = random.nextLong();
			for(int j=0; j<size; j++) {
			current += ttp.evaluate_function_G(osobniki.get(i).getSequence());
			if(rand < current) { parents.add(osobniki.get(i)); break; }
			}
		}
		
		return parents;
	}
	
	
	
	public void mutation(Osobnik osobnik, double pm) {
		Random random = new Random();
		double number = random.nextDouble();
		if(pm > number) {
			ArrayList<Integer> sequence = osobnik.getSequence();
		int first = random.nextInt(sequence.size());
		int second = -1;
		while(true) {
			second = random.nextInt(sequence.size());
			if(second != first) break;
		}
		int temp = sequence.get(first);
		sequence.set(first, sequence.get(second));
		sequence.set(second, temp);
		}
		}
	
	public Osobnik crossover(Osobnik osobnik1, Osobnik osobnik2, double px) {
		Random random = new Random();
		double number = random.nextDouble();
		ArrayList<Integer> cities = osobnik1.getSequence();
		if(px > number) {
			ArrayList<Integer> sequence1 = osobnik1.getSequence();
			ArrayList<Integer> sequence2 = osobnik2.getSequence();
			int divide_point = random.nextInt(sequence1.size());
			ArrayList<Integer> nowa_sekwencja = new ArrayList<Integer>();
			for(int i=0; i<sequence1.size(); i++) {
				if(i<=divide_point) nowa_sekwencja.add(sequence1.get(i));
				else nowa_sekwencja.add(sequence2.get(i));
			}
			
			ArrayList<Integer> indexes = new ArrayList<Integer>();
			ArrayList<Integer> duplicated = new ArrayList<Integer>();
			
			for(int i=0; i<nowa_sekwencja.size(); i++) {
				if(!duplicated.contains(nowa_sekwencja.get(i))) {
				int freq = Collections.frequency(nowa_sekwencja, nowa_sekwencja.get(i));
				duplicated.add(nowa_sekwencja.get(i));
				if(freq > 1) {
					indexes.add(i);
				}
				}
			}
			
			for(int i=0; i<cities.size(); i++) {
				if(!nowa_sekwencja.contains(cities.get(i))) {
					int index = indexes.get(indexes.size()-1);
					nowa_sekwencja.set(index, cities.get(i));
					indexes.remove(indexes.size()-1);
				}
			}
			
			Osobnik potomek = new Osobnik(nowa_sekwencja);
			osobnik1 = null;
			osobnik2 = null;
			return potomek;
		}
		
		return null;
	}
	
}
