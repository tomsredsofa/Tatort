
<%@ page import="net.codeplumber.tatort.Tatort" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tatort.label', default: 'Tatort')}" />
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
                            <td valign="top" class="name"><g:message code="tatort.id.label" default="Id" /></td>
                            <td valign="top" class="value">${fieldValue(bean: tatortInstance, field: "id")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tatort.wikipediaId.label" default="Wikipedia Id" /></td>
                            <td valign="top" class="value">${fieldValue(bean: tatortInstance, field: "wikipediaId")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tatort.city.label" default="City" /></td>
                            <td valign="top" class="value"><g:link controller="city" action="show" id="${tatortInstance?.city?.id}">${tatortInstance?.city?.encodeAsHTML()}</g:link></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tatort.firstSendingDate.label" default="First Sending Date" /></td>
                            <td valign="top" class="value"><g:formatDate format="yyyy-MM-dd" date="${tatortInstance?.firstSendingDate}" /></td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tatort.kommissars.label" default="Kommissars" /></td>
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                    <g:each in="${tatortInstance.kommissars}" var="k">
                                        <li><g:link controller="kommissar" action="show" id="${k.id}">${k?.encodeAsHTML()}</g:link></li>
                                    </g:each>
                                </ul>
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tatort.fallNr.label" default="Fall Nr" /></td>
                            <td valign="top" class="value">${fieldValue(bean: tatortInstance, field: "fallNr")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tatort.title.label" default="Title" /></td>
                            <td valign="top" class="value">${fieldValue(bean: tatortInstance, field: "title")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tatort.movieStatus.label" default="Movie Status" /></td>
                            <td valign="top" class="value">${tatortInstance?.movieStatus?.encodeAsHTML()}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tatort.description.label" default="Description" /></td>
                            <td valign="top" class="value">${fieldValue(bean: tatortInstance, field: "description")}</td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="tatort.specialInfo.label" default="Special Info" /></td>
                            <td valign="top" class="value">${fieldValue(bean: tatortInstance, field: "specialInfo")}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${tatortInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
