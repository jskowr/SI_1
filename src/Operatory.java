import java.util.ArrayList;
import java.util.Random;

public class Operatory {

	private TTP ttp;
	
	public Operatory(TTP ttp) {
		this.ttp = ttp;
	}
	
	public ArrayList<Osobnik> selection(ArrayList<Osobnik> osobniki, int tours) {
		
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
			double max = ttp.evaluate_function_G(tour_members.get(0).getSequence());
			Osobnik parent = null;
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
	
	public Osobnik crossover(Osobnik osobnik1, Osobnik osobnik2, int px) {
		Random random = new Random();
		double number = random.nextDouble();
		if(px > number) {
			ArrayList<Integer> sequence1 = osobnik1.getSequence();
			ArrayList<Integer> sequence2 = osobnik2.getSequence();
			int divide_point = random.nextInt(sequence1.size());
			ArrayList<Integer> nowa_sekwencja = new ArrayList<Integer>();
			for(int i=0; i<sequence1.size(); i++) {
				if(i<=divide_point) nowa_sekwencja.add(sequence1.get(i));
				else nowa_sekwencja.add(sequence2.get(i));
			}
			Osobnik potomek = new Osobnik(nowa_sekwencja);
			osobnik1 = null;
			osobnik2 = null;
			return potomek;
		}
		
		return null;
	}
	
}
