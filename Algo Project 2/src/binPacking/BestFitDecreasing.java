package binPacking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BestFitDecreasing {
	public static void main(String[] args) throws FileNotFoundException{
		int noOfLines = 10 ;
		List<Double> averageWastageDiffInput = new ArrayList<Double>();
		
		for(int noOfItemsPerLine = 10; noOfItemsPerLine <= 100000;){
			Scanner sc = new Scanner(new File("C:\\Users\\Karan\\Desktop\\Study\\Spring 2019\\CS260P-Algorithms\\Workspace\\Algo Project 2\\"+ Integer.toString(noOfItemsPerLine) + "_item_sizes.txt"));
			double averageWastage = 0.0;
			
			for(int lineCount = 0;lineCount < noOfLines;lineCount++) {
				List<Double> items = new ArrayList<Double>();
				BestFit bfd = new BestFit();
				int count = 0;
				
				while(sc.hasNextDouble() && count < noOfItemsPerLine) {
					double currentItemSize = sc.nextDouble();
					items.add(currentItemSize);
					count++;
				}
				
				Collections.sort(items);
				Collections.reverse(items);
				
				for(int j = 0;j < items.size();j++){
					double currentItemSize = items.get(j);
					
					String size_s = Double.toString(currentItemSize);
					Integer decimalPlaces = size_s.length() - size_s.indexOf('.') - 1;
					String noOfDecimals = Integer.toString(decimalPlaces);
					
					bfd.bestFit(currentItemSize,noOfDecimals);
				}
				averageWastage += bfd.calculateWastage();
			}
			averageWastage /= noOfLines;
			averageWastageDiffInput.add(averageWastage);
			sc.close();

			System.out.println(averageWastage);
			noOfItemsPerLine *= 10;
		}
	}

}
