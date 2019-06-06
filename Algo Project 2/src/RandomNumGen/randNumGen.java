package RandomNumGen;

import java.io.*;
import java.util.*;

public class randNumGen {
	
	static int noOfItems = 1000;
	public static void main(String[] args) throws IOException{
		Random rn = new Random();
		BufferedWriter bw = new BufferedWriter(new FileWriter(Integer.toString(noOfItems) + "_item_sizes.txt"));
		for(int j = 0;j <10;j++) {
			for(int i = 0;i < noOfItems;i++)
				bw.append(Double.toString(rn.nextDouble() * 0.8) + " ");
			bw.append("\n");
		}
		bw.close();
	}
}
