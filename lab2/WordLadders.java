import java.util.*;
import java.io.*;

public class WordLadders{
	public static void main(String[] args) {
		HashMap<String, LinkedList> words = new HashMap<>();
		ArrayList<Integer> nrStepsList = new ArrayList<>();

		Scanner s1 = new Scanner(System.in);

		while (s1.hasNextLine())
			words.put(s1.nextLine(), new LinkedList<String>());

		String[] wordArray = (String[]) words.keySet().toArray();

		for (int i = 0; i < wordArray.length; i++){
			for (int j = 0; j < wordArray.length; j++) {
				if(isNeighbour(wordArray[i], wordArray[j])){
					words.get(wordArray[i]).add(wordArray[j]);
				}
			}
		}



	}

	public static boolean isNeighbour(String w1, String w2){
		if(w1.equals(w2))
			return false;
		int found = 0;
		String[] wA1 = w1.split("");
		String[] wA2 = w2.split("");
		for(int i = 2; i<6; i++){
			for (int j = 1; j<6; j++) {
				if (wA1[i].equals(wA2[j])){
					wA2[j] = "$";
					found++;
				}
			}
		}
		return found == 4;	
	}

}