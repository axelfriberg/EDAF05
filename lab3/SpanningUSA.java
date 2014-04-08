import java.util.*;
import java.io.*;

public class SpanningUSA{
	public static void main(String[] args) {
		Scanner scanner;
		HashMap<String, City> cities = new HashMap<>();
		LinkedList<Edge> edges = new LinkedList<>();
		HashSet<Edge> mst = new HashSet<>();
		
		try{
			scanner = new Scanner(new File(args[0]));
			String city;
			String destination;
			String distance;
			int n = 0;
				
			while (n<128){
				city = scanner.nextLine().trim();
				cities.put(city, new City(city));
				n++;
			}

			n=0;			
			while(scanner.hasNextLine()){
				String[] journey = scanner.nextLine().split("--");
				city = journey[0];				
				distance = journey[1].substring(journey[1].indexOf("[") + 1, journey[1].length()-1);
				destination = journey[1].substring(0,journey[1].indexOf("[")-1);
				edges.add(new Edge(cities.get(city), cities.get(destination), Integer.parseInt(distance)));
				n++;
			}

			System.out.println(edges.size());

				
		while(!edges.isEmpty()){
			Edge temp = edges.poll();
			City start = temp.getStart();
			City end = temp.getEnd();
			if(!start.findRoot().equals(end.findRoot())){
				mst.add(temp);
				City root1 = start.findRoot();
				City root2 = end.findRoot();
				if(root1.getHeight()<=root2.getHeight()){
					if(root1.getHeight()==root2.getHeight()){
						root2.incrHeight();
					}
					root1.setDestination(root2);
				}
				else{
					root2.setDestination(root1);
				}

			}
		}
	}

		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		int sum = 0;
		for(Edge e : mst){
			System.out.println(e.getDistance());
			sum += e.getDistance();
		}

		System.out.println(sum);
	}
}