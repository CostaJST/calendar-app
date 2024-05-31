package edu.curtin.calenderApp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import edu.curtin.API.CalenderAPI;



public class CalenderApp {
    public static void main(String[] args) {
        /* Internationalization */
        //Providing a menu option to allow user to select a locale by entering an IETF language tag.
        Locale currentLocale = Locale.getDefault();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Current Locale: " + currentLocale.toLanguageTag());
            System.out.println("1. Select a new locale");
            System.out.println("2. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter an IETF language tag(e.g en-UK): ");
                    String languageTag = scanner.next();

                    try {
                        currentLocale = Locale.forLanguageTag(languageTag);
                        Locale.setDefault(currentLocale);
                        System.out.println("Locale set to: " + currentLocale.toLanguageTag());
                    } catch(IllegalArgumentException e) {
                        System.out.println("Invalid language tag. Please try again.");
                    }
                    break;
                
                case 2:
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

        //Translating the UI texts
        Locale currentLocale = Locale.forLanguageTag("en-US");
        ResourceBundle bundle = ResourceBundle.getBundle("bundle", currentLocale); //getting UI text for default locale en-US.

        //Getting UI text for a diffrent locale si-LK
        Locale siLocale = Locale.forLanguageTag("si-LK");
        ResourceBundle bundle2 = ResourceBundle.getBundle("bundle", siLocale);

        //Display UI text
        //To-do

        //Internationalizing the date, time and numbers
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Sri Lanka/Colombo"));
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(siLocale);
        String dateFmt = dtf.format(zonedDateTime);

        NumberFormat nf = NumberFormat.getInstance();
        String realFmt = nf.format(12345.678);

        //Normalizing the Strings
        String languageTag = "";
        String normalizedString = Normalizer.normalize(languageTag, Normalizer.Form.NFC);

        //Unicode Encodings
        //To-do : write the file path here
        String filename = "";
        try {
            String fileExtension = getFileExtension(filename); 
            String encoding;

            if(fileExtension.equalsIgnoreCase("utf16.cal")) {
                encoding = "UTF-16";
            } else if(fileExtension.equalsIgnoreCase("utf32.cal")) {
                encoding = "UTF-32";
            } else {
                encoding = "UTF-8";
            }

            //Read the file using encodings specified above
            FileInputStream fileInputStream = new FileInputStream(filename);//write the filename here
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, encoding);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

        //Loading plugin classes dynamically 
        String pluginName = "package edu.curtin.calPlugins.Notify";
        try {
            Class<?> NotifyPlugin = Class.forName(pluginName);
            Notify pluginObj = (Notify) NotifyPlugin.getConstructor().newInstance();
            
        } catch(IllegalArgumentException e) {}

    }

    //getting file extension from the filename
    private static String getFileExtension(String filename) {
        if(filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }

}
        