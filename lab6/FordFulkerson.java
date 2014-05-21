import java.util.*;
import java.io.*;
import java.lang.*;

public class FordFulkerson{
  	private int[] parent;
	private Queue<Integer> queue;
	private int nov;
	private boolean[] visited;
	private LinkedList<String> minCut;

	public FordFulkerson(int nov){
		this.nov = nov;
		queue = new LinkedList<Integer>();
    	parent = new int[nov + 1];
    	visited = new boolean[nov + 1];
    	minCut = new LinkedList<>();		
	}

	private boolean bfs(int s, int t, int[][] residualGraph, int[][] graph){
		boolean pathFound = false;
		int destination, node;

		for(int v = 1; v <= nov; v++){
			parent[v] = -1;
			visited[v] = false;
		}
		queue.add(s);
		parent[s] = -1;
		visited[s] = true;

		while(!queue.isEmpty()){
			node = queue.remove();
			destination = 1;

			while(destination <= nov){
				if(residualGraph[node][destination]>0 && !visited[destination]){
					parent[destination] = node;
					queue.add(destination);
					if(residualGraph[node][destination] == graph[node][destination] && residualGraph[node][destination] != Integer.MAX_VALUE){
						minCut.add((node-1) + " " + (destination-1) + " " + graph[node][destination]);
					}
					visited[destination] = true;
				}
				destination++;
			}
		}
		if(visited[t]){
			minCut.clear();
			pathFound=true;
		}
		return pathFound;
	}

	public String fordFulkerson(int[][] graph, int s, int destination){
		int u, v;
		int maxFlow = 0;
		int pathFlow;
		int[][] residualGraph = new int[nov+1][nov+1];

		for(int sourceV = 1; sourceV <= nov; sourceV++){
			for(int destV = 1; destV <= nov; destV++){
				residualGraph[sourceV][destV] = graph[sourceV][destV];
			}
		}

		while(bfs(s, destination, residualGraph, graph)){
			pathFlow = Integer.MAX_VALUE;
			for(v = destination; v != s; v = parent[v]){
				u = parent[v];
				pathFlow = Math.min(pathFlow, residualGraph[u][v]);
			}
			for(v = destination; v != s; v = parent[v]){
				u = parent[v];
				residualGraph[u][v] -= pathFlow;
				residualGraph[v][u] += pathFlow; 
			}
			maxFlow += pathFlow;

		}
		StringBuilder sb = new StringBuilder();
		for(String string : minCut){
			sb.append(string);
			sb.append("\n");
		}
		return "Max flow: " + maxFlow + "\nMin Cut:\n" + sb.toString();
	}
}