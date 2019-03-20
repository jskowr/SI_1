public class Przedmiot {

	private int index;
	private int value;
	private int weight;
	private int city;
	private boolean isPicked;
	
	public boolean isPicked() {
		return isPicked;
	}
	public void setPicked(boolean isPicked) {
		this.isPicked = isPicked;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	
	public Przedmiot(int index, int value, int weight, int city) {
		this.index = index;
		this.value = value;
		this.weight = weight;
		this.city = city;
		this.isPicked = false;
	}
	
	public Przedmiot() {}
	
}
