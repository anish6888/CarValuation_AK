package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    //    To read the Input file data
    public static List<String> extractRegistrationNumbers(String fileName) {
        List<String> registrationNumbers = new ArrayList<>();

        try {
            File file = new File("src/test/resources/" + fileName);
            List<String> lines = FileUtils.readLines(file, "UTF-8");

            for (String regNum : lines) {
                regNum = regNum.trim();
                if (!regNum.isEmpty()) { // Skip empty lines
                    if (isRegNoPatternMatches(regNum)) { // condition to check if "regNum" matches the pattern
                        registrationNumbers.add((regNum));
                    } else {
                        System.out.println("Invalid registration number format ---> " + regNum);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return registrationNumbers;
    }

//    To read the exepcted Output file data
    public static Map<String, String> readExpectedCarDetails(String fileName) {
        Map<String, String> carDetails = new HashMap<>();
        try {
            File file = new File("src/test/resources/" + fileName);
            List<String> lines = FileUtils.readLines(file, "UTF-8");

            // Parse each line into a map entry
            for (String line : lines) {
                if (!line.trim().isEmpty()) { // Skip empty lines
                    String[] parts = line.split(",", 2); // Split into parts: registration number and car details
                    if (parts.length == 2 && isRegNoPatternMatches(parts[0])) {
                        carDetails.put(parts[0].trim(), parts[1].trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carDetails;
    }

//    Method to check the Registration Number patters matches
    public static boolean isRegNoPatternMatches(String registration) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[0-9]{2} [A-Z]{3}$"); // Example regex for UK reg numbers
        Matcher matcher = pattern.matcher(registration);

        return matcher.matches();

    }
}
