package org.softuni.workshopspringadvanced.registration.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

    String message() default "The two fields do not match";
    String first();
    String second();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
