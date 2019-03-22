import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//indeksy od 3 do 8
public class Loader {
	
	private int dimension;
	private int items_count;
	private int knapsack_capacity;
	private double min_speed;
	private double max_speed;
	private double renting_ratio;
	private ArrayList<Miasto> cities;
	private ArrayList<Przedmiot> items;
	
	public Loader() {
		cities = new ArrayList<Miasto>();
		items = new ArrayList<Przedmiot>();
	}
	
	public ArrayList<Miasto> getCities() {
		return cities;
	}

	public void setCities(ArrayList<Miasto> cities) {
		this.cities = cities;
	}

	public ArrayList<Przedmiot> getItems() {
		return items;
	}

	public void setItems(ArrayList<Przedmiot> items) {
		this.items = items;
	}
	
	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public int getItems_count() {
		return items_count;
	}

	public void setItems_count(int items_count) {
		this.items_count = items_count;
	}

	public int getKnapsack_capacity() {
		return knapsack_capacity;
	}

	public void setKnapsack_capacity(int knapsack_capacity) {
		this.knapsack_capacity = knapsack_capacity;
	}

	public double getMin_speed() {
		return min_speed;
	}

	public void setMin_speed(double min_speed) {
		this.min_speed = min_speed;
	}

	public double getMax_speed() {
		return max_speed;
	}

	public void setMax_speed(double max_speed) {
		this.max_speed = max_speed;
	}

	public double getRenting_ratio() {
		return renting_ratio;
	}

	public void setRenting_ratio(double renting_ratio) {
		this.renting_ratio = renting_ratio;
	}
	
	public String choose() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Wybierz plik (trivial, easy, medium, hard) odpowiednio numerowany (0, 1, 2, 3, 4) np. trivial_0: ");
		String f = scan.nextLine();
		String file = "./dane/"+f+".txt";
		
		BufferedReader reader;
		try {
			int count = 1;
			reader = new BufferedReader(new FileReader(
					file));
			String line = reader.readLine();
			while (line != null) {
				//parametry
				if(count>=3 && count <=8) {
					line = line.replaceAll("[^0-9?!\\.]","");
					if(count == 3) { dimension = Integer.parseInt(line); }
					if(count == 4) { items_count = Integer.parseInt(line); }
					if(count == 5) { knapsack_capacity = Integer.parseInt(line); } 
					if(count == 6) { min_speed = Double.parseDouble(line); }
					if(count == 7) { max_speed = Double.parseDouble(line); }
					if(count == 8) { renting_ratio = Double.parseDouble(line); }
				}
				
				//miasta
				if(count>=11 && count < 11+dimension) {
					String[] values = line.split("\\s+");
					int city_index = Integer.parseInt(values[0]);
					double city_x = Double.parseDouble(values[1]);
					double city_y = Double.parseDouble(values[2]);
					cities.add(new Miasto(city_index, city_x, city_y));
				}
				
				if(count>=12+dimension && count<12+dimension+items_count) {
					String[] values = line.split("\\s+");
					int item_index = Integer.parseInt(values[0]);
					int item_profit = Integer.parseInt(values[1]);
					int item_weight = Integer.parseInt(values[2]);
					int city = Integer.parseInt(values[3]);
					items.add(new Przedmiot(item_index, item_profit, item_weight, city));
				}
				count++; 
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}
}
