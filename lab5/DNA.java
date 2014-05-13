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

			//PARSER START
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
			//PARSER END

			Collection<String> keySet1 = dnaMap.keySet();
			Collection<String> keySet2 = new HashSet<>();
			keySet2.addAll(keySet1);

			for (String s1 : keySet1) {

				for (String s2 : keySet2) {
					if(!s1.equals(s2)){
					System.out.println(s1 + " -- " + s2);
					System.out.println(Alignment(dnaMap.get(s1),dnaMap.get(s2)));
					}					
				}
				keySet2.remove(s1);
			}
		}		

		catch (FileNotFoundException e){
		e.printStackTrace();
		}		
	}

	//Should not return int
	private static int Alignment(String s1, String s2){
		int[][] graph = new int[s1.length()+1][s2.length()+1];
		graph[0][0] = 0;

		for (int i = 1; i < graph[0].length; i++) {
			graph[0][i] = graph[0][i-1] - 4;
		}
		
		for (int i = 1; i < graph.length; i++) {
			graph[i][0] = graph[i-1][0] - 4;
		}	

		int optValue = 0;

		for (int i = 1; i < graph.length; i++) {
			for (int j = 1; j < graph[0].length; j++) {
				int index1 = indexMap.get(Character.toString(s1.charAt(i-1)));
				int index2 = indexMap.get(Character.toString(s2.charAt(j-1)));
				graph[i][j] = Math.max(blosum[index1][index2] + graph[i-1][j-1], Math.max(-4 + graph[i-1][j], -4 + graph[i][j-1])); 
			}				
		}

		return graph[s1.length()][s2.length()];
	}	
}