import java.util.*;
import java.io.*;
import java.lang.*;

public class DNA{
	public static void main(String[] args) {
		HashMap<String, String> dnaMap = new HashMap<>();

		try{
			Scanner scanner = new Scanner(new File(args[0]));
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				String species = "";
				if(line.contains(">")){
					String[] split = line.split("\\s+"); 
					species = split[0].substring(1);
				}
				
				StringBuilder dna = new StringBuilder();

				while(!line.contains(">")){
					dna.append(line);
					line = scanner.nextLine();
				}
				dnaMap.put(species, dna.toString());
			}

			for (String s : dnaMap.keySet()) {
				System.out.println(s + " " + dnaMap.get(s));
			}
		}
		catch (FileNotFoundException e){
		e.printStackTrace();
		}
	}
}