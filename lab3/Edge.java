public class Edge{
	private City start;
	private City end;
	private int distance;

	public Edge(City start, City end, int distance){
		this.start = start;
		this.end = end;
		this.distance=distance;
	}

	public City getStart(){
		return start;
	}

	public City getEnd(){
		return start;
	}

	public int getDistance(){
		return distance;
	}

	public String toString(){
		return start.toString() + " -- " + end.toString();
	}
}