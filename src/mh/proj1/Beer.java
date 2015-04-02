package mh.proj1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Beer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6802041954086147412L;
	
	private String name;
	private Date productionDate;
	private Integer bottlesFilled; //optional attribute - the brewery focuses on keg production
	private int kegsFilled;
	private int batchNumber;
	private Yeast yeast; //complex attribute

	public static int NEWEST_BATCH_NUMBER; //used to calculate the batchNumber attribute
	
	public static List<Beer> extent = new ArrayList<Beer>();
	private static final String FILENAME = "beer.txt";
	
	
	public Beer() {
		extent.add(this);
	}

	public Beer(String name, Date productionDate,
			Integer bottlesFilled, Integer kegsFilled, Yeast yeast) {
		
		super();
		setName(name);
		setProductionDate(productionDate);
		setBatchNumber();
		setBottlesFilled(bottlesFilled);
		setKegsFilled(kegsFilled);
		setYeast(yeast);
		
		extent.add(this);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("passed value cannot be null");
		}
		this.name = name;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		if (productionDate == null) {
			throw new IllegalArgumentException("passed value cannot be null");
		}
		this.productionDate = productionDate;
	}

	public int getBatchNumber() {
		return batchNumber;
	}

	private void setBatchNumber() {
		this.batchNumber = NEWEST_BATCH_NUMBER + 1;
		Beer.NEWEST_BATCH_NUMBER++;
	}

	public int getBottlesFilled() {
		return bottlesFilled;
	}

	public void setBottlesFilled(Integer bottlesFilled) {
		
		this.bottlesFilled = bottlesFilled;
	}

	public int getKegsFilled() {
		return kegsFilled;
	}

	public void setKegsFilled(Integer kegsFilled) {
		if (kegsFilled == null) {
			throw new IllegalArgumentException("passed value cannot be null");
		}
		this.kegsFilled = kegsFilled;
	}
	
	public Date getBestBeforeDate() { //derived attribute - default
		Calendar c = Calendar.getInstance();
		c.setTime(productionDate);
		c.add(Calendar.DATE, 30); //adds 30 days to productionDate
		return c.getTime();
	}
	
	public Date getBestBeforeDate(int days) { //in case it's a non-standard beer (overloaded method)
		Calendar c = Calendar.getInstance();
		c.setTime(productionDate);
		c.add(Calendar.DATE, days); //adds 30 days to productionDate
		return c.getTime();
	}
	
	public static void saveExtent(){
		ObjectOutputStream stream = null;
		
		try {
			
			stream = new ObjectOutputStream(new FileOutputStream(new File(FILENAME)));
			stream.writeObject(extent);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		finally {
			
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static void loadExtent(){
		ObjectInputStream stream = null;
		
		try {
			stream = new ObjectInputStream(new FileInputStream(new File(FILENAME)));
			extent = (ArrayList<Beer>) stream.readObject();
		} catch (IOException e){
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		finally {
			
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static List<Beer> getExtent() {
		return new ArrayList<Beer>(extent);
	}
	
	@Override
	public String toString() {
		return "Beer no." + this.batchNumber + ": " + this.name + ", brewed on " + this.productionDate.toString() + 
				", kegs filled:" + this.kegsFilled + ", bottles filled:" + (this.bottlesFilled != null ? this.bottlesFilled : "none") +
				", yeast strain name: " + this.yeast.getName();
	}

	public Yeast getYeast() {
		return yeast;
	}

	public void setYeast(Yeast yeast) {
		if (yeast == null) {
			throw new IllegalArgumentException("passed value cannot be null");
		}
		this.yeast = yeast;
	}
	
	public void setYeast(String name, Double floculationRate, FermentationType fermentationType,
			String flavourProfile) { //overloaded method
		this.yeast = new Yeast(name, floculationRate, fermentationType, flavourProfile);
	}

}
