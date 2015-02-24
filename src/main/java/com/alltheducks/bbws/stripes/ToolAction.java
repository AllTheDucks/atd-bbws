package com.alltheducks.bbws.stripes;

import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;


public class ToolAction implements ActionBean {

    private ActionBeanContext context;
    	
	public ActionBeanContext getContext() {
        return context;
    }

    @DefaultHandler
    public Resolution displayPage() {
        return new ForwardResolution("/WEB-INF/jsp/tool.jsp");
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }
}
