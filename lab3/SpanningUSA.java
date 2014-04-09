import java.util.*;
import java.io.*;
import java.lang.*;

public class SpanningUSA{
	public static void main(String[] args) {
		Scanner scanner;
		ArrayList<HashSet<String>> cities = new ArrayList<>();
		PriorityQueue<Road> roads = new PriorityQueue();
		HashSet<Road> mst = new HashSet<>();
		
		try{
			scanner = new Scanner(new File(args[0]));
			String city;
			String endcity;
			String distance;
			int n = 0;
				
			while (n<128){
				city = scanner.nextLine().trim();
				HashSet<String> set = new HashSet<>();
				set.add(city);
				cities.add(set);
				n++;
			}

			while(scanner.hasNextLine()){
				String[] split = scanner.nextLine().split("--");
				city = split[0];				
				distance = split[1].substring(split[1].indexOf("[") + 1, split[1].length()-1);
				endcity = split[1].substring(0,split[1].indexOf("[")-1);
				roads.add(new Road(city, endcity, Integer.parseInt(distance)));
			}

		while(!roads.isEmpty()){
			Road temp = roads.poll();
			String start = temp.getStart();
			String end = temp.getEnd();
			int startIndex = -1;
			int endIndex = -1;

			for (HashSet<String> s : cities) {

				if(s.contains(start)){
					startIndex = cities.indexOf(s);
				}
				if(s.contains(end)){
					endIndex = cities.indexOf(s);
				}

			}

			if(startIndex != endIndex && startIndex != -1){
				mst.add(temp);
				if(cities.get(endIndex).size() <= cities.get(startIndex).size()){
					cities.get(startIndex).addAll(cities.get(endIndex));
					cities.remove(endIndex);
				}
				else{
					cities.get(endIndex).addAll(cities.get(startIndex));
					cities.remove(startIndex);
				}

			}
		}
	}
	catch (FileNotFoundException e){
		e.printStackTrace();
	}

	int sum = 0;

	for(Road e : mst){
		sum += e.getDistance();
	}
		System.out.println(sum);
	}
}