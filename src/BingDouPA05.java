//*********************************************************************
//*                                                                   *
//* CIS611               Summer Session 2016                Bing Dou  *
//*                                                                   *
//*                    Program Assignment PA05                        * 
//*                                                                   *
//*                       Class Description                           *
//*                                                                   *
//*                                                                   *
//*           	          6/26/2016 Created                           *
//*                                                                   *
//*                   Saved in: BingDouPA05.java                      *
//*                                                                   *
//*********************************************************************
//

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class BingDouPA05 {
	
	static String fileName;
	static String[] total_info = new String[12];

	public static void repairData(String fileName) throws IOException{
		
		// Initialize the input file
		java.io.File file = new java.io.File(fileName);
    	
		// Create a Scanner for the file
    	Scanner input = new Scanner(file);
    	input.useDelimiter(",|\\s\n");
    	
    	// Read file line by line
    	int y = 0;
		do
		{
			// Set contents to the array
			for(int x = 0; x < 12; x++){
				total_info[x] = input.next();
			}
			
			// Initialize the variables
			String temp_info11 = "web";
			String temp_info10 = "email";
			String temp_info9 = "phone2";
			int index11 = 11;
			int index10 = 10;
			int index9 = 9;
			
			// Set patterns: web[11],email[10],phone2[9]
			Pattern webPattern = Pattern.compile("^http[s]?://www.*.com$");
			Pattern emailPattern = Pattern.compile( "^.*@.*$" );
			Pattern phone2Pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
			
			// Check the information of web[11]
			for(int x = 0; x < 12; x++){
				
				// Set matchers for web[11]
				Matcher webMatcher = webPattern.matcher(total_info[x]); 
				
				if(webMatcher.find()){
					if(x != 11){
						temp_info11 = total_info[x]; // get web site
						index11 = x;
						//System.out.println("[11]Wrong place: " + x);
					}
					else{
						temp_info11 = total_info[x];
						//System.out.println("[11]Right place!");
					}
				}
			}
			total_info[index11] = total_info[11];
			total_info[11] = temp_info11;
			
			// Check the information of email[10]
			for(int x = 0; x < 12; x++){
				
				// Set matchers for email[10]	

				Matcher emailMatcher = emailPattern.matcher(total_info[x]);
				
				if(emailMatcher.find()){
					if(x != 10){
						//System.out.println("[10]Wrong place: " + x);
						temp_info10 = total_info[x]; // get email address
						index10 = x;
					}
					else{
						temp_info10 = total_info[x];
						//System.out.println("[10]Right place!" );
					}
				}
			}
			total_info[index10] = total_info[10];
			total_info[10] = temp_info10;
			
			// Check the information of phone2[9]
			for(int x = 0; x < 12; x++){
				
				// Set matchers for phone2[9]	
				Matcher phone2Matcher = phone2Pattern.matcher(total_info[x]);
				
				if(x != 8){
					if(phone2Matcher.find()){
						if(x != 9){
							//System.out.println("[9]Wrong place: " + x);
							temp_info9 = total_info[x];
							index9 = x;
						}
						else{
							temp_info9 = total_info[x];
							//System.out.println("[9]Right place!" );
						}
					}
				}
			}
			total_info[index9] = total_info[9];
			total_info[9] = temp_info9;

			// Initialize the output file
			java.io.FileWriter output = new java.io.FileWriter(new File("output.txt"), true);
			java.io.PrintWriter printWriter = new java.io.PrintWriter(output);
			
			// Print one by one
			for(int x=0; x<12; x++){
				//System.out.println("[" + y + "] " + x + " " + total_info[x] );
				if(x<11){
					printWriter.print(total_info[x] + ", ");
				}
				else{
					printWriter.print(total_info[x]);
				}
			}
			printWriter.println("\n");
			output.close();
			printWriter.close();
			
			y++;
			
		}while(input.hasNext() && y < 502);	
		
	    // Close the file
	    input.close();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// Using JFileChooser to get the path
		JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setCurrentDirectory(new java.io.File("."));
	    fileChooser.setDialogTitle("Choose a File");
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			fileName = fileChooser.getSelectedFile().getPath();
			repairData(fileName);
		}
		else {
	    	JOptionPane.showMessageDialog(null,"Canceled, exit!");
		}
	}
}
