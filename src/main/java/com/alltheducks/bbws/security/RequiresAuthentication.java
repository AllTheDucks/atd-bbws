package com.alltheducks.bbws.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Wiley Fuller on 5/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresAuthentication {
}
