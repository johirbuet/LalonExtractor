import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class GID {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		String fileloc = "/Users/mislam/Desktop/Research/ResearchWeeklyProgress/BigData 2017/km.csv";
		Scanner sc = new Scanner(new File(fileloc));
		String [] lats = sc.nextLine().split(",");
		System.out.println(lats.length);
		ArrayList<Double> lat_list = new ArrayList<>();
		for(String s : lats){
			if(s.isEmpty() || s==null || s.equals("\\s+")){
				continue;
			}
			if(s.length()>2 &&s.charAt(1)=='A'){
				continue;
			}
			//System.out.println(s.charAt(1));
			s=s.trim();
			s = s.replaceAll("\\s+", "");
			s= s.replaceAll(" ", "");
			try {
				s=s.substring(0, s.length()-1);
				lat_list.add(Double.parseDouble(s));
			} catch (Exception e) {
				//System.out.println(s.charAt(s.length()-1) ==' ');
				//System.out.println(s.charAt(s.length()-1)+ " "+s);
				continue;
			}
			
		}
		System.out.println(lat_list.size());
	
	
		
		String [] lons = sc.nextLine().split(",");
		System.out.println(lons.length);
		
		ArrayList<Double> lon_list = new ArrayList<>();
		for(String s : lons){
			if(s.isEmpty() || s==null || s.equals("\\s+")){
				continue;
			}
			if(s.length()>2 &&s.charAt(1)=='A'){
				continue;
			}
			//System.out.println(s.charAt(1));
			s=s.trim();
			s = s.replaceAll("\\s+", "");
			s= s.replaceAll(" ", "");
			try {
				s=s.substring(0, s.length()-1);
				lon_list.add(Double.parseDouble(s));
			} catch (Exception e) {
				continue;
			}
			
		}
		System.out.println(lon_list.size());
		ArrayList<Integer> gids = new ArrayList<>();
		for(int i=1;i<lat_list.size();i++){
			int gid =gid(lat_list.get(i), lon_list.get(i));
			gids.add(gid);
		}
		sc.close();
	}
	public static int gid(double lat,double lon){
		int row = (int) ((lat - 40.37)/.01);
		int col = (int)((lon + 96.70)/.01 + row);
		return row*660 + col;
	}
	
	public Location getLoc(int gid){
		int row = gid/660;
		int col =gid%660;
		double lon = -96.70 +(col -row)*.01;
		double lat = 40.37+ row *.01;
		Location loc = new Location(lat, lon);
		return loc;
	}
	
	private class Location{
		public double lat;
		public double lon;
		public Location(double lat,double lon) {
			this.lat = lat;
			this.lon = lon;
		}
	}
}
