package com.aripd.util.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NotNull
@Size(min = 6, max = 20)
//@Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})")
//@Pattern.List({
//    @Pattern(regexp = "(?=.*[0-9])", message = "Password must contain one digit.")
//    ,
//    @Pattern(regexp = "(?=.*[a-z])", message = "Password must contain one lowercase letter.")
//    ,
//    @Pattern(regexp = "(?=.*[A-Z])", message = "Password must contain one uppercase letter.")
//    ,
//    @Pattern(regexp = "(?=.*[@#$%])", message = "Password must contains one special symbols in the list \"@#$%\".")
//    ,
//    @Pattern(regexp = "(?=\\S+$)", message = "Password must contain no whitespace.")
//})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
@ReportAsSingleViolation
public @interface Password {

    String message() default "{com.aripd.util.validator.Password.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
