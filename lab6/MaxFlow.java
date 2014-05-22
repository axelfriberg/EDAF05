import java.util.*;
import java.io.*;
import java.lang.*;
public class MaxFlow{
	public static void main(String[] args) {
		try{
			Scanner scan = new Scanner(new File(args[0]));

			for (int i = 0; i < 57 ; i++) {
				scan.nextLine();
			}

			int[][] capacity = new int[55][55];

			
			for (int i = 0; i < 119; i++) {
				String line = scan.nextLine();
				String [] split = line.split("\\s+");
				int n1 = Integer.parseInt(split[0]);
				int n2 = Integer.parseInt(split[1]);
				int c = Integer.parseInt(split[2]);
				if(c == -1){
					c = Integer.MAX_VALUE;
				}
				capacity[n1][n2] = c;
				capacity[n2][n1] = c;
			}
			
			FordFulkerson fordFulkerson = new FordFulkerson(55);
			System.out.println(fordFulkerson.fordFulkerson(capacity, 0, 54));


		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}