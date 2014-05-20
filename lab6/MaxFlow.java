import java.util.*;
import java.io.*;
import java.lang.*;
public class MaxFlow{
	private static boolean[] seen;
	private static int[][] capacity;
	private static StringBuilder sb;
	public static void main(String[] args) {
		try{
			Scanner scan = new Scanner(new File(args[0]));

			for (int i = 0; i < 57 ; i++) {
				scan.nextLine();
			}

			capacity = new int[55][55];
			seen = new boolean[55];
			sb = new StringBuilder();

			
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
			}

			minCut(capacity, 0, 54);

		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

	private static boolean findAugPath(int[][] resGraph, int source, int sink, int parent[]){
		for(int i = 0; i < 55; i++){
			seen[i] = false;
		}
		LinkedList<Integer> queue = new LinkedList<>();
		queue.add(source);
		seen[source] = true;
		parent[source] = -1;
		while(!queue.isEmpty()){
			int temp = queue.poll();
			for(int i = 0; i < 55; i++){
				if(!seen[source] && resGraph[temp][i] > 0){
					queue.add(i);
					parent[i] = temp;
					seen[i] = true;
				}
			}
		}
		return seen[sink] == true;
	}

	private static boolean[] dfs(int resGraph[][], int source, boolean[] visited){
		visited[source] = true;
		for(int i = 0; i < 55; i ++){
			if(resGraph[source][i] > 0 && !visited[i]){
				visited = dfs(resGraph, i, visited);
			}
		}
		return visited;
	}

	private static void minCut(int[][] graph, int source, int sink){
		int[][] resGraph = new int[55][55];
		for (int i = 0; i < 55; i++) {
			for (int j = 0; j < 55; j++) {
				resGraph[i][j] = graph[i][j];
			}
			
		}
		int[] parent = new int[55];
		int temp;
		while(findAugPath(resGraph, source, sink, parent)){
			int flow = Integer.MAX_VALUE;
			for(int i = 54; i <=0; i = parent[i]){
				temp = parent[i];
				flow = Math.min(flow, resGraph[temp][i]);
			}
			for(int i = 54; i <=0; i = parent[i]){
				temp = parent[i];
				resGraph[temp][i] -= flow;
				resGraph[i][temp] += flow;
			}
		}
		boolean[] visited = new boolean[55];
		for(int i = 0; i < 55; i++){
			visited[i] = false;
		}
		visited = dfs(resGraph, source, visited);
		for(int i = 0; i < 55; i++){
			for(int j = 0; j < 55; j++){
				System.out.println(visited[j]);
				if(visited[i] && !visited[j] && graph[i][j] > 0){
					System.out.println(i + " " + j);
				}
			}
		}
	}
}



