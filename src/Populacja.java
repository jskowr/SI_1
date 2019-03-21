import java.util.ArrayList;
import java.util.List;

public class Populacja {

	private ArrayList<Osobnik> osobniki;

	public ArrayList<Osobnik> getOsobniki() {
		return osobniki;
	}

	public void setOsobniki(ArrayList<Osobnik> osobniki) {
		this.osobniki = osobniki;
	}
	
	public void dodajOsobnika(Osobnik osobnik) {
		osobniki.add(osobnik);
	}
	
	public Populacja() {
		osobniki = new ArrayList<Osobnik>();
	}
	
	public Populacja(ArrayList<Osobnik> osobniki) {
		this.osobniki = osobniki;
	}
	
	public void initialize(int populationSize, ArrayList<Miasto> cities) {
		for(int i=1; i<=populationSize; i++) {
			ArrayList<Integer> sequence = new ArrayList<Integer>();
			
			for(Miasto m : cities) {
				sequence.add(m.getIndex());
			}
			
			Osobnik temp = new Osobnik(sequence);
			temp.randomShuffle();
			dodajOsobnika(temp);
		}
	}
	
	public void printPoulation() {
		int cnt = 0;
		for(Osobnik o : osobniki) {
			System.out.println(o.toString());
		}
	}
}
