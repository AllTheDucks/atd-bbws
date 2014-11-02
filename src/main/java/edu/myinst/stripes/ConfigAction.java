package org.oscelot.jshack.stripes;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;


public class ConfigAction implements ActionBean {

    private ActionBeanContext context;
    	
	public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }
}