

<%@ page import="net.codeplumber.tatort.TatortUtils; net.codeplumber.tatort.Vorschau" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vorschau.label', default: 'Vorschau')}" />
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
            <g:hasErrors bean="${vorschauInstance}">
            <div class="errors">
                <g:renderErrors bean="${vorschauInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${vorschauInstance?.id}" />
                <g:hiddenField name="version" value="${vorschauInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="downloadAction"><g:message code="vorschau.downloadAction.label" default="Download Action" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vorschauInstance, field: 'downloadAction', 'errors')}">
                                    <g:select name="downloadAction" from="${net.codeplumber.tatort.DownloadAction?.values()}" keys="${net.codeplumber.tatort.DownloadAction?.values()*.name()}" value="${vorschauInstance?.downloadAction?.name()}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="firstSendingDate"><g:message code="vorschau.firstSendingDate.label" default="First Sending Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vorschauInstance, field: 'firstSendingDate', 'errors')}">
                                    <g:datePicker name="firstSendingDate" precision="day" value="${vorschauInstance?.firstSendingDate}"  years="${TatortUtils.createFSDRange()}"/>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tatort"><g:message code="vorschau.tatort.label" default="Tatort" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vorschauInstance, field: 'tatort', 'errors')}">
                                    <g:select name="tatort.id" from="${net.codeplumber.tatort.Tatort.list([sort:'wikipediaId'])}" optionKey="id" value="${vorschauInstance?.tatort?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="title"><g:message code="vorschau.title.label" default="Title" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vorschauInstance, field: 'title', 'errors')}">
                                    <g:textField name="title" value="${vorschauInstance?.title}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="sendingDate"><g:message code="vorschau.sendingDate.label" default="Sending Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vorschauInstance, field: 'sendingDate', 'errors')}">
                                    <g:datePicker name="sendingDate" precision="minute" value="${vorschauInstance?.sendingDate}" years="${TatortUtils.createVorschauRange()}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="channel"><g:message code="vorschau.channel.label" default="Channel" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: vorschauInstance, field: 'channel', 'errors')}">
                                    <g:textField name="channel" value="${vorschauInstance?.channel}" />
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
