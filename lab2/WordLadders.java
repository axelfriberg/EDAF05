import java.util.*;
import java.io.*;

public class WordLadders{
	public static void main(String[] args) {
		HashMap<String, LinkedList> words = new HashMap<>();
		ArrayList<Integer> nrStepsList = new ArrayList<>();
		String word, bucketKey;
		StringHandler sh = new StringHandler();

		
		Scanner s1 = new Scanner(System.in);

		while (s1.hasNextLine()){
			word = s1.nextLine();
			for(int i = 0; i < word.length(); i++){
				StringBuilder sb = new StringBuilder(word);
				sb.deleteCharAt(i);
				bucketKey = sh.sortString(sb.toString());
				if(!words.containsKey(bucketKey)){
					words.put(bucketKey, new LinkedList<>());
				}
				if(!words.get(bucketKey).contains(word)){
					words.get(bucketKey).add(word);
				}
			}
		}

		Scanner s2 = new Scanner(System.in);

		while(s2.hasNextLine()){
			System.out.println(s2.nextLine());
			String[] pair = s2.nextLine().split(" ");
			System.out.println(pair.length);
			int distance = sh.getDistance(words, pair[0], pair[1]);
			System.out.println(distance);
		}
	}


	public String sortString(String s){
		char[] chars = s.toCharArray();
       	Arrays.sort(chars);
       	String sorted = new String(chars);
  		return sorted;
	}

	public int getDistance(HashMap<String, LinkedList> words, String root, String find){
		LinkedList<String> queue = new LinkedList<>();
		LinkedList<String> visited = new LinkedList<>(); 
		int distance = 0;
		queue.add(root);
		while(!queue.isEmpty()){
			String temp = queue.remove(0);
			if(temp.equals(find)){
				return distance;
			}
			distance++;
			System.out.println(temp);
			temp = sortString(temp.substring(1));
			System.out.println(temp);
			for(String s : words.get(temp)){
				if(!visited.contains(s)){
					visited.add(s);
					queue.add(s);
				}
			}

		}
		return -1;
	}
}