package org.softuni.workshopspringadvanced.registration.model;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch,Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;


    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(o);
        Object first = wrapper.getPropertyValue(firstFieldName);
        Object second = wrapper.getPropertyValue(secondFieldName);
        boolean valid =
                (first == null && second == null) ||
                        (first!=null && first.equals(second));
        if(!valid){
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addPropertyNode(secondFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return false;
    }
}
