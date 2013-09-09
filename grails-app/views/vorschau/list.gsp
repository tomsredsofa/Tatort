
<%@ page import="net.codeplumber.tatort.DownloadAction; net.codeplumber.tatort.Vorschau" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'vorschau.label', default: 'Vorschau')}" />
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

            <g:link action="importVorschau">Import new stuff</g:link>
            <g:if test="${'listPage' == pageToShow}">
                <br /><g:link action="showDownload">Show Vorschauen to download</g:link>
            </g:if>
            <g:elseif test = "${'downloadPage' == pageToShow}">
                <br /><g:link action="list">Show all Vorschauen</g:link>
            </g:elseif>

            <g:form controller="vorschau" action="deleteOldVorschau"  >
                <label>Delete old Vorschauen: To (yyyy-MM-dd)</label>
                <input id="deleteDate" type="text" name="deleteDate" />
                <input type="submit" value="Go" />
            </g:form>

            <div class="list">
                <table>
                    <thead>
                        <tr>
                            <g:sortableColumn property="id" title="${message(code: 'vorschau.id.label', default: 'Id')}" />
                            <g:sortableColumn property="downloadAction" title="${message(code: 'vorschau.downloadAction.label', default: 'Download Action')}" />
                            <g:sortableColumn property="firstSendingDate" title="${message(code: 'vorschau.firstSendingDate.label', default: 'First Sending Date')}" />
                            <th><g:message code="vorschau.tatort.label" default="Tatort" /></th>
                            <g:sortableColumn property="title" title="${message(code: 'vorschau.title.label', default: 'Title')}" />
                            <g:sortableColumn property="sendingDate" title="${message(code: 'vorschau.sendingDate.label', default: 'Sending Date')}" />
                            <g:sortableColumn property="channel" title="${message(code: 'vorschau.channel.label', default: 'Channel')}" />
                            <g:if test="${'downloadPage' == pageToShow}">
                                <th><g:message code="vorschau.fileName.label" default="File Name"/></th>
                            </g:if>
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${vorschauInstanceList}" status="i" var="vorschauInstance">
                        %{--<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">--}%
                        <tr class="${vorschauInstance.downloadAction == DownloadAction.shouldBeDownloaded ? 'download' : 'nodownload'}">
                            <td><g:link action="show" id="${vorschauInstance.id}">${fieldValue(bean: vorschauInstance, field: "id")}</g:link></td>
                            <td>${fieldValue(bean: vorschauInstance, field: "downloadAction")}</td>
                            <td><g:formatDate format="yyyy-MM-dd" date="${vorschauInstance.firstSendingDate}" /></td>
                            <td>${fieldValue(bean: vorschauInstance, field: "tatort")}</td>
                            <td>${fieldValue(bean: vorschauInstance, field: "title")}</td>
                            <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${vorschauInstance.sendingDate}" /></td>
                            <td>${fieldValue(bean: vorschauInstance, field: "channel")}</td>
                            <g:if test="${'downloadPage' == pageToShow}">
                                <td>${vorschauInstance.createFileName()}</td>
                            </g:if>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${vorschauInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
