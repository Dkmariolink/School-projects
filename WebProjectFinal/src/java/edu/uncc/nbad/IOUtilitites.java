/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uncc.nbad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Hunter
 */
public class IOUtilitites {
        public static void RemoveMatchingLine(String lineToRemove, String filePath) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = new File(filePath.replace("Products.txt", "") + java.util.UUID.randomUUID() + ".txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        boolean found = false;
        
        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(!trimmedLine.equals("")) {
                if(trimmedLine.equals(lineToRemove) && !found) {
                    found = true;
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));                
            }

        }
        writer.close(); 
        reader.close(); 
        inputFile.delete();
        tempFile.renameTo(inputFile);       
    } 
}
