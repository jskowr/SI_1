import java.util.ArrayList;

public class TTP {
	
	private int weight_max;
	private double v_min;
	private double v_max;
	private ArrayList<Miasto> cities;
	private int first;
	private ArrayList<Integer> sequence;
	
	public TTP(int weight_max, double v_min, double v_max, ArrayList<Miasto> cities, int first, ArrayList<Integer> sequence) {
		this.weight_max = weight_max;
		this.v_min = v_min;
		this.v_max = v_max;
		this.cities = cities;
		this.first = first;
		this.sequence = sequence;
	}
	
	public double evaluate_function_G() {
		
		KNP knp = new KNP(weight_max);
		TSP tsp = new TSP(v_min, v_max, weight_max);
		
		return knp.g_function(first, cities) - tsp.f_function(sequence, cities);
	}
	
}
