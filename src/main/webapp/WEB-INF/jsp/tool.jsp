<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/bbUI" prefix="bbUI"%>
<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="bbng" uri="/bbNG" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:message var="bbwsMessage" key="bbws-app.bbwsPage.message" />


<bbNG:learningSystemPage ctxId="ctx" navItem="atd-bbws-app-nav-bbws">
    ${bbwsMessage}
</bbNG:learningSystemPage>
