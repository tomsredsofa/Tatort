<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'tatort.label', default: 'Tatort')}" />
    <title><g:message code="default.search.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav" >
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
</div>
<div class="searchBox" >
    <g:form controller="tatort" action="searchTatorte" >
        <label>Search:</label>
        <input id="query" type="text" name="query" value="${params?.query}"/>
        <input type=submit value="Go" />
    </g:form>
</div>
<div class="body" >
    <h1>Search Results</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <ul>
        <g:if test="${tatorte}" >
            <g:render template="tatortList" model="${['tatorte' : tatorte]}" />
            <div class="paginateButtons">
                <g:paginate total="${tatorteSize}" params="${params}"/>
            </div>
        </g:if>
        <g:else>
            <h3>No Matching Results Found</h3>
            %{--<%@ page import="grails.plugin.searchable.internal.util.StringQueryUtils" %>--}%

%{--<p>Did you mean --}%
%{--<g:link controller="tatort" action="searchTatorte" --}%
        %{--params="[q: tatorte.suggestedQuery]"> --}%
    %{--${grails.plugin.searchable.internal.util.StringQueryUtils.highlightTermDiffs(params.q.trim(), tatorte.suggestedQuery)} --}%
%{--</g:link>? </p>--}%


        </g:else>
    </ul>
</div>
</body>
</html>