/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fwdproject.invoicehandler;

import com.mycompany.fwdproject.model.InvoiceHeader;
import com.mycompany.fwdproject.model.InvoiceLine;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InvoiceHeaderHandler {

    public InvoiceHeader setInvoiceHeaderObj(String invoiceHead[], ArrayList<InvoiceLine> invoiceLine,JFrame frame) {
        InvoiceHeader invoiceHeader = new InvoiceHeader();
        invoiceHeader.setId(invoiceHead[0]);
        SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
        try {
            invoiceHeader.setDate(dd.parse(invoiceHead[1]));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error, Date format", "Alert", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        invoiceHeader.setCustomerName(invoiceHead[2]);
        invoiceHeader.setInvoiceLines(invoiceLine);
        invoiceHeader.setTotalPrice(invoiceLine.stream().mapToDouble(i -> i.getTotalPrice()).sum());
        return invoiceHeader;
    }

}
