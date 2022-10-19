/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fwdproject.invoicehandler;

import com.mycompany.fwdproject.model.InvoiceLine;

public class InvoiceLineHandler {
        public InvoiceLine setInvoiceLineObj(String line[]) {
        InvoiceLine invoiceLine = new InvoiceLine();
        
        invoiceLine.setId(line[0]);
        invoiceLine.setItemName(line[1]);
        invoiceLine.setItemPrice(Integer.parseInt(line[2]));
        invoiceLine.setItemCount(Integer.parseInt(line[3]));
        invoiceLine.setTotalPrice(invoiceLine.getItemCount() * invoiceLine.getItemPrice());
        return invoiceLine;
    }
}
