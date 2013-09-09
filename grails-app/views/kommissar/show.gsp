
<%@ page import="net.codeplumber.tatort.Kommissar" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'kommissar.label', default: 'Kommissar')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="kommissar.id.label" default="Id" /></td>
                            <td valign="top" class="value">${fieldValue(bean: kommissarInstance, field: "id")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="kommissar.firstName.label" default="First Name" /></td>
                            <td valign="top" class="value">${fieldValue(bean: kommissarInstance, field: "firstName")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="kommissar.lastName.label" default="Last Name" /></td>
                            <td valign="top" class="value">${fieldValue(bean: kommissarInstance, field: "lastName")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="kommissar.jobTitle.label" default="Job Title" /></td>
                            <td valign="top" class="value">${fieldValue(bean: kommissarInstance, field: "jobTitle")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="kommissar.specialInfo.label" default="Special Info" /></td>
                            <td valign="top" class="value">${fieldValue(bean: kommissarInstance, field: "specialInfo")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="kommissar.actorFirstName.label" default="Actor First Name" /></td>
                            <td valign="top" class="value">${fieldValue(bean: kommissarInstance, field: "actorFirstName")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="kommissar.actorLastName.label" default="Actor Last Name" /></td>
                            <td valign="top" class="value">${fieldValue(bean: kommissarInstance, field: "actorLastName")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${kommissarInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
