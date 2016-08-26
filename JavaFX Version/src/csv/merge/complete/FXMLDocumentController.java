/*
 * @author Eric Davis
*/
package csv.merge.complete;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Eric Davis
 */
public class FXMLDocumentController implements Initializable 
{
    @FXML private Button horizontalMergeButton, verticalMergeButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO - Auto generated
    }    
    
    /**
     * Action for "Horizontal Merge" button click
     */
    @FXML
    private void horizontalMergeButtonClick()
    {
        try 
        {
            File folder = chooseFile();
            Task task = new HorizontalMerge(folder);
            Thread thread = new Thread(task);
            thread.start();
            thread.join();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Horizontal Merge Successful");
            alert.setHeaderText("Horizontal merge completed successfully.");
            alert.setContentText("Your merged file is located at:\n"
                    + folder.getAbsolutePath() + "\\Horizontal-Combine.csv");
            alert.showAndWait();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Alert fail = new Alert(Alert.AlertType.ERROR);
            fail.setTitle("Merge Failed");
            fail.setHeaderText("CSV file merge failed");
            fail.setContentText("Your .csv files failed to merge together.\n"
                    + "Check chosen directory and try again");
            fail.showAndWait();
        }
    }
    
    
    /**
     * Action for the "Vertical Merge" button click
     */
    @FXML
    private void verticalMergeButtonClick()
    {
        try 
        {
            File folder = chooseFile();
            Task task = new VerticalMerge(folder);
            Thread thread = new Thread(task);
            thread.start();
            thread.join();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vertical Merge Successful");
            alert.setHeaderText("Vertical merge completed successfully.");
            alert.setContentText("Your merged file is located at:\n"
                    + folder.getAbsolutePath() + "\\Vertical-Combine.csv");
            alert.showAndWait();
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            Alert fail = new Alert(Alert.AlertType.ERROR);
            fail.setTitle("Merge Failed");
            fail.setHeaderText("CSV file merge failed");
            fail.setContentText("Your .csv files failed to merge together.\n"
                    + "Check chosen directory and try again");
            fail.showAndWait();
        }
    }
    
    /**
     * Display user prompt to select folder (DirectoryChooser class)
     * @return return File object for selected directory.
     */
    private File chooseFile()
    {
        DirectoryChooser chooseFolder = new DirectoryChooser();
        File folder = chooseFolder.showDialog(new Stage());
        if  (folder.exists())
        {
            return folder;
        }
        else
        {
            return null;
        }
    }
}
