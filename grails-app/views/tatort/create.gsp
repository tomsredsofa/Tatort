

<%@ page import="net.codeplumber.tatort.TatortUtils; net.codeplumber.tatort.Tatort" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tatort.label', default: 'Tatort')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tatortInstance}">
            <div class="errors">
                <g:renderErrors bean="${tatortInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="wikipediaId"><g:message code="tatort.wikipediaId.label" default="Wikipedia Id" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tatortInstance, field: 'wikipediaId', 'errors')}">
                                <g:textField name="wikipediaId" value="${tatortInstance?.wikipediaId}" />
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="city"><g:message code="tatort.city.label" default="City" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tatortInstance, field: 'city', 'errors')}">
                                <g:select name="city.id" from="${net.codeplumber.tatort.City.list(sort: 'shortName')}" optionKey="id" value="${tatortInstance?.city?.id}"  />
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="firstSendingDate"><g:message code="tatort.firstSendingDate.label" default="First Sending Date" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tatortInstance, field: 'firstSendingDate', 'errors')}">
                                <g:datePicker name="firstSendingDate" precision="day" value="${tatortInstance?.firstSendingDate}" years="${TatortUtils.createFSDRange()}" />
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="kommissars"><g:message code="tatort.kommissars.label" default="Kommissars" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tatortInstance, field: 'kommissars', 'errors')}">
                                <g:select name="kommissars" from="${net.codeplumber.tatort.Kommissar.list(sort: 'lastName')}" multiple="yes" optionKey="id" size="5" value="${tatortInstance?.kommissars*.id}" />
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="fallNr"><g:message code="tatort.fallNr.label" default="Fall Nr" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tatortInstance, field: 'fallNr', 'errors')}">
                                <g:textField name="fallNr" value="${fieldValue(bean: tatortInstance, field: 'fallNr')}" />
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="title"><g:message code="tatort.title.label" default="Title" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tatortInstance, field: 'title', 'errors')}">
                                <g:textField name="title" value="${tatortInstance?.title}" />
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="movieStatus"><g:message code="tatort.movieStatus.label" default="Movie Status" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tatortInstance, field: 'movieStatus', 'errors')}">
                                <g:select name="movieStatus" from="${net.codeplumber.tatort.MovieStatus?.values()}" keys="${net.codeplumber.tatort.MovieStatus?.values()*.name()}" value="${tatortInstance?.movieStatus?.name()}"  />
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="description"><g:message code="tatort.description.label" default="Description" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tatortInstance, field: 'description', 'errors')}">
                                <g:textArea name="description" value="${tatortInstance?.description}" rows="3" cols="60"/>
                            </td>
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name">
                                <label for="specialInfo"><g:message code="tatort.specialInfo.label" default="Special Info" /></label>
                            </td>
                            <td valign="top" class="value ${hasErrors(bean: tatortInstance, field: 'specialInfo', 'errors')}">
                                <g:textArea name="specialInfo" value="${tatortInstance?.specialInfo}" rows="3" cols="60" />
                            </td>
                        </tr>

                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
