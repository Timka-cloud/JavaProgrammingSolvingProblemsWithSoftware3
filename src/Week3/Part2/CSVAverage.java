package Week3.Part2;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVAverage {
    public double averageTemperatureInFile(CSVParser parser){
        double average = 0.0;
        int count = 0;
        double current = 0.0;
        double all = 0.0;
        for(CSVRecord record : parser){
           current = Double.parseDouble(record.get("TemperatureF"));
           all += current;
           count++;
        }
        return all / count;
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avg = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("The average is " + avg);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int val){
        double all = 0;
        double current = 0.0;
        double number = 0;
        double humidity = 0;
        for (CSVRecord record: parser) {
            if (record.get("Humidity").equals("N/A")) humidity = -999;
            else humidity = Double.parseDouble(record.get("Humidity"));
            if (humidity >= val) {
                number++;
                current = Double.parseDouble(record.get("TemperatureF"));
                all += current;
            }
        }

        return all / number;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();

        double average = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (average == 0) System.out.println("No temperatures with that humidity");
        else {
            System.out.print("Average temperature with high Humidity is ");
            System.out.println(average);
        }
    }

    public static void main(String[] args) {
        CSVAverage csvAverage = new CSVAverage();


        csvAverage.testAverageTemperatureWithHighHumidityInFile();
    }
}

