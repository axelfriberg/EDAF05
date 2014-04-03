import java.util.*;
import java.io.*;

public class WordLadders{
	public static void main(String[] args) {
		HashMap<String, HashSet<String>> words = new HashMap<>();
		String word, bucketKey;
		Scanner s1, s2; 
		try{
			s1 = new Scanner(new File(args[0]));
			s2 = new Scanner(new File(args[1]));
			while (s1.hasNextLine()){
				word = s1.nextLine();
				for(int i = 0; i < word.length(); i++){
					StringBuilder sb = new StringBuilder(word);
					sb.deleteCharAt(i);
					bucketKey = sortString(sb.toString());
					if(!words.containsKey(bucketKey)){
						words.put(bucketKey, new HashSet<String>());
					}
					words.get(bucketKey).add(word);
				}
			}

			while(s2.hasNextLine()){
				String[] pair = s2.nextLine().split(" ");
				int distance = getDistance(words, pair[0], pair[1]);
				System.out.println(distance);
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}

	}

	private static String sortString(String s){
		char[] chars = s.toCharArray();
       	Arrays.sort(chars);
       	String sorted = new String(chars);
  		return sorted;
	}

	private static int getDistance(HashMap<String, HashSet<String>> words, String root, String find){
		LinkedList<String> queue = new LinkedList<>();
		HashSet<String> visited = new HashSet<>();
		int currentNrNodes = 1;
		int nextNrNodes = 0; 
		int distance = 0;
		queue.add(root);
		while(!queue.isEmpty()){
			String temp = queue.poll();
			currentNrNodes--;
			if(temp.equals(find)){
				return distance;
			}
			temp = sortString(temp.substring(1));
			for(String s : words.get(temp)){	
				if(!visited.contains(s)){
					visited.add(s);
					nextNrNodes++;
					queue.add(s);
				}
			}
			if(currentNrNodes == 0){
				distance++;
				currentNrNodes=nextNrNodes;
				nextNrNodes=0;
			}
		}
		return -1;
	}
}