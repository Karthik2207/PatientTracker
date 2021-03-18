package com.tracker.patienttracker.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintTarget;
import javax.validation.Payload;
import javax.validation.constraintvalidation.ValidationTarget;

import org.hibernate.tool.schema.TargetType;

import ch.qos.logback.classic.db.names.TableName;

@Documented
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConstraintValidator.class)
public @interface ValidPassword {

    String message() default "Invalid Password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}