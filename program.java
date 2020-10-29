package covid19bst;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

public class program {

	public static void main(String[] args) throws NumberFormatException, IOException {
		PatientBST pBST = new PatientBST();	
		
		BufferedReader bf;
		bf = new BufferedReader(new FileReader("PatientsInfo.txt"));
		int PatientsQ = Integer.parseInt(bf.readLine());
		String [] datos;
		String symptoms [];
		for (int i=0; i<PatientsQ; i++) {
			datos = bf.readLine().split(" ");
			symptoms = bf.readLine().split(" ");
				
				Patient patient = new Patient(Integer.parseInt(datos[0]), datos[1], symptoms);
				if (pBST.verificationLeft(patient.id)==null&&pBST.verificationRight(patient.id)==null) {
					pBST.addPatient(patient);
				}
				else {
					System.out.println("Patient " + patient.name + " was NOT added to the system.");
				}
		}
		Patient searchResult = pBST.search(70);
		if(searchResult == null) {
			System.out.println("Patient not found");
		}
		pBST.average();
		pBST.hasFever();		
		System.out.println("The patient with most symptoms is : " + pBST.mostSymptoms(pBST.root).name);
		pBST.mostCommonSymptom();
	}// end main
}// end program
