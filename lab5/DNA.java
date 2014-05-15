import java.util.*;
import java.io.*;
import java.lang.*;

public class DNA{
	private static int[][] blosum;
	private static HashMap<String, Integer> indexMap;
	private static int matchValue;

	public static void main(String[] args) {
		HashMap<String, String> dnaMap = new HashMap<>();
		ArrayList<String> speciesList = new ArrayList<>();
		indexMap = new HashMap<>();
		blosum = new int[24][24];
		String[] protiens = {"A", "R", "N", "D" ,"C", "Q", "E", "G", "H", "I", "L", "K", "M", "F", "P", "S", "T", "W", "Y", "V", "B", "Z", "X", "*"};
		for(int i = 0; i<24; i++){
			indexMap.put(protiens[i], i);
		}


		try{
			Scanner scanner = new Scanner(new File(args[0]));
			Scanner matrixScan = new Scanner(new File("../testfiles/lab5/BLOSUM62.txt"));
			Scanner outScan = new Scanner(new File("../testfiles/lab5/HbB_FASTAs.out"));
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
					if(split[0].contains("Spider")){
						species = "Spider Monkey";
					}
					else{
						species = split[0].substring(1);
					}	
					dnaMap.put(species, "");
					speciesList.add(species);
				}
				else{
					dna.append(dnaMap.get(species));
					dna.append(line);
					dnaMap.put(species, dna.toString());
				}
			}

			while(outScan.hasNextLine()){
				String line = outScan.nextLine();
				if(line.contains(":")){
					String creatures = line.split(":")[0];
					String creature1 = creatures.split("--")[0];
					String creature2 = creatures.split("--")[1];
					String align = optAlign(dnaMap.get(creature1), dnaMap.get(creature2));
					System.out.println(creature1 + "--" + creature2 +": " + matchValue);
					System.out.println(align);
					matchValue = 0;
				}
			}

			// for(String s : dnaMap.keySet()){
			// 	speciesList.remove(s);
			// 	for(int i = 0; i < speciesList.size(); i++){
			// 		String align = optAlign(dnaMap.get(s), dnaMap.get(speciesList.get(i)));
			// 		System.out.println(s + "--" + speciesList.get(i)+": " + matchValue);
			// 		System.out.println(align);
			// 		matchValue = 0;
			// 	}
			// }
		}

		catch (FileNotFoundException e){
		e.printStackTrace();
		}

	}

	private static String optAlign(String dna1, String dna2){
		int[][] matchMatrix = alignMat(dna1, dna2);
		StringBuilder seq1 = new StringBuilder();
		StringBuilder seq2 = new StringBuilder();
		int i = dna1.length();
		int j = dna2.length();
		while(i > 0 || j > 0){
			if( i > 0 && j > 0 && matchMatrix[i][j] == matchMatrix[i-1][j-1] + blosum[indexMap.get(Character.toString(dna1.charAt(i-1)))][indexMap.get(Character.toString(dna2.charAt(j-1)))]){
				seq1.append(dna1.charAt(i-1));
				seq2.append(dna2.charAt(j-1));
				matchValue += blosum[indexMap.get(Character.toString(dna1.charAt(i-1)))][indexMap.get(Character.toString(dna2.charAt(j-1)))];
				i--;
				j--;
			}
			else if(i > 0 && matchMatrix[i][j] == matchMatrix[i-1][j] - 4){
				seq1.append(dna1.charAt(i-1));
				seq2.append("-");
				matchValue += -4;
				i--;
			}
			else {
				seq2.append(dna2.charAt(j-1));
				seq1.append("-");
				matchValue += -4;
				j--;
			}
		}
		seq1.reverse();
		seq2.reverse();
		return  seq1.toString()+ "\n" + seq2.toString();
	}

	private static int[][] alignMat(String n, String m){
		int[][] matrix = new int[n.length()+1][m.length()+1];
		for(int i = 0; i <= n.length(); i ++){
			matrix[i][0] = -4*i;
		}
		for(int j = 0; j <= m.length(); j ++){
			matrix[0][j] = -4*j;
		}
		for(int i = 1; i <= n.length(); i++){
			for(int j = 1; j <= m.length(); j++){
				int match = matrix[i-1][j-1] + blosum[indexMap.get(Character.toString(n.charAt(i-1)))][indexMap.get(Character.toString(m.charAt(j-1)))];
				int delete = matrix[i-1][j] - 4;
				int insert = matrix[i][j-1] - 4;

				matrix[i][j] = Math.max(Math.max(match, delete), insert);
			}
		}
		return matrix;
	}
}