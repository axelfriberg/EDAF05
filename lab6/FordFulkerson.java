import java.util.*;
import java.io.*;
import java.lang.*;

public class FordFulkerson{
  	private int[] parent;
	private Queue<Integer> queue;
	private int nov;
	private boolean[] visited;

	public FordFulkerson(int nov){
		this.nov = nov;
		queue = new LinkedList<Integer>();
    	parent = new int[nov];
    	visited = new boolean[nov];	
	}

	private boolean bfs(int s, int t, int[][] residualGraph){
		boolean pathFound = false;
		int destination, currV;

		for(int v = 0; v < nov; v++){
			parent[v] = -1;
			visited[v] = false;
		}
		queue.add(s);
		visited[s] = true;

		while(!queue.isEmpty()){
			currV = queue.remove();
			destination = s;

			while(destination < nov){
				if(residualGraph[currV][destination] > 0 && !visited[destination]){
					parent[destination] = currV;
					queue.add(destination);
					visited[destination] = true;
				}
				destination++;
			}
		}

		if(visited[t]){
			pathFound=true;
		}
		return pathFound;
	}

	public String fordFulkerson(int[][] graph, int s, int destination){
		int u, v;
		int maxFlow = 0;
		int bottleneck;
		int[][] residualGraph = new int[nov][nov];

		for(int sourceV = 0; sourceV < nov; sourceV++){
			for(int destV = 0; destV < nov; destV++){
				residualGraph[sourceV][destV] = graph[sourceV][destV];
			}
		}

		while(bfs(s, destination, residualGraph)){
			bottleneck = Integer.MAX_VALUE;

			for(v = destination; v != s; v = parent[v]){
				u = parent[v];
				bottleneck = Math.min(bottleneck, residualGraph[u][v]);
			}

			for(v = destination; v != s; v = parent[v]){
				u = parent[v];
				residualGraph[u][v] -= bottleneck;
				residualGraph[v][u] += bottleneck; 
			}
			
			maxFlow += bottleneck;
		}

		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < visited.length; i++){
			for(int j = 0; j < visited.length; j++){
				if(visited[i] && !visited[j] && graph[i][j] > 0){
					sb.append(i + " " + j + " " + graph[i][j] + "\n");
				}
			}
		}

		return "Max flow: " + maxFlow + "\nMin Cut:\n" + sb.toString().trim();
	}
}