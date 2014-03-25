import java.util.*;

public class Reader{
	private HashMap<Integer, Person> map;

	public Reader(String filePath){
		Scanner scanner = new Scanner(new File(filePath));
		map = new HashMap<>();

	}
}