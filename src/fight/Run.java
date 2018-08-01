package fight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Run {
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		boolean exit = false;
		String choice;
		int faces;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Double> bonus = new ArrayList<Double>();
		ArrayList<Double> malus = new ArrayList<Double>();
		
		
		
		do {
			
			bonus.clear();
			malus.clear();
			
			do {

				System.out.println("\n\n1 - Lancer un nouveau combat\n"
				 		         + "2 - Quitter");
				choice = br.readLine();
				
			} while(!choice.matches("\\d{1}") || choice.isEmpty());
			
			
			if (choice.charAt(0) == '2') {
				System.out.println("Voulez-vous vraiment quitter ? y/n");
				
				if (br.readLine().toUpperCase().charAt(0) == 'Y') {
					System.out.println("Programme terminé");
					exit = true;
				}
			}
			
			else {
				System.out.println("Entrez tout les bonus séparés par un espace : ");
				bonus = getNumbers(br);
				System.out.println("Entrez tout les malus séparés par un espace : ");
				malus = getNumbers(br);
				
				System.out.println("Entrez le nombre de faces du dé : ");
				faces = Integer.valueOf(br.readLine());
				
				System.out.println("Résultat : " + compute(faces, bonus, malus));
			}
		
		} while(!exit); 
	}
	
	public static ArrayList<Double> getNumbers(BufferedReader br) throws IOException {
		ArrayList<Double> res = new ArrayList<Double>();
		String[] tmp  = br.readLine().split(" ");
		
		for(int i = 0; i<tmp.length; i++) {
			if(tmp[i].matches("\\d")) {
				res.add(Double.valueOf(tmp[i]));
			}
		}
		return res;
	}
	
	public static long compute(int faces, ArrayList<Double> bonus, ArrayList<Double> malus) {
		long res = Math.round(Math.random()*faces % faces);
		double bonusMultiplier = 1;
		double malusMultiplier = 1;
		
		for(double a : bonus) {
			bonusMultiplier*= 1 + (a/100);
		}
		
		for(double a : malus) {
			malusMultiplier*=  1 - (a/100);
		}
		
		return Math.round(res*bonusMultiplier*malusMultiplier);
	}
}
