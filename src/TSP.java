import java.util.ArrayList;

public class TSP {
	
	private double v_max;
	private int weight_max;
	private double v_min;
	
	public TSP(double v_min, double v_max, int weight_max) {
		this.v_max = v_max;
		this.weight_max = weight_max;
		this.v_min = v_min;
	}
	
	public double getV_min() {
		return v_min;
	}

	public void setV_min(double v_min) {
		this.v_min = v_min;
	}

	public double getV_max() {
		return v_max;
	}

	public void setV_max(double v_max) {
		this.v_max = v_max;
	}

	public int getWeight_max() {
		return weight_max;
	}

	public void setWeight_max(int weight_max) {
		this.weight_max = weight_max;
	}

	public double f_function(ArrayList<Integer> sequence, ArrayList<Miasto> cities) {
		
		double time = 0.0;
		int current_weight = 0;
		
		Miasto from = null;
		Miasto to = null;
		
		for(int i=0; i<sequence.size()-1; i++) {
			for(Miasto m : cities) {
				if(m.getIndex() == sequence.get(i)) { from = m; }
				if(m.getIndex() == sequence.get(i+1)) { to = m; }
			}
			time += travelTimeFromCityToAnotherCity(from, to, current_speed(current_weight));
			
			ArrayList<Przedmiot> items = to.getItems();
			for(Przedmiot item : items) {
				if(item.isPicked()) {
					current_weight += item.getWeight();
				}
			}
		}
		
		time += travelTimeFromCityToAnotherCity(cities.get(cities.size()-2), cities.get(cities.size()-1), current_speed(current_weight));
		
		return time;
	}
	
	public double distanceBetweenTwoCities(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
	}
	
	public double travelTimeFromCityToAnotherCity(Miasto city1, Miasto city2, double speed) {
		return (distanceBetweenTwoCities(city1.getX(), city1.getY(), city2.getX(), city2.getY()))/speed;
	}
	
	public double current_speed(int current_weight) {
		return (v_max - current_weight*((v_max-v_min))/weight_max); 
	}
	
}
