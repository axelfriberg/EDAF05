import java.util.*;
import java.io.*;

public class SpanningUSA{
	public static void main(String[] args) {
		Scanner scanner; 
		
		HashMap<String, HashMap<String, Integer>> cities = new HashMap<>();
		
		try{
			scanner = new Scanner(new File(args[0]));
			String city;
			String destination;
			String distance;
			
			while (scanner.hasNextLine()){
				String[] line = scanner.nextLine().split("--");
				String[] temp;
				if(line[0].charAt(0)== '"'){
					temp = line[0].split("\"");
					city = temp[1];
				}
				else{
					city = line[0];
				}

				if(line.length == 1){
					cities.put(city, new HashMap<String, Integer>());
				}
				else{
					String[] destdist = line[1].split(" ");
					if(destdist[0].charAt(0)=='"'){
						temp = destdist[0].split("\"");
						destination = temp[1];
					}
					else{
						destination = destdist[0];
					}
					distance = destdist[2].substring(1, destdist[2].length()-2);

					cities.get(city).put(destination, Integer.parseInt(distance));
				}
			}			
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		Set<String> set = cities.keySet();
		for(String s : set){
			System.out.println(s);
		}
	}
}