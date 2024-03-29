package binPacking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class BestFit {

	private List<Double> binsRemainingSpace;
	
	public BestFit(){
		this.binsRemainingSpace = new ArrayList<Double> ();
	}
	
	public void bestFit(Double currentItemSize,String noOfDecimals){
		int noOfBins = this.binsRemainingSpace.size();
		int i = 0,min_index = -1;
		double minRemainingSpace = 2.0;
		for(;i<noOfBins;i++) {
			double currentBinRemainingSpace = this.binsRemainingSpace.get(i);
			if(currentBinRemainingSpace >= currentItemSize && minRemainingSpace > currentBinRemainingSpace) {
				min_index = i;
				minRemainingSpace = currentBinRemainingSpace;
			}
		}
		if(min_index == -1)
			this.binsRemainingSpace.add(Double.parseDouble(String.format("%." + noOfDecimals + "f", 1.0 - currentItemSize)));
		
		else
			this.binsRemainingSpace.set(min_index, Double.parseDouble(String.format("%." + noOfDecimals + "f", minRemainingSpace - currentItemSize)));
			
	}
	public void displayBins(){
		for(int i = 0;i < this.binsRemainingSpace.size();i++){
			System.out.print(this.binsRemainingSpace.get(i).doubleValue());
			System.out.print(" ");
		}
		System.out.println();
	}
	
	public double calculateWastage(){
		double wastage = 0.0;
		for(int i = 0;i < binsRemainingSpace.size();i++)
			wastage += binsRemainingSpace.get(i);
		return wastage;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		
		int noOfLines = 10;
		List<Double> averageWastageDiffInput = new ArrayList<Double>();
		
		for(int noOfItemsPerLine = 10; noOfItemsPerLine <= 100000;){
			Scanner sc = new Scanner(new File("C:\\Users\\Karan\\Desktop\\Study\\Spring 2019\\CS260P-Algorithms\\Workspace\\Algo Project 2\\"+ Integer.toString(noOfItemsPerLine) + "_item_sizes.txt"));
			double averageWastage = 0.0;
			
			for(int i = 0;i < noOfLines;i++) {
				BestFit bf = new BestFit();
				int count = 0;
	//			Double[] itemSizes = new Double[] {0.5, 0.8, 0.2, 0.4};
				while (sc.hasNextDouble() && count < noOfItemsPerLine) {
			    	count++;
			        double currentItemSize = sc.nextDouble();
					
			        String size_s = Double.toString(currentItemSize);
					Integer decimalPlaces = size_s.length() - size_s.indexOf('.') - 1;
					String noOfDecimals = Integer.toString(decimalPlaces);
					
	//				System.out.print(currentItemSize + " ");
					bf.bestFit(currentItemSize,noOfDecimals);
				}
		
	//			System.out.println();
	//			bf.displayBins();
	//			System.out.println("Wastage :" + bf.calculateWastage());
				averageWastage += bf.calculateWastage();
	//			System.out.println();
			}
			averageWastage /= noOfLines;
			System.out.println(averageWastage);
			averageWastageDiffInput.add(averageWastage);
			
			sc.close();
			noOfItemsPerLine *= 10;
		}
	
	}
}
