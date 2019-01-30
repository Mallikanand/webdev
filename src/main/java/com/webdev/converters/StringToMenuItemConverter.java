/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.converters;

import com.webdev.data.model.MenuItem;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author mkotra
 */
public class StringToMenuItemConverter implements Converter<String,MenuItem> {

    public StringToMenuItemConverter() {
    }

    @Override
    public MenuItem convert(String o) {
        MenuItem retVal = null;
        
        String s = (String)o;
        System.out.println("Attempting to convert the following to MenuITem: " + s);
        
        return retVal;
    }
    
}
