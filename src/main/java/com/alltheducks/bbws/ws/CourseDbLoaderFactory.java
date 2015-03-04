package com.alltheducks.bbws.ws;

import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import org.glassfish.hk2.api.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wiley Fuller on 4/03/15.
 * Copyright All the Ducks Pty. Ltd.
 */
public class CourseDbLoaderFactory implements Factory<CourseDbLoader> {

    @Override
    public CourseDbLoader provide() {
        CourseDbLoader cdl;
        try {
            cdl = CourseDbLoader.Default.getInstance();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        return cdl;
    }

    @Override
    public void dispose(CourseDbLoader instance) {

    }
}
