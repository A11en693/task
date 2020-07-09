package com.yan.utils.taskenum.convert;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InjectField {
    String[] processBeanName();

    String fieldName() default "";
}
