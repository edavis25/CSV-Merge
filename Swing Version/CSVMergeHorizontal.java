import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.PrintWriter;

public class CSVMergeHorizontal 
{
	static PrintWriter writer;
	static String importDirectory;
	
	static int index = 0;
	
	// Array used to hold the string arrays that contain one line of the csv file per element.
	static List<List<String>> arrayOfArrays = new ArrayList<List<String>>();

	
	public static void main(String[] args) throws FileNotFoundException 
	{
		// Show dialog to open folder containing .csv files
		JOptionPane.showMessageDialog(null, "Choose the folder containing the .csv files. (All .csv files in folder will be read)\nExport file will be placed in the chosen import directory.", "Information", JOptionPane.INFORMATION_MESSAGE);
		
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("choosertitle");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
		{
			// Send absolute path of selected folder to a string
			importDirectory = chooser.getSelectedFile().getAbsolutePath();
		} 
		else 
		{
		  System.exit(0);
		}
		
		// Create new file in the chosen directory
		writer = new PrintWriter(importDirectory + "/Horizontal-Combine.csv");

		// Find all csv files, scan each line-by-line, writing each line into a separate element in a nested array.
		File folder = new File(importDirectory);
		
		for (File file : folder.listFiles())
		{		
			if (file.getName().endsWith(".csv"))
			{
				// Add a new string array to the array of arrays for each csv file.
				Scanner scan = new Scanner(file);
				arrayOfArrays.add(new ArrayList<String>());
				
				while (scan.hasNextLine())
				{
					String string = scan.nextLine();
					arrayOfArrays.get(index).add(string);
				}
				scan.close();
				index++;
			}
		}
		
		// For each line in the scanned imported csv files
		for (int i = 0, length = arrayOfArrays.get(0).size(); i<length; i++)
		{
			// For each scanned file, write one line, (side-by-side) and then move on to the next line in each file.
			for (int j = 0; j < arrayOfArrays.size()-1; j++)
			{
				writer.print(arrayOfArrays.get(j).get(i));
			}
			
			writer.println();
			
		}
		writer.close();
		
		// Confirmation message for finished
		JOptionPane.showMessageDialog(null, "Merge Complete.\nFile Path: " + importDirectory + "\\Horizontal-Combine.csv", "Finished", JOptionPane.INFORMATION_MESSAGE);

	}
}
