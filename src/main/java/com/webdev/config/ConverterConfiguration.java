/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import com.webdev.converters.MenuItemsToOrderDTOConverter;
import com.webdev.converters.OrderDTOToOrderConverter;

/**
 *
 * @author mkotra
 */

@Configuration
public class ConverterConfiguration {
    
    @Bean(name = "conversionService")
    public ConversionService getConversionService(){
        ConversionServiceFactoryBean converterFactory = new ConversionServiceFactoryBean();
        converterFactory.setConverters(getConverters());
        converterFactory.afterPropertiesSet();
        return converterFactory.getObject();
    }

    private Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<>();
        //converters.add(new StringToMenuItemConverter());
        converters.add(new MenuItemsToOrderDTOConverter());
        converters.add(new OrderDTOToOrderConverter());
        
        return converters;
    }
}
