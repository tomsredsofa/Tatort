
<%@ page import="net.codeplumber.tatort.Vorschau" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vorschau.label', default: 'Vorschau')}" />
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
                            <td valign="top" class="name"><g:message code="vorschau.id.label" default="Id" /></td>
                            <td valign="top" class="value">${fieldValue(bean: vorschauInstance, field: "id")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vorschau.downloadAction.label" default="Download Action" /></td>
                            <td valign="top" class="value">${vorschauInstance?.downloadAction?.encodeAsHTML()}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vorschau.firstSendingDate.label" default="First Sending Date" /></td>
                            <td valign="top" class="value"><g:formatDate format="yyyy-MM-dd" date="${vorschauInstance?.firstSendingDate}" /></td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vorschau.tatort.label" default="Tatort" /></td>
                            <td valign="top" class="value"><g:link controller="tatort" action="show" id="${vorschauInstance?.tatort?.id}">${vorschauInstance?.tatort?.encodeAsHTML()}</g:link></td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vorschau.title.label" default="Title" /></td>
                            <td valign="top" class="value">${fieldValue(bean: vorschauInstance, field: "title")}</td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vorschau.sendingDate.label" default="Sending Date" /></td>
                            <td valign="top" class="value"><g:formatDate format="yyyy-MM-dd HH:mm" date="${vorschauInstance?.sendingDate}" /></td>
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="vorschau.channel.label" default="Channel" /></td>
                            <td valign="top" class="value">${fieldValue(bean: vorschauInstance, field: "channel")}</td>
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${vorschauInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
