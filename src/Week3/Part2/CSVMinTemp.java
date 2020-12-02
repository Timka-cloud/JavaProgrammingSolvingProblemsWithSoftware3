package Week3.Part2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVMinTemp {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord minimumSoFar = null;
        for(CSVRecord currentRow : parser){
            minimumSoFar = getLargestOfTwo(currentRow, minimumSoFar);
        }
        return minimumSoFar;
    }

    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord minimum = coldestHourInFile(parser);
        System.out.println("coldest temp was " + minimum.get("TemperatureF") +
                " at " + minimum.get("DateUTC"));

    }

    public CSVRecord ValueOfColdestTempera (){
        CSVRecord minimumSoFar = null;
        //iterate over files
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);

            // use method to get largest in file
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            minimumSoFar = getLargestOfTwo(currentRow,minimumSoFar);

        }
        return minimumSoFar;
    }

    public void testColdestInManyDays(){
        CSVRecord minimum = ValueOfColdestTempera();
        String t = FileNameWithColdestTempera();
        System.out.println("File name: " + t);
        System.out.println("coldest temp was " + minimum.get("TemperatureF") +
                " at " + minimum.get("DateUTC"));
    }

    public String FileNameWithColdestTempera () {
        CSVRecord coldestSoFar = null;
        String coldestFileName = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) coldestSoFar = current;
            else {
                double currentTem = Double.parseDouble(current.get("TemperatureF"));
                double coldestTem = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTem != -9999 && currentTem < coldestTem) {
                    coldestSoFar = current;
                    coldestFileName = f.getName();
                }
            }
        }
        return coldestFileName;
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord minimumSoFar){
        // if largestSoFar is nothing
        if(minimumSoFar == null){
            minimumSoFar = currentRow;

        }
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double minimumTemp = Double.parseDouble(minimumSoFar.get("TemperatureF"));
            if(currentTemp == -9999){
                currentRow = minimumSoFar;
            }
            else if(currentTemp < minimumTemp){
                minimumSoFar = currentRow;
            }

        }

        return minimumSoFar;
    }


    public static void main(String[] args) {
    CSVMinTemp hw2 = new CSVMinTemp();
    hw2.testColdestInManyDays();
    }
}
