package mh.proj1;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy, MM, dd");
		
		Yeast y1 = new Yeast("Wyeast Belgian Ardennes", 0.75, FermentationType.TOP, "banana");
		Yeast y2 = new Yeast("Whitelabs California Ale", 0.80, FermentationType.TOP, "flowers");
		Yeast y3 = new Yeast("Saflager S-33", 0.80, FermentationType.BOTTOM, "malty");
		
		Beer b1 = new Beer("HoppyPilsner", sdf.parse("2015, 3, 9"), 2000, 5000, y3);
		Beer b2 = new Beer("American Ale", sdf.parse("2015, 2, 10"), null, 7000, y2);
		
		System.out.println(b1);
		System.out.println(b2);
		
		System.out.println();
		
		System.out.println("Newest batch no.: " + Beer.NEWEST_BATCH_NUMBER);
		
		System.out.println();
		
		System.out.println("b1 bestBeforeDate: " + b1.getBestBeforeDate());
		System.out.println("b1 bestBeforeDate (overloaded method): " + b1.getBestBeforeDate(120));
		
		Beer.saveExtent();
		Beer.loadExtent();
		
		System.out.println();
		
		System.out.println("Beer class extent: \n" + Beer.getExtent());
		
		
		System.out.println();
		
		System.out.println("Top-fermenting yeasts: \n" + Yeast.findByFermentationType(FermentationType.TOP));
		
		System.out.println();
		
		y1.addFlavourProfile("peach");
		
		System.out.println("y1 flavour profile: " + y1.getFlavourProfile());
		
		y1.removeFlavourProfile("peach");
		
		System.out.println("y1 flavour profile: " + y1.getFlavourProfile());
		
		System.out.println();
		
		try {
			y1.removeFlavourProfile("banana"); //results in an exception
		} catch (Exception e) {
			System.err.println("caught an exception: " + e.getMessage());
		}
		
	}
}
