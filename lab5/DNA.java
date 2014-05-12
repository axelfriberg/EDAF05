import java.util.*;
import java.io.*;
import java.lang.*;

public class DNA{
	private static int[][] blosum;
	private static HashMap<String, Integer> indexMap;

	public static void main(String[] args) {
		HashMap<String, String> dnaMap = new HashMap<>();
		indexMap = new HashMap<>();
		blosum = new int[24][24];
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

			System.out.println(optAlign("ACACACTA","AGCACACA"));

		}

		catch (FileNotFoundException e){
		e.printStackTrace();
		}

	}

	private static String optAlign(String dna1, String dna2){
		int[][] matchMatrix = new int[dna1.length()+2][dna2.length()+2];
		for(int i = 1; i < dna1.length()+1; i++){
			for(int j = 1; j < dna2.length()+1; j++){
				matchMatrix[i][j] = blosum[indexMap.get(Character.toString(dna1.charAt(i-1)))][indexMap.get(Character.toString(dna2.charAt(j-1)))];
			}
		}

		for(int i = 0; i < dna1.length()+1; i++){
			matchMatrix[0][i] = -100;
			matchMatrix[dna1.length()][i] = -100;
		}
		for(int i = 0; i < dna2.length()+1; i++){
			matchMatrix[i][0] = -100;
			matchMatrix[i][dna2.length()] = -100;
		}
		StringBuilder seq1 = new StringBuilder();
		StringBuilder seq2 = new StringBuilder();
		int k = 0;
		int l = 0;
		while (k < dna1.length()-1 && l < dna2.length()-1){
			int nextStep = findMax(matchMatrix[k+1][l], matchMatrix[k][l+1], matchMatrix[k+1][l+1]);
			if(matchMatrix[k+1][l] == nextStep){
				seq1.append("-");
				seq2.append(dna2.charAt(k));
				k++;
			}
			if(matchMatrix[k][l+1] == nextStep){
				seq1.append("-");
				seq2.append(dna2.charAt(l));
				l++;
			}
			if(matchMatrix[k+1][l+1] == nextStep){
				seq1.append(dna1.charAt(k));
				seq2.append(dna2.charAt(l));
				k++;
				l++;
			}
		}

		seq1.append("\n" + seq2.toString());

		for(int i = 0; i < dna1.length()+1; i++){
			for(int j = 0; j < dna2.length()+1; j++){
				System.out.print(matchMatrix[i][j] + "\t");
			}
			System.out.println();
		}

		return seq1.toString();
	}

	private static int findMax(int i, int j, int k){
		return Math.max(i, Math.max(j, k));
	}
}