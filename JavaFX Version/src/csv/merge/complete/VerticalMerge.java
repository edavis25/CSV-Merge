/*
 * @author Eric Davis
 */
package csv.merge.complete;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.concurrent.Task;

/**
 * 
 * @author Eric Davis
 */
public class VerticalMerge extends Task
{
    private final File FOLDER;
    private final String FOLDER_URL;
    
    
    /**
     * Default constructor.
     * @param folder Chosen directory containing .csv files to merge together.
     */
    public VerticalMerge(File folder)
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
        PrintWriter writer = new PrintWriter(FOLDER_URL + "/Vertical-Combine.csv");
        
        for (File file : FOLDER.listFiles())
        {
            //Skip over file we are creating to avoid infinite loop
            if (file.getName().endsWith("Vertical-Combine.csv"))
            {
                continue;
            }
            else if (file.getName().endsWith(".csv"))
            {
                Scanner scan =  new Scanner(file);
                while (scan.hasNextLine())
                {
                    String string = scan.nextLine();
                    writer.println(string);
                }
                scan.close();
            }
        }
        
        writer.flush();
        writer.close();
    }
}
