<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/bbUI" prefix="bbUI" %>
<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="bbng" uri="/bbNG" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message var="toolSettingsStepTitle" key="bbws-app.configPage.toolSettingsStep.title" />
<fmt:message var="usernameSettingLabel" key="bbws-app.configPage.usernameSetting.label" />
<fmt:message var="passwordSettingLabel" key="bbws-app.configPage.passwordSetting.label" />

<bbNG:genericPage bodyClass="normalBackground"
                  navItem="atd-bbws-app-nav-bbwsconfig">

    <style type="text/css">
        span.fieldErrorText {
            margin-left: 1em;
            color: red;
        }
    </style>
    <stripes:form beanclass="com.alltheducks.bbws.stripes.ConfigAction">
        <stripes:hidden name="saveConfiguration"/>

        <bbNG:dataCollection>
            <bbNG:step title="${toolSettingsStepTitle}">
                <bbNG:dataElement isRequired="true" label="${usernameSettingLabel}">
                    <stripes:text name="config.username"></stripes:text>
                    <stripes:errors field="config.username"></stripes:errors>
                </bbNG:dataElement>
                <bbNG:dataElement isRequired="true" label="${passwordSettingLabel}">
                    <stripes:text name="config.password"></stripes:text>
                    <stripes:errors field="config.password"></stripes:errors>
                </bbNG:dataElement>
            </bbNG:step>
            <bbNG:stepSubmit></bbNG:stepSubmit>
        </bbNG:dataCollection>
    </stripes:form>

</bbNG:genericPage>
