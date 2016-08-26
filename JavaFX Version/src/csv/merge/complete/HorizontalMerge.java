/*
 * @author Eric Davis
 */
package csv.merge.complete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.concurrent.Task;

/**
 *
 * @author Eric Davis
 */
public class HorizontalMerge extends Task
{
    private final File FOLDER;
    private final String FOLDER_URL;
    
    private List<List<String>> arrayOfArrays = new ArrayList<List<String>>();
    
    /**
     * Default constructor
     * @param folder Chosen directory containing .csv files to merge together.
     */
    public HorizontalMerge(File folder)
    {
        this.FOLDER = folder;
        this.FOLDER_URL = folder.getAbsolutePath();
    }

    @Override
    protected Object call() throws Exception 
    {
        execute();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Main program execution for this task
     * @throws FileNotFoundException .csv file could not be created or found.
     */
    private void execute() throws FileNotFoundException
    {
        int index = 0;
        
        for (File file : FOLDER.listFiles())
        {
            //Skip over file we are creating to avoid infinite loop --->
            if (file.getName().endsWith("Horizontal-Combine.csv"))
            {
                continue;
            }
            else if (file.getName().endsWith(".csv"))
            {
                Scanner scan =  new Scanner(file);
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
        
        // Create PrintWriter and write Strings in the array to a .csv file --->
        PrintWriter writer = new PrintWriter(FOLDER_URL + "/Horizontal-Combine.csv");
        for (int i =  0, length = arrayOfArrays.get(0).size(); i < length; i++)
        {
            // For each scanned file, write one line, (side-by-side) and then move to the next line in each file.
            for (int j = 0, size = arrayOfArrays.size(); j < size; j++)
            {
                writer.print(arrayOfArrays.get(j).get(i));
            }
            writer.println();
        }

        writer.flush();
        writer.close();
    }
}
