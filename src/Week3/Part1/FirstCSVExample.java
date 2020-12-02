package Week3.Part1;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class FirstCSVExample {
    public static void main(String[] args) {
        readFood();
    }
    public static void readFood(){
        FileResource fl = new FileResource("Example.csv");
        CSVParser parser = fl.getCSVParser();
        for(CSVRecord record : parser){
            System.out.print(record.get("Name") + " ");
            System.out.println(record.get("Favourite Food"));
        }

    }
}
