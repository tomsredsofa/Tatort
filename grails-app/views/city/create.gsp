<%@ page import="net.codeplumber.tatort.City" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'city.label', default: 'City')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${cityInstance}">
        <div class="errors">
            <g:renderErrors bean="${cityInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="city.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: cityInstance, field: 'name', 'errors')}">
                        <g:textField name="name" value="${cityInstance?.name}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="country"><g:message code="city.country.label" default="Country"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: cityInstance, field: 'country', 'errors')}">
                        <g:textField name="country" value="${cityInstance?.country}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="shortName"><g:message code="city.shortName.label" default="Short Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: cityInstance, field: 'shortName', 'errors')}">
                        <g:textField name="shortName" value="${cityInstance?.shortName}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save"
                                                 value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
