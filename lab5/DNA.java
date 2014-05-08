import java.util.*;
import java.io.*;
import java.lang.*;

public class DNA{
	public static void main(String[] args) {
		HashMap<String, String> dnaMap = new HashMap<>();
		HashMap<String, Integer> indexMap = new HashMap<>();
		int[][] blosum = new int[24][24];
		String[] protiens = {"A", "R", "N", "D" ,"C", "Q", "E", "G", "H", "I", "L", "K", "M", "F", "P", "S", "T", "W", "Y", "V", "B", "Z", "X", "*"};
		for(int i = 0; i<24; i++){
			indexMap.put(protiens[i], i);
		}


		try{
			Scanner scanner = new Scanner(new File(args[0]));
			Scanner matrixScan = new Scanner(new File("../testfiles/lab5/BLOSUM62.txt"));
			for (int i = 0; i < 7; i++) {
				matrixScan.nextLine();
			}
			String row = "";
			int k = 0;
			while(matrixScan.hasNextLine()){
				row = matrixScan.nextLine();
				String[] matrixSplit = row.split("\\s+");
				for (int i = 0; i < 24; i++) {
					blosum [k][i] = Integer.parseInt(matrixSplit[i+1]);
				}
				k++;
			}

			String species = "";
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				StringBuilder dna = new StringBuilder();
				if(line.contains(">")){
					String[] split = line.split("\\s+"); 
					species = split[0].substring(1);
					dnaMap.put(species, "");
				}
				else{
					dna.append(dnaMap.get(species));
					dna.append(line);
					dnaMap.put(species, dna.toString());
				}
			}
		}

		catch (FileNotFoundException e){
		e.printStackTrace();
		}
	}
}