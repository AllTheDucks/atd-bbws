<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="bbng" uri="/bbNG" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message var="toolSettingsStepTitle" key="myapp.configPage.toolSettingsStep.title" />
<fmt:message var="settingOneLabel" key="myapp.configPage.settingOne.label" />
<fmt:message var="settingTwoLabel" key="myapp.configPage.settingTwo.label" />

<bbNG:genericPage bodyClass="normalBackground"
                  navItem="myu-myapp-nav-helloworldconfig">
    <stripes:form beanclass="edu.myinst.stripes.ConfigAction">
        <stripes:hidden name="saveConfiguration"/>
        <bbNG:dataCollection>
            <bbNG:step title="${toolSettingsStepTitle}">
                <bbNG:dataElement isRequired="true" label="${settingOneLabel}">
                    <stripes:text name="config.settingOne"></stripes:text>
                </bbNG:dataElement>
                <bbNG:dataElement isRequired="true" label="${settingTwoLabel}">
                    <stripes:text name="config.settingTwo"></stripes:text>
                </bbNG:dataElement>
            </bbNG:step>
            <bbNG:stepSubmit></bbNG:stepSubmit>
        </bbNG:dataCollection>
    </stripes:form>

</bbNG:genericPage>