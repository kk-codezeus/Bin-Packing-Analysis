package binPacking;

import java.io.*;
import java.util.*;

public class NextFit {
	
	private List<Double> binsRemainingSpace;					// Initially new bin created has 1.0 space
	
	public NextFit(){
		this.binsRemainingSpace = new ArrayList<Double> ();
	}
	
	public void nextFit(Double currentItemSize,String noOfDecimals){
		int noOfBins = this.binsRemainingSpace.size();
		
		if(noOfBins == 0 || this.binsRemainingSpace.get(noOfBins - 1) < currentItemSize)
			this.binsRemainingSpace.add(Double.parseDouble(String.format("%." + noOfDecimals +"f", 1.0 - currentItemSize)));
		
		else{
			double remainingSpace = this.binsRemainingSpace.get(noOfBins - 1); 
			this.binsRemainingSpace.set(noOfBins - 1, Double.parseDouble(String.format("%." + noOfDecimals +"f", remainingSpace - currentItemSize)));
		}
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
			
			for(int lineCount = 0;lineCount < noOfLines;lineCount++) {
				NextFit nf = new NextFit();
				int count = 0;
	//			Double[] itemSizes = new Double[] {0.5, 0.8, 0.2, 0.4};
				while (sc.hasNextDouble() && count < noOfItemsPerLine) {
			    	count++;
			        double currentItemSize = sc.nextDouble();
					
			        String size_s = Double.toString(currentItemSize);
					Integer decimalPlaces = size_s.length() - size_s.indexOf('.') - 1;
					String noOfDecimals = Integer.toString(decimalPlaces);
					
	//				System.out.print(currentItemSize + " ");
					nf.nextFit(currentItemSize,noOfDecimals);
				}
		
	//			System.out.println();
	//			nf.displayBins();
	//			System.out.println("Wastage :" + nf.calculateWastage());
				averageWastage += nf.calculateWastage();
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
