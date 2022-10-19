/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fwdproject.filehandler;

import com.mycompany.fwdproject.model.InvoiceHeader;
import com.mycompany.fwdproject.model.InvoiceLine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL.SXH11
 */
public class FileService {

    public String loadFile(JFileChooser myFileChooser, JFrame frame) {
        int result = myFileChooser.showOpenDialog(frame);
        String filePath = "";
        
        if (result == JFileChooser.APPROVE_OPTION) {
            Path path = myFileChooser.getSelectedFile().toPath();
            System.out.println(path.toString().substring(0, path.toString().lastIndexOf("\\")));
            if (path.toString().endsWith("csv")) {
                filePath = path.toString();
            } else {
                JOptionPane.showMessageDialog(frame, "Error File Extension Selection", "Alert", JOptionPane.ERROR_MESSAGE);
            }
        }
        return filePath;
    }

    public void saveFile(String path, ArrayList<InvoiceHeader> invoiceHeaders) {

        String headerLinePath = path.substring(0, path.lastIndexOf("\\") + 1) + "InvoiceLine.csv";
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        try (PrintWriter writer = new PrintWriter(path)) {

            StringBuilder sb = new StringBuilder();
            for(InvoiceHeader inv: invoiceHeaders){
                sb.append(inv.getId());
                sb.append(",");
                sb.append(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
                sb.append(",");
                sb.append(inv.getCustomerName());
                sb.append("\n");
                invoiceLines.addAll(inv.getInvoiceLines());
            }
            
            saveToLineInv(headerLinePath,invoiceLines);
            writer.write(sb.toString());
          

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private void saveToLineInv(String headerLinePath,ArrayList<InvoiceLine> invoiceLines) {
          
        try (PrintWriter writer = new PrintWriter(headerLinePath)) {

            StringBuilder sb = new StringBuilder();
            for(InvoiceLine inv: invoiceLines){
                sb.append(inv.getId());
                sb.append(",");
                sb.append(inv.getItemName());
                sb.append(",");
                sb.append(inv.getItemPrice());
                sb.append(",");
                sb.append(inv.getItemCount());
                sb.append("\n");
            }
             writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
