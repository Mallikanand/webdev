/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.validators;

import com.webdev.binding.OrderBean;
import com.webdev.data.model.MenuItem;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 *
 * @author mkotra
 */
public class OrderDTOValidatorTest {
    
    
    private OrderDTOValidator orderDTOValidator;
    
    @Before public void setup(){
        orderDTOValidator = new OrderDTOValidator();
    }

    @Test(expected = IllegalArgumentException.class) 
    public void test_Illegal_Object_To_Validate(){
        
        Object o = new Object();
        Errors errors = new BindException(o, "orderDTO");
        ValidationUtils.invokeValidator(orderDTOValidator,o ,errors );
    }
    
    @Test
    public void test_Empty_DTO(){
        
        OrderBean o = new OrderBean();
        Errors errors = new BindException(o, "orderDTO");
        ValidationUtils.invokeValidator(orderDTOValidator,o ,errors );
        
        Assert.assertTrue(errors.hasErrors());
        Assert.assertNotNull("Error Handling when Order Items is Empty is not correct", errors.getFieldError("items")!= null );
        Assert.assertTrue("If Items is Empty, the error code must be \"Order_cannot_have_no_items\"", errors.getFieldError("items").getCode().equals("Order_cannot_have_no_items"));
    }
    
    @Test
    public void test_Empty_Items(){
        OrderBean o = new OrderBean();
        
        o.addItem(new MenuItem(1), "Food", -1);
        Errors errors = new BindException(o, "orderDTO");
        
        ValidationUtils.invokeValidator(orderDTOValidator, o, errors);
        
        Assert.assertTrue(errors.hasErrors());
    }
}
