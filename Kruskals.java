import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
class Edge implements Comparable<Edge>{
	int value;
	String u;
	String v;
	public Edge() {//initializing variables
		value = 0;
		u = "";
		v = "";
	}
	public Edge( int val, String left, String right ) {
		value = val;
		u = left;
		v = right;
	}
	public int compareTo(Edge e) {
		return this.value - e.value;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Edge)) {
			return false;
		}		
		Edge e = (Edge) o;		
		if(this.value == e.value && this.u.equals(e.u) && this.v.equals(e.v)) {
			return true;
		}else if(this.value == e.value && this.v.equals(e.u) && this.u.equals(e.v)) {
			return true;
		}
		return false;
	}
}
public class Kruskals {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		ArrayList<String> city = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		Edge ed;
		//File file = new File("C:/Users/Rishav-PC/eclipse-workspace/kruskals/assn9_data.csv"); //for absolute path
		File file = new File("assn9_data.csv"); //for direct path
		//System.err.println(file.getAbsolutePath());
		
		try {
			Scanner fl = new Scanner(file);
			while(fl.hasNextLine()) {
				String s = fl.nextLine();
				String [] ar = s.split(",");
				city.add(ar[0]);
				for(int i = 1; i < ar.length-1; i+=2) {
					ed = new Edge(Integer.parseInt(ar[i+1]) ,ar[0], ar[i]);
					if(!pq.contains(ed)) {
						pq.add(ed);
					}					
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < city.size(); i++) {
			map.put(city.get(i), i);
		}
		DisjSets ds = new DisjSets(city.size());
		int edgesAccepted = 0;
		int totalDistance = 0;
		while(edgesAccepted < city.size() - 1) {
			ed = pq.poll();
			if(ds.find(map.get(ed.u)) != ds.find(map.get(ed.v))) {
				// accept the edge
	            edgesAccepted++;
				ds.union(ds.find(map.get(ed.u)), ds.find(map.get(ed.v)));
				System.out.println(ed.u + " "+ ed.v + " " + ed.value);
				totalDistance += ed.value;
			}
		}
		System.out.println();
		System.out.println("Total Distance is: " + totalDistance);
	}
}