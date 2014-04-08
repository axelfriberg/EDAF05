public class City{

	private City destination;
	private String name;
	private int height;

	 public City(String name){
	 	destination = this;
	 	this.name = name;
	 	height = 1;
	 }

	 public void setDestination(City l){
	 	destination = l;
	 }

	 public City findRoot(){
	 	if(destination.equals(this)){
	 		return this;
	 	}
	 	else{
	 		return destination.findRoot();
	 	}
	 }

	 public boolean equals(City c){
	 	return name.equals(c.name);
	 }

	 public void incrHeight(){
	 	height++;
	 }

	 public int getHeight(){
	 	return height;
	 }

	 public String toString(){
	 	return name;
	 }
}