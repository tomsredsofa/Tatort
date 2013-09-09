

<%@ page import="net.codeplumber.tatort.Kommissar" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'kommissar.label', default: 'Kommissar')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${kommissarInstance}">
            <div class="errors">
                <g:renderErrors bean="${kommissarInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${kommissarInstance?.id}" />
                <g:hiddenField name="version" value="${kommissarInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="firstName"><g:message code="kommissar.firstName.label" default="First Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: kommissarInstance, field: 'firstName', 'errors')}">
                                    <g:textField name="firstName" value="${kommissarInstance?.firstName}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastName"><g:message code="kommissar.lastName.label" default="Last Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: kommissarInstance, field: 'lastName', 'errors')}">
                                    <g:textField name="lastName" value="${kommissarInstance?.lastName}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="jobTitle"><g:message code="kommissar.jobTitle.label" default="Job Title" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: kommissarInstance, field: 'jobTitle', 'errors')}">
                                    <g:textField name="jobTitle" value="${kommissarInstance?.jobTitle}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="specialInfo"><g:message code="kommissar.specialInfo.label" default="Special Info" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: kommissarInstance, field: 'specialInfo', 'errors')}">
                                    <g:textArea name="specialInfo" cols="40" rows="5" value="${kommissarInstance?.specialInfo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="actorFirstName"><g:message code="kommissar.actorFirstName.label" default="Actor First Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: kommissarInstance, field: 'actorFirstName', 'errors')}">
                                    <g:textField name="actorFirstName" value="${kommissarInstance?.actorFirstName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="actorLastName"><g:message code="kommissar.actorLastName.label" default="Actor Last Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: kommissarInstance, field: 'actorLastName', 'errors')}">
                                    <g:textField name="actorLastName" value="${kommissarInstance?.actorLastName}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
