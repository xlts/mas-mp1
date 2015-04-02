package mh.proj1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Yeast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6138109446246893235L;
	
	private String name;
	private double floculationRate; //represents how much sediment this yeast produces
	private FermentationType fermentationType;
	private List<String> flavourProfile = new ArrayList<String>();
	
	public static List<Yeast> extent = new ArrayList<Yeast>();
	private static final String FILENAME = "yeast.txt";
	
	public Yeast() {
		extent.add(this);
	}
	
	public Yeast(String name, Double floculationRate, FermentationType fermentationType,
			String flavourProfile) {
		super();
		setName(name);
		setFloculationRate(floculationRate);
		setFermentationType(fermentationType);
		addFlavourProfile(flavourProfile);
		
		extent.add(this);
	}
	
	public static List<Yeast> findByFermentationType(FermentationType fermentationType) {
		List<Yeast> list = new ArrayList<Yeast>();
		
		for (Yeast y : extent) {
			if (y.fermentationType == fermentationType) {
				list.add(y);
			}
		}
		
		return list;
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

	
	public double getFloculationRate() {
		return floculationRate;
	}
	
	public void setFloculationRate(Double floculationRate) {
		if (floculationRate == null) {
			throw new IllegalArgumentException("passed value cannot be null");
		}
		this.floculationRate = floculationRate;
	}
	
	public FermentationType getFermentationType() {
		return fermentationType;
	}

	public void setFermentationType(FermentationType fermentationType) {
		if (fermentationType == null) {
			throw new IllegalArgumentException("passed value cannot be null");
		}
		this.fermentationType = fermentationType;
	}

	public boolean addFlavourProfile(String e) {
		if (e == null) {
			throw new IllegalArgumentException("passed value cannot be null");
		}
		return flavourProfile.add(e);
	}

	public boolean removeFlavourProfile(Object o) {
		if (flavourProfile.size() < 2) {
			throw new RuntimeException("yeast strain needs to have at least 1 flavour profile");
		}
		return flavourProfile.remove(o);
	}
	
	public List<String> getFlavourProfile() {
		return new ArrayList<String>(flavourProfile);
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
			extent = (ArrayList<Yeast>) stream.readObject();
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
	
	public static List<Yeast> getExtent() {
		return new ArrayList<Yeast>(extent);
	}
	
	@Override
	public String toString() {
		return "Yeast name: " + this.name + ", floc.rate: " + this.floculationRate + ", ferm.type: " + this.fermentationType;
	}


	
	
	
}
