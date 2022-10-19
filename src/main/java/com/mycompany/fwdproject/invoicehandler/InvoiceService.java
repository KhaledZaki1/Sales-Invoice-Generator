/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fwdproject.invoicehandler;

import com.mycompany.fwdproject.model.InvoiceHeader;
import com.mycompany.fwdproject.model.InvoiceLine;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL.SXH11
 */
public class InvoiceService {

    private InvoiceLineHandler invoiceLineHandler;
    private InvoiceHeaderHandler invoiceHeaderHandler;
    
    public InvoiceService(){
             invoiceLineHandler = new InvoiceLineHandler();
             invoiceHeaderHandler = new InvoiceHeaderHandler();
    }
    
    public ArrayList<InvoiceHeader> readInvoiceHeaderFile(String path,JFrame frame) {
        ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
        String headerLinePath  = path.substring(0,path.lastIndexOf("\\")+1)+"InvoiceLine.csv";
        HashMap<String, ArrayList<InvoiceLine>> invoiceLines = loadInvoiceLines(headerLinePath, frame);
        Scanner sc;
        try {
            sc = new Scanner(new File(path));
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                String invoiceHead[] = sc.nextLine().split("\\,");
                InvoiceHeader invoiceHeader  = invoiceHeaderHandler.setInvoiceHeaderObj(invoiceHead, invoiceLines.get(invoiceHead[0]),frame);
                invoiceHeaders.add(invoiceHeader);
            }
            sc.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error, file not found", "Alert", JOptionPane.ERROR_MESSAGE);
          return null;
        }
        return invoiceHeaders;
    }

    private HashMap<String, ArrayList<InvoiceLine>> loadInvoiceLines(String path,JFrame frame) {
        HashMap<String, ArrayList<InvoiceLine>> invoiceLines = new HashMap<>();
        Scanner sc;
        try {
            sc = new Scanner(new File(path));
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                String line[] = sc.nextLine().split("\\,");
                
                ArrayList<InvoiceLine> invoiceLineList = new ArrayList<>();
                if (invoiceLines.get(line[0]) != null) {
                    invoiceLineList = invoiceLines.get(line[0]);
                }
                InvoiceLine invoiceLine = invoiceLineHandler.setInvoiceLineObj(line);
                invoiceLineList.add(invoiceLine);
                invoiceLines.put(line[0], invoiceLineList);
            }
            sc.close();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error, file not found", "Alert", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return invoiceLines;
    }

   

   
}
