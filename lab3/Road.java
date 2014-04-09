import java.lang.*;
public class Road implements Comparable<Road>{
	private String start;
	private String end;
	private int distance;

	public Road(String start, String end, int distance){
		this.start = start;
		this.end = end;
		this.distance=distance;
	}

	public String getStart(){
		return start;
	}

	public String getEnd(){
		return end;
	}

	public int getDistance(){
		return distance;
	}

	public String toString(){
		return start.toString() + " - " + Integer.toString(distance) + " - " + end.toString();
	}

	public int compareTo(Road r){
		if(!r.getClass().equals(this.getClass())){
			throw new ClassCastException();
		}
		else{
			return distance - r.getDistance();
		}
	}
}