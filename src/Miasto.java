import java.util.ArrayList;

public class Miasto {

	private int index;
	private double x;
	private double y;
	private ArrayList<Przedmiot> items;
	
	public ArrayList<Przedmiot> getItems() {
		return items;
	}
	public void setItems(ArrayList<Przedmiot> items) {
		this.items = items;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public void addItem(Przedmiot p) {
		items.add(p);
	}
	
	public Miasto(int index, double x, double y, ArrayList<Przedmiot> items) {
		this.index = index;
		this.x = x;
		this.y = y;
		this.items = items;
	}
	
	public Miasto(int index, double x, double y) {
		this.index = index;
		this.x = x;
		this.y = y;
		items = new ArrayList<Przedmiot>();
	}
	
}
