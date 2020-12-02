package Week3.Part2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVHumidity {
    public CSVRecord minimumHumidityInFile(CSVParser parser) {
        CSVRecord minimumSoFar = null;
        double lowest = 0;
        double current = 0;
        for (CSVRecord currentRow : parser) {
            if (minimumSoFar == null) {
                minimumSoFar = currentRow;

            }
            if (currentRow.get("Humidity").equals("N/A")) {
                current = -999;
            } else {
                current = Double.parseDouble(currentRow.get("Humidity"));
            }
            if (minimumSoFar.get("Humidity").equals("N/A")) {
                lowest = -999;
            } else {
                lowest = Double.parseDouble(minimumSoFar.get("Humidity"));
            }
            if (current < lowest && current != -999) {
                minimumSoFar = currentRow;

            }



        }
        return minimumSoFar;
    }


        public void testHumidityHourInFile () {
            FileResource fr = new FileResource();
            CSVRecord minimum = minimumHumidityInFile(fr.getCSVParser());
            System.out.println("lowest humidity was " + minimum.get("Humidity") +
                    " at " + minimum.get("DateUTC"));

        }

    public CSVRecord lowestHumidityInManyFiles(){

        DirectoryResource dr = new DirectoryResource();
        CSVRecord minimumSoFar = null;
        String filename = null;
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = minimumHumidityInFile(fr.getCSVParser());
            if (minimumSoFar == null) minimumSoFar = currentRow;
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            double lowestHum = Double.parseDouble(minimumSoFar.get("Humidity"));
            if (currentHum < lowestHum) {
                minimumSoFar = currentRow;
                filename = f.getName();
            }
        }
        return minimumSoFar;
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord minimum = lowestHumidityInManyFiles();

        System.out.println("lowest humidity was " + minimum.get("Humidity") +
                " at " + minimum.get("DateUTC"));
    }

        public static void main (String[]args){
            CSVHumidity csvHumidity = new CSVHumidity();
            csvHumidity.testLowestHumidityInManyFiles();
        }

}

