<%@ page import="net.codeplumber.tatort.MovieStatus" %>
<div class="list">
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="id" title="${message(code: 'tatort.id.label', default: 'Id')}" params="${params}" />
            <g:sortableColumn property="wikipediaId" title="${message(code: 'tatort.wikipediaId.label', default: 'WP Id')}" params="${params}" />
            <g:sortableColumn property="firstSendingDate" title="${message(code: 'tatort.firstSendingDate.label', default: 'First Sending Date')}" params="${params}" />
            <th><g:message code="tatort.city.label" default="City" /></th>
            <th><g:message code="tatort.kommissar.label" default="Kommissar" /></th>
            <g:sortableColumn property="fallNr" title="${message(code: 'tatort.fallNr.label', default: 'Fall')}" params="${params}" />
            <g:sortableColumn property="title" title="${message(code: 'tatort.title.label', default: 'Title')}" params="${params}" />
            <g:sortableColumn property="movieStatus" title="${message(code: 'tatort.movieStatus.label', default: 'Movie Status')}" params="${params}" />
            <g:sortableColumn property="description" title="${message(code: 'tatort.description.label', default: 'Description')}" params="${params}" />
            <g:sortableColumn property="specialInfo" title="${message(code: 'tatort.specialInfo.label', default: 'Special Info')}" params="${params}" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${tatorte}" status="i" var="tatortInstance">
            %{--<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">--}%
            <tr class="${tatortInstance.movieStatus.name()}">
                <td><g:link action="show" id="${tatortInstance.id}">${fieldValue(bean: tatortInstance, field: "id")}</g:link></td>
                <td>${fieldValue(bean: tatortInstance, field: "wikipediaId")}</td>
                <td><g:formatDate format="yyyy-MM-dd" date="${tatortInstance.firstSendingDate}" /></td>
                <td>${fieldValue(bean: tatortInstance, field: "city")}</td>
                <td>${fieldValue(bean: tatortInstance, field: "kommissars")}</td>
                <td>${fieldValue(bean: tatortInstance, field: "fallNr")}</td>
                <td>${fieldValue(bean: tatortInstance, field: "title")}</td>
                <td>${fieldValue(bean: tatortInstance, field: "movieStatus")}</td>
                <td>${fieldValue(bean: tatortInstance, field: "description")}</td>
                <td>${fieldValue(bean: tatortInstance, field: "specialInfo")}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>