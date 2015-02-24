package com.alltheducks.bbws.stripes;

import blackboard.platform.plugin.PlugInUtil;
import com.alltheducks.bbws.config.Configuration;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import com.alltheducks.configutils.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;

import java.io.File;


public class ConfigAction implements ActionBean {

    private ActionBeanContext context;

    @ValidateNestedProperties({@Validate(field = "settingOne", required = true),
            @Validate(field = "settingTwo", required = true, minvalue = 0, maxvalue = 100)})
    private Configuration config;

    @SpringBean
    private ConfigurationService<Configuration> configService;

    @Before(stages = LifecycleStage.BindingAndValidation)
    public void loadConfiguration() {
        config = configService.loadConfiguration();
        if (config == null) {
            config = new Configuration();
        }

    }


    @DefaultHandler
    @DontValidate
    public Resolution displayConfigPage() {
        return new ForwardResolution("/WEB-INF/jsp/config.jsp");
    }

    public Resolution saveConfiguration() {
        configService.persistConfiguration(config);
        return new RedirectResolution(PlugInUtil.getPlugInManagerURL(), false);
    }

    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
}
