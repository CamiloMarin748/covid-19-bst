package covid19bst;

public class PatientBST {

	PatientNode root;
	
	int totalPayments=0;
	int patientsNumber=0;
	int patientsWithFever=0;
	
	String [] allSymptoms = {"fever", "Tos","Cansancio","Dolores","Dolorgarganta",
			"Diarrea","Conjuntivitis","Dolorcabeza","PerdidaOlfato","Erupcionescutáneas",
			"DificultadRespirar","PresiónPecho","IncapacidadHablar"};
	int [] allSymptomsAdds= new int [12];
	String mostCommonSymptom=null;
		
	
	public void addPatient(Patient newPatient) {
		if(root==null) {
			root = new PatientNode(newPatient);
			int payment = newPatient.symptoms.length * 100;
			totalPayments+=payment;
			patientsNumber++;
			
			
			for(int k=0; k<newPatient.symptoms.length; k++) {
			if (newPatient.symptoms[k].equals("fever")) {
				patientsWithFever++;
			}
			}
			
			
			
			System.out.println("Patient " + newPatient.name + " was added to the system.");
			System.out.println("Payment: " + payment );	
			return;
		}
		addPatient(root, newPatient);
	}// end add patient 
	
	public void addPatient(PatientNode node, Patient newPatient) {
		if(node == null) {
			return;
		}
		
		if(newPatient.id>node.patient.id) {
			if(node.right==null) {
				node.right= new PatientNode(newPatient);
				int payment = newPatient.symptoms.length * 100;
				totalPayments+=payment;
				patientsNumber++;
				
				for (int a=0; a<newPatient.symptoms.length; a++) {
					if (newPatient.symptoms[a].equals("fever")) {
						patientsWithFever++;
					}
				}
				
				
				System.out.println("Patient " + newPatient.name + " was added to the system.");
				System.out.println("Payment: " + payment );
				
			}
			else {//climbing by the right 
				addPatient(node.right, newPatient);
			}
		}
		else {// if newPatient.id<node.patient.id
			if(node.left==null) {
				node.left=new PatientNode(newPatient);
				int payment = newPatient.symptoms.length * 100;
				totalPayments+=payment;
				patientsNumber++;
				
				for (int b=0; b<newPatient.symptoms.length; b++) {
				if (newPatient.symptoms[b].equals("fever")) {
					patientsWithFever++;
				}
				}
				
				System.out.println("Patient " + newPatient.name + " was added to the system.");
				System.out.println("Payment: " + payment );
			}
			else {//Climbing by the left 
				addPatient(root.left, newPatient);
			}
		}
	}// end add Patient 2
	
	void print() {
		print(root);
	}
	
	void print(PatientNode node) {
		
		if(node != null) {
			System.out.println(node.patient.name);
			print(node.left);
			print(node.right);
		}
	}
	
	
	
	public int average() {
		int av = totalPayments/patientsNumber;
		System.out.println("totalPayments: " + totalPayments);
		System.out.println("patientsNumber: " + patientsNumber);
		System.out.println("Average: " + av);
		return av;
	}
	
	public Patient search(int id) {
		return search(root, id);
	}
	
	private Patient search(PatientNode node, int id) {
		if(node == null) {
			return null;
		}
		if(node.patient.id==id) {
			System.out.print("Patient found: " + node.patient.name + " has " + node.patient.symptoms.length + " symptons: " );
			for(int i=0; i<node.patient.symptoms.length; i++) {
			System.out.print(" " + node.patient.symptoms[i]);
			}
			System.out.println();
			return node.patient;
		}
		if (node.patient.id<id) {
			return search(node.right, id);
		}
		return search(node.left, id);
	}
	
	
	
// verification methods by left and right 
	
	public Patient verificationRight(int id) {
		return verificationRight(root, id);
	}
	
	private Patient verificationRight(PatientNode node, int id) {
		if(node == null) {
			return null;
		}
		if(node.patient.id==id) {
			System.out.println("Patient already exist" );
			return node.patient;
		}
		else {
			return verificationRight(node.right, id);
		}
	}

	public Patient verificationLeft(int id) {
		return verificationLeft(root, id);
	}
	
	private Patient verificationLeft(PatientNode node, int id) {
		if(node == null) {
			return null;
		}
		if(node.patient.id==id) {
			System.out.println("Patient already exist" );
			return node.patient;
		}
		else {
			return verificationRight(node.left, id);
		}
	}

	public void hasFever() {
		System.out.println(patientsWithFever + " Patients have fever");
	}
	
	// Symptoms method
	public Patient mostSymptoms(PatientNode node) {
		if(node == null) {
			return null;
		}	
		Patient mostSymptomsPatientleft=null;		
		if(node.left != null) {
			mostSymptomsPatientleft = mostSymptoms(node.left);
		}
		Patient mostSymptomsPatienRight=null;
		if(node.right != null) {
			mostSymptomsPatienRight = mostSymptoms(node.right);
		}
		//comparating 3 variables
		Patient theBiggestOne = node.patient;
		if(mostSymptomsPatientleft != null) {
			if(theBiggestOne.symptoms.length <= mostSymptomsPatientleft.symptoms.length) {
				theBiggestOne = mostSymptomsPatientleft;
			}
		}
		if(mostSymptomsPatienRight != null) {
			if(mostSymptomsPatienRight.symptoms.length >= theBiggestOne.symptoms.length) {
				theBiggestOne = mostSymptomsPatienRight;
			}
		}
		
		return theBiggestOne;
	}
	
	
		
	void allSymptomsAdds(PatientNode node) {
		if(node != null) {
			for (int i=0; i<allSymptomsAdds.length; i++) {
				for(int m=0; m <node.patient.symptoms.length; m++) {
					if(allSymptoms[i].equals(node.patient.symptoms[m])) {
						allSymptomsAdds[i]++;
						}
				}
			allSymptomsAdds(node.left);
			allSymptomsAdds(node.right);
			}
		}
		
	}
	
	
	public void mostCommonSymptom() {
		allSymptomsAdds(this.root);
		
		int max=0;
		for(int z=0; z<allSymptomsAdds.length;z++) {
			int tmp = allSymptomsAdds[z];
			if (tmp>max) {
			max=tmp;
			mostCommonSymptom = allSymptoms[z];
		}
		}
	System.out.println("The most common symptom is : " + mostCommonSymptom);
	}
}//end main 
