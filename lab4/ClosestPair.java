import java.util.*;
import java.io.*;
import java.lang.*;

public class ClosestPair{
	private static yComparator yComp = new yComparator();
	private static xComparator xComp = new xComparator();
	
	public static void main(String[] args){
		//Parser start
		int dimension = 0;
		double time = System.currentTimeMillis();
		HashSet<Node> nodes = new HashSet<>();

		try{
			Scanner scanner = new Scanner(new File(args[0]));
			String[] split;
			String line = scanner.nextLine();
			line = line.trim();
			System.out.print(args[0]+ ":\t");
			boolean flag = true;
			while(flag){
				if(line.contains("DIMENSION")){
					split = line.split(":");
					dimension = Integer.parseInt(split[1].trim());
				}
				if(line.contains("NODE_COORD_SECTION")){
					flag = false;
				}
				line = scanner.nextLine();
				line = line.trim();
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
			Arrays.sort(ySortedArray, yComp);
			Arrays.sort(xSortedArray, xComp);

			time = System.currentTimeMillis()-time;

		    System.out.print("Time elapsed: " + time/1000 + "s" + "\t"+dimension + "\t" + dac(xSortedArray, ySortedArray)+"\n");
		}
		catch (FileNotFoundException e){
		e.printStackTrace();
		}
	}

	private static double dac(Node[] px, Node[] py){
		double minDist = Double.MAX_VALUE; 

		if(px.length <= 3){
			return findClosestBrute(px);
		}
		int mid = px.length/2;
		Node[] lx = new Node[mid];
		Node[] rx = new Node[px.length-mid];
		Node[] ly = new Node[mid];
		Node[] ry = new Node[px.length-mid];
		System.arraycopy(px, 0, lx, 0, mid);
		System.arraycopy(px, mid, rx, 0, px.length-mid);
		System.arraycopy(px, 0, ly, 0, mid);
		System.arraycopy(px, mid, ry, 0, px.length-mid);
		Arrays.sort(ly, yComp);
		Arrays.sort(ry, yComp);
		double res1 = dac (lx, ly);
		double res2 = dac (rx, ry);
		if(res1 < res2){
			res1 = minDist;
		}
		else{
			res2 = minDist;
		}
		double midLine = lx[mid-1].x;
		ArrayList<Node> dList = new ArrayList<>();

		for(int i = 0; i < px.length ; i ++){
			if(Math.abs(px[i].x-midLine)<minDist){
				dList.add(px[i]); 
			}
		}
		Node[] dArray = dList.toArray(new Node[dList.size()]);
		Arrays.sort(dArray, yComp);
		double newDist = Double.MAX_VALUE;
		int index = 15;
		if(dArray.length<15){
			index = dArray.length;
		}
		for(int i = 0; i < dArray.length; i ++){
			ArrayList<Node> tempList = new ArrayList<>();
			for(int k = 1; k < 15; k++){
				if(i+k<dArray.length){
					tempList.add(dArray[i+k]);
				}
			}

			for (Node n : tempList) {
				newDist = dArray[i].getDistanceTo(n);			
				if(newDist<minDist){
					minDist = newDist;
				}	
			}
		}

		double minTemp; 
		for(int i = 0; i < dArray.length; i++){
			for(int k = i+1; k < index; k++){
				if(dArray.length - k < 15){

				}	
			}
		}

		return minDist;
	}

	private static double findClosestBrute(Node[] list){
		double closest = Double.MAX_VALUE;
		for(int i = 0; i < list.length; i++){
			for(int k = i+1; k < list.length; k++){
				double dist = list[i].getDistanceTo(list[k]);
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