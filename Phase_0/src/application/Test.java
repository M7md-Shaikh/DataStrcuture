package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test {
	
	static Martyr [] martyrArr =new Martyr[10];
	static int n=0;
	private static void readCSVFile() {
		
		File file = new File("C:\\Users\\mhmds\\Desktop\\M7md\\data.csv");
		  try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		        String line;
		        boolean firstLine = true;

		        while ((line = br.readLine()) != null) {
		            if (firstLine) {
		                firstLine = false;
		                continue;
		            }

		            String[] parts = line.split(",");
		            if (parts.length == 5) {
		                String name = parts[0];
		                int age;
		                if (parts[1].isEmpty()) {
		                    age = 0;  
		                } else {
		                age = Integer.parseInt(parts[1]);
		                }
		                String eventLocation = parts[2];
		                String date = parts[3];
		                String gender = parts[4];

		                Martyr martyr = new Martyr(name, age, eventLocation, date, gender);
		                martyrArr[n++] = martyr;

		                
		                
		            } else {
		                System.out.println("Invalid line format: " + line);
		            }
		        }
		    } catch (FileNotFoundException e) {
		        System.out.println(e.getMessage());
		    } catch (NumberFormatException e) {
		        System.out.println( e.getMessage());
		    } catch (IOException e) {
		    	System.out.println(e.getMessage());
			}
		}
	public static void main(String[] args) {
		readCSVFile();
	}
}
