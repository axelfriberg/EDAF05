import java.util.*;
import java.io.*;
import java.lang.*;

public class ClosestPair{
	public static void main(String[] args){
		//Parser start
		int dimension = 0;
		HashSet<Node> nodes = new HashSet<>();

		try{
			Scanner scanner = new Scanner(new File(args[0]));
			String[] split;
			String line = scanner.nextLine();
			line = line.trim();
			while(!Character.isDigit(line.charAt(0))){
				if(line.contains("DIMENSION")){
					split = line.split(":");
					dimension = Integer.parseInt(split[1].trim());
				}
				line = scanner.nextLine();
				line = line.trim();
				System.out.println(line);
			}
			Node[] xSortedArray = new Node[dimension];
			Node[] ySortedArray = new Node[dimension];

			for(int i = 0; i<dimension; i++){
				split = line.split("\\s+");
				Node node = new Node(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2]));
				xSortedArray[i] = node;
				ySortedArray[i] = node;
				line = scanner.nextLine();
				line = line.trim();
			}
			//Parser end
			Arrays.sort(ySortedArray, new yComparator());
			Arrays.sort(xSortedArray, new xComparator());
			
		    Node[] pl = Arrays.copy
		    Node[] pr = new Node[dimension/2];

		    System.out.println(dac(xSortedArray, ySortedArray));
		}
		catch (FileNotFoundException e){
		e.printStackTrace();
		}
	}

	private static double dac(Node[] px, Node[] py){
		if(px.length <= 3){
			return findClosestBrute(px);
		}
		int mid = px.length/2;
		Node[] qx = new Node[mid];
		Node[] rx = new Node[px.length-mid];
		Node[] qy = new Node[mid];
		Node[] ry = new Node[px.length-mid;
		System.arraycopy(px, 0, qx, 0, mid);
		System.arraycopy(px, mid, rx, 0, px.length-mid);
		System.arraycopy(py, 0, qy, 0, mid);
		System.arraycopy(py, mid, ry, 0, px.length-mid);
		double res1 = dac (qx, qy);
		double res2 = dac (rx, ry); 
		if(res1 < res2){
			return res1;
		}
		return res2;
	}

	private static double findClosestBrute(Node[] list){
		int closest;
		for(int i = 0; i < list.length; i++){
			for(int k = i+1; k < list.length; k++){
				int dist = list[i].getDistanceTo(list[k]);
				if(dist < closest){
					closest = dist;
				}
			}
		}
		return closest;
	}

	private static class Node{
		private double x, y;
		private String name;

		private Node(String name, double x, double y){
			this.x=x; this.y=y; this.name = name;
		}

		private double getDistanceTo(Node n){
			return Math.hypot(x-n.x, y-n.y);		
		}
	}

	private static class xComparator implements Comparator<Node>{
		public xComparator(){

		}

		public int compare(Node n1, Node n2){
			if(n1.x == n2.x){
				return 0;
			} else if(n1.x < n2.x) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	private static class yComparator implements Comparator<Node>{
		public yComparator(){

		}

		public int compare(Node n1, Node n2){
			if(n1.y == n2.y){
				return 0;
			} else if(n1.y < n2.y) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}