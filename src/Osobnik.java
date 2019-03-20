import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Osobnik {
	
	private List<Integer> sequence;
	
	public List<Integer> getSequence() {
		return sequence;
	}

	public void setSequence(ArrayList<Integer> sequence) {
		this.sequence = sequence;
	}

	public Osobnik(List<Integer> seq) {
		 this.sequence = seq;
	}
	
	public void randomShuffle() {
		Collections.shuffle(this.sequence);
	}
	
	public Osobnik() {
		
	}
	
	public String toString() {
		String res = "[ ";
		
		for(int i=0; i<sequence.size(); i++) {
			res += sequence.get(i) + " , ";
		}
		
		return res + " ]";
	}
}
