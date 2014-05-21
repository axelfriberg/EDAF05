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

			int[][] capacity = new int[56][56];

			
			for (int i = 1; i <= 119; i++) {
				String line = scan.nextLine();
				String [] split = line.split("\\s+");
				int n1 = Integer.parseInt(split[0]);
				int n2 = Integer.parseInt(split[1]);
				int c = Integer.parseInt(split[2]);
				if(c == -1){
					c = Integer.MAX_VALUE;
				}
				capacity[n1+1][n2+1] = c;
			}
			
			FordFulkerson fordFulkerson = new FordFulkerson(55);
			System.out.println("Max Flow: " + fordFulkerson.fordFulkerson(capacity, 1, 55));


		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}
}