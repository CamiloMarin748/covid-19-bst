package covid19bst;

public class PatientNode {

	public PatientNode(Patient newPatient) {
		this.patient = newPatient;
	}
	
	Patient patient;
	
	PatientNode left;
	PatientNode right;
}
