package edu.myinst.stripes;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;


public class ConfigAction implements ActionBean {

    private ActionBeanContext context;
    	
	public ActionBeanContext getContext() {
        return context;
    }

    @DefaultHandler
    public Resolution displayConfigPage() {
        return new ForwardResolution("/WEB-INF/jsp/config.jsp");
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }
}