package datalayer;


import businesslogic.Bestilling;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Casper P, Frederik, Mikkel
 */

public class FileFacade {
    //Write file tager en bestilling inde fra controlleren når vi opretter ordre. 
    // Den writer så ordrens toString ind i filen
    public void writeFile(Bestilling bestilling) throws IOException {
        File ordrehistorik = new File("ordrehistorik.txt");
        FileWriter filewriter = new FileWriter(ordrehistorik, true);
        BufferedWriter buf = new BufferedWriter(filewriter);
        buf.write(bestilling.toString());
        buf.newLine();
        buf.flush();
    }
    
    
    public void visOrdreHistorik() throws FileNotFoundException {
        File ordrehistorik = new File("ordrehistorik.txt");
        Scanner scan = new Scanner(ordrehistorik);
        
        while(scan.hasNextLine()){
            String token = scan.nextLine();
            System.out.println(token);
        }
        scan.close();
        
        
    }
}
