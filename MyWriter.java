package com.company;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class MyWriter extends Component {


    public static String reader(String fileName) {

        File inF = new File(fileName);
        try {
            Scanner in = new Scanner(inF);
            String testo = in.nextLine();
            while(in.hasNextLine()){
                testo+="\n";
                testo+=in.nextLine();
            }
            return testo;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void writer(String fileName, String testo){
        File inF = new File(fileName);
        try {
            BufferedWriter scritturaFile = new BufferedWriter(new FileWriter(inF));
            scritturaFile.write(testo);
            scritturaFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void fileMother(String fileName){
        File inF = new File(fileName);
        try {
            BufferedWriter scritturaFile = new BufferedWriter(new FileWriter(inF));
            scritturaFile.write("Share Notepad-- with all your friends! ");
            scritturaFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File find(){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(MyWriter.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        } else {
            System.out.println("azione annullata");
        }
        return null;
    }
    public File save(){
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Specify file name and directory");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        fc.setFileFilter(filter);
        int userSelection = fc.showSaveDialog(MyWriter.this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            return fc.getSelectedFile();
        } else {
            System.out.println("azione annullata");
        }
        return null;
    }
}
