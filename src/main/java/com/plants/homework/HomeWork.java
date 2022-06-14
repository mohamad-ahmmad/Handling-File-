

package com.plants.homework;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


/**
 *
    * @author Mhammad Ahmad
    * @reg-number 12028676
 */
public class HomeWork {
    public final static File file = new File("plants.txt");
    public static void main(String[] args) throws FileNotFoundException, IOException {
       MainFrame s = new MainFrame();
       s.setVisible(true);
       s.setResizable(false);
    }
}
