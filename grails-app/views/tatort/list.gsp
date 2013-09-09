
<%@ page import="net.codeplumber.tatort.MovieStatus; net.codeplumber.tatort.Tatort" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'tatort.label', default: 'Tatort')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="searchBox" >
            <g:link action="importTatort">Import wikipedia stuff</g:link>
            <g:form controller="tatort" action="searchTatorte" >
                <label>Search:</label>
                <input id="query" type="text" name="query" />
                <input type=submit value="Go" />
            </g:form>
            MovieStatus values are:
            <g:each var="movieStatus" in="${MovieStatus.values()}">
                <g:link action="searchTatorte" params="${[query: movieStatus]}">${movieStatus}</g:link> |
            </g:each>
            <g:form controller="tatort" action="searchTatorte"  >
                <label>Search by WikipediaId: From </label>
                <input id="wikiStart" type="text" name="wikiStart" />
                <label> to </label>
                <input id="wikiEnd" type="text" name="wikiEnd" />
                <input type="submit" value="Go" />
            </g:form>

        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:render template="tatortList" model="${['tatorte' : tatortInstanceList]}" />
            <div class="paginateButtons">
                <g:paginate total="${tatortInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
