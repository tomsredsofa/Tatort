
<%@ page import="net.codeplumber.tatort.Kommissar" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'kommissar.label', default: 'Kommissar')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'kommissar.id.label', default: 'Id')}" />
                            <g:sortableColumn property="firstName" title="${message(code: 'kommissar.firstName.label', default: 'First Name')}" />
                            <g:sortableColumn property="lastName" title="${message(code: 'kommissar.lastName.label', default: 'First Name')}" />
                            <g:sortableColumn property="jobTitle" title="${message(code: 'kommissar.jobTitle.label', default: 'Job Title')}" />
                            <g:sortableColumn property="specialInfo" title="${message(code: 'kommissar.specialInfo.label', default: 'Special Info')}" />
                            <g:sortableColumn property="actorFirstName" title="${message(code: 'kommissar.actorFirstName.label', default: 'Actor First Name')}" />
                            <g:sortableColumn property="actorLastName" title="${message(code: 'kommissar.actorLastName.label', default: 'Actor Last Name')}" />
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${kommissarInstanceList}" status="i" var="kommissarInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                            <td><g:link action="show" id="${kommissarInstance.id}">${fieldValue(bean: kommissarInstance, field: "id")}</g:link></td>
                            <td>${fieldValue(bean: kommissarInstance, field: "firstName")}</td>
                            <td>${fieldValue(bean: kommissarInstance, field: "lastName")}</td>
                            <td>${fieldValue(bean: kommissarInstance, field: "jobTitle")}</td>
                            <td>${fieldValue(bean: kommissarInstance, field: "specialInfo")}</td>
                            <td>${fieldValue(bean: kommissarInstance, field: "actorFirstName")}</td>
                            <td>${fieldValue(bean: kommissarInstance, field: "actorLastName")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${kommissarInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
