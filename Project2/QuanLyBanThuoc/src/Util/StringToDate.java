/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drago
 */
public class StringToDate {

    public Date String2Date(String dob) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dob);
        } catch (ParseException ex) {
            Logger.getLogger(StringToDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
}
