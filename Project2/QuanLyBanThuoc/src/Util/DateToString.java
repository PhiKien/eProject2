/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

/**
 *
 * @author drago
 */
public class DateToString {

    public String Date2String(Date date) {
        String stringDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        stringDate = dateFormat.format(date);
        return stringDate;
    }
}
