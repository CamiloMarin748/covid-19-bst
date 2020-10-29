package covid19bst;

public class Patient {

	public int id;
	public String name;
	public String [] symptoms;
	
	public Patient(int id, String name, String [] symptoms) {
		this.id = id;
		this.name = name;
		this.symptoms = symptoms; 
	}
}
