/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webdev.validators;

import com.webdev.binding.OrderBean;
import com.webdev.binding.OrderItemBean;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author mkotra
 */

class OrderDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderBean.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderBean orderBean = (OrderBean) target;
        ValidationUtils.rejectIfEmpty(errors, "items" , "Order_cannot_have_no_items");
        
        if(orderBean.getItems()!=null){
            orderBean.getItems().stream()
                .filter(Objects::nonNull)
                .filter(i -> i.getQuantity()< 1)
                .peek(i -> errors.rejectValue("item.quantity", "Item Quantity must be atleast 1"));
            
        }
    }
}
