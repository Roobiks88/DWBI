import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;

public class Jaccard {

	/*
	 * This code calculates the Jaccard Distance.
	 * Follow this link for a simple explanation on how to calculate the Jaccard Distance:
	 * https://www.statisticshowto.com/jaccard-index/
	 * 
	 * The results will be saved into a csv file.
	 * This csv file can be imported into an excel file by
	 * separating the entries with a comma delimiter.
	 * For calculating the Jaccard Similarity simply uncomment code line 187 and comment line 188.
	 * 
	 * Part 1: Array gets filled with each word, named tokens.
	 * Part 2: Calculation of Jaccard Distance.
	 * Part 3: Writing of file
	 * 
	 * Important: If you use large files, please watch out for your heap and garbage collector!
	 * Add following lines to yout VM arguments:  
	 * 
	 * -Xmx5024M
	 * -XX:+UseConcMarkSweepGC
	 * -XX:-UseGCOverheadLimit
	 * 
	 * Important: Variable y has to be changed to the numbers of lines in your text document!
	 * Important: Variable y could be calculated automatically, please feel free to add code lines for this!
	 * Important: Variable x has to be changed to a large amount so all lines will have space!
	 * Important: Variable x could be calculated automatically, please feel free to add code lines for this!
	 * Important: Please make sure, that your words in each line are already sorted, if no please feel free to
	 * add a bubble sort or any other sorting algorithm.
	 * 
	 * Please feel free to copy and edit the code.
	 * 
	 * Kind regards,
	 * Olivia Williams
	 * 04.11.2020
	 *  
	 */
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		// 1
		// save words as tokens in array
		// save length of each line in second array
		
		// variables for dimension of array
		// x represents the length of lines, please make sure to add a wide range.
		// y represents the width of each line, please change according to your text file.
		int x = 200;
		int y = 4;
		
		// multidimensional array for tokens
		// this array will be filled with all words as tokens
		String[][] arr = new String[y][x];

		// array for length of each line
		int[] linesl = new int[y];
		
		// fill each element with 0
		 for(int k=0; k<y; k++) {
			 for(int i=0; i<x; i++) {
				 arr[k][i]="0";
			 } // end of for
		 } // end of for
		
		
		// read in lines of file
		// change path accordingly
		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\olivi\\eclipse-workspace\\TestJava\\src\\para_small_.txt"));
		String line;
		 
		// split words of file into array
		// each single word will be added as token to the array
		// seconde array linels[] will carry the length of each line
		int j=0;
		// split words and put into multidimensional array
		while ((line = in.readLine()) != null) {
		    	String[] splited = line.split(" ");
		    	for (int i=0; i<splited.length; i++) {
		    		arr[j][i]=splited[i];
		    	}
		    	
		    	// put length of line into array
		    	linesl[j] = splited.length;
		    	
		    	j=j+1;
		    } // end of while
	
		
		//2
		// calculate distance (1- Jaccard Similarity)
		// compare each line to all other lines
		
		 
		// multidimensional array for Jaccard Distance
		BigDecimal[][] arrd = new BigDecimal[y][x];
		
		// write first row
		FileWriter myWriter2 = new FileWriter("C:\\Users\\olivi\\eclipse-workspace\\TestJava\\src\\table.csv");
		myWriter2.write("Jaccard Distance,");
		for(int k=0; k<y; k++) {
			// for last document no comma at the end
			if(k==y-1) {
				myWriter2.write("Doc"+(k+1));
			}
			else {
			myWriter2.write("Doc"+(k+1)+",");
			}
			
		} // end of for
			
		myWriter2.write("\n");

		// Union and Interception and Division of both:
		// 1- Interception / Union
		
		for(int p=0; p<y; p++) {
			
		// write document
		myWriter2.write("Doc"+(p+1)+",");
		
		// write empty entries followed by comma
		// this is the empty cell
	     if (p==0) { } 
	     else {
	    	 for (int z=1; z<=p; z++) {
	    		 myWriter2.write(",");
	    	 } // end of for
	    	 
	     } // end of else
	     
	     // write content of arrd
			
		for(int q=p; q<y; q++) {
		
		int e= linesl[p];
		int r = linesl[q];
		
	    String[] firstArray = new String[e];
	    String[] secondArray = new String[r];
	    
	    for(int i=0; i<linesl[p]; i++) {
	    	firstArray[i]=arr[p][i];
	    }
	    
	    for(int i=0; i<linesl[q]; i++) {
	    	secondArray[i]=arr[q][i];
	    }
	     
	    // use Union and Intersect for calculation of Jaccard Distance
	    
	    HashSet<String> set = new HashSet<>(); 
	    set.addAll(Arrays.asList(firstArray));
	    set.addAll(Arrays.asList(secondArray));
	     
	    String[] union = {};
	    union = set.toArray(union);

	    HashSet<String> set2 = new HashSet<>(); 
	    set2.addAll(Arrays.asList(firstArray));
	    set2.retainAll(Arrays.asList(secondArray));
	
	    String[] intersection = {};
	    intersection = set2.toArray(intersection);

	    // get decimal values
	     BigDecimal bg1, bg2, bg3, one;
	     bg1 = new BigDecimal(union.length);
	     bg2 = new BigDecimal(intersection.length);
	     bg3 = bg2.divide(bg1, 3, RoundingMode.CEILING);
	     one = new BigDecimal(1);
	     //System.out.println(one.subtract(bg3));
	    
	     // add Jaccard Distance to array
	     // if you would like to change to Jaccard Similarity,
	     // uncomment next line and comment second next line
	     //  arrd[p][q]=bg3;
	     arrd[p][q]=one.subtract(bg3);
	     
	     // Output of each document and Jaccard Distance in lines
	     System.out.println("Array content of "+ p +" and " + q +" " + arrd[p][q]);
	     
	     
	     // 3
	     // write Jaccard distances to visual format
	     // write entries followed by comma
	     
	     if (q==y-1) {
	    	 myWriter2.write(arrd[p][q] + "");
	    	
	     }
	     else {
	    	 myWriter2.write(arrd[p][q] + ",");
	    	 
	     }
	     
			} // end of internal for
			
			// write new line
			myWriter2.write("\n");
			
		} // end of external for
	
		myWriter2.close();
		
	} // end of main

} // end of class
