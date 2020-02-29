package com.forever17.project.charityquest.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * check login status Annotation
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 29 Feb 2020
 * @since 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface LoginCheck {

}
