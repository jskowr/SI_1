import java.util.ArrayList;
import java.util.List;

public class Populacja {

	private List<Osobnik> osobniki;

	public List<Osobnik> getOsobniki() {
		return osobniki;
	}

	public void setOsobiniki(List<Osobnik> osobniki) {
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
			List<Integer> sequence = new ArrayList<Integer>();
			
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
