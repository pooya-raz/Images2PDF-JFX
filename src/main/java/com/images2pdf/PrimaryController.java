package com.images2pdf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.text.DocumentException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimaryController {
    private List<ImageType> imageFiles = new ArrayList<ImageType>();
    private File saveToDirectory;
    private Boolean filesSelected;
    private Boolean directorySelected;
    private Boolean fileNameSet;

    @FXML
    private ListView<String> listView;

    @FXML
    private TextArea directory;

    @FXML
    private TextField fileName;

    @FXML
    private Button makePDFButton;

    @FXML
    public void initialize(){
        fileNameSet = true;
        directorySelected = false;
        filesSelected = false;
    }

    private void tryToEnableMakePDFButton(){
        if(filesSelected && directorySelected && fileNameSet){
            makePDFButton.setDisable(false);
        }
    }

    public void selectFile(ActionEvent e){
        FileChooser fileChooser = new FileChooser();


        //Set extension filter
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("Image files (*.jpg)", "*.jpg" );
        fileChooser.getExtensionFilters().add(fileExtensions);
        System.out.println("pressed");
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(new Stage());

        if(selectedFiles != null){
            for (File file: selectedFiles){
                listView.getItems().add(file.getName());

            }
        }else{
            System.out.println("No valid files selected");
        }
        Thread newThread = new Thread(() ->{
            if(selectedFiles!=null){
                for (File file:selectedFiles){
                    ImageType image = new ImageType(file);
                    image.optimizeImage();
                    imageFiles.add(image);
                }
            }
        });
        newThread.start();


        filesSelected = true;
        tryToEnableMakePDFButton();

    }

    public void selectLocation(){
        //Allow the selection of the output destination
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Set title");
        File defaultDirectory = new File("./");
        chooser.setInitialDirectory(defaultDirectory);
        saveToDirectory = chooser.showDialog(new Stage());

        //Modify results to display in TextArea
        String[] fullDirectories = saveToDirectory.toString().split("/");
        String[] relevantDirectories = Arrays.copyOfRange(fullDirectories, 3, fullDirectories.length);
        StringBuilder directoriesString = new StringBuilder();
        for (int i=0; i< relevantDirectories.length; i++){
            directoriesString.append("  ".repeat(i));
            directoriesString.append(relevantDirectories[i]);
            directoriesString.append("\n ");
        }
        directory.setText(directoriesString.toString());
        directorySelected = true;
        tryToEnableMakePDFButton();
    }

    public void saveFileAs(){
        if(!fileName.getText().equals("")){
            fileNameSet = true;
        }
    }

    public void makePDF() throws DocumentException, IOException {
        PDFMaker pdfMaker = new PDFMaker();

        pdfMaker.makePDF(imageFiles, saveToDirectory, fileName.getText()+".pdf");
    }
}
