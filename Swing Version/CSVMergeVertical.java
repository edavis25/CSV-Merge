import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.io.PrintWriter;


public class CSVMergeVertical
{
	static PrintWriter writer;
	static String importDirectory;

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
		writer = new PrintWriter(importDirectory + "/Vertical-Combine.csv");

		// Find all csv files, scan each line-by-line, and then write each one into a combined file.
		File folder = new File(importDirectory);

		for (File file : folder.listFiles())
		{
			// Skip over the file we are creating to avoid an infinite loop
			if (file.getName().endsWith("Vertical-Combine.csv"))
			{
				continue;
			}
			else if (file.getName().endsWith(".csv"))
			{
				Scanner scan = new Scanner(file);
				while (scan.hasNextLine())
				{
					String string = scan.nextLine();
					writer.println(string);
				}
				scan.close();
			}
		}

		writer.close();

		// Confirmation message for finished
		JOptionPane.showMessageDialog(null, "Merge Complete.\nFile Path: " + importDirectory + "\\Vertical-Combine.csv", "Finished", JOptionPane.INFORMATION_MESSAGE);

	}
}
