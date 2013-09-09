<html>
    <head>
        <title>Welcome to Toms Tatort Vorschau</title>
        <meta name="layout" content="main" />
        <style type="text/css" media="screen">

        #nav {
            margin-top:20px;
            margin-left:30px;
            width:228px;
            float:left;

        }
        .homePagePanel * {
            margin:0px;
        }
        .homePagePanel .panelBody ul {
            list-style-type:none;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody h1 {
            text-transform:uppercase;
            font-size:1.1em;
            margin-bottom:10px;
        }
        .homePagePanel .panelBody {
            background: url(images/leftnav_midstretch.png) repeat-y top;
            margin:0px;
            padding:15px;
        }
        .homePagePanel .panelBtm {
            background: url(images/leftnav_btm.png) no-repeat top;
            height:20px;
            margin:0px;
        }

        .homePagePanel .panelTop {
            background: url(images/leftnav_top.png) no-repeat top;
            height:11px;
            margin:0px;
        }
        h2 {
            margin-top:15px;
            margin-bottom:15px;
            font-size:1.2em;
        }
        #pageBody {
            margin-left:40px;
            margin-right:20px;
        }
        </style>
    </head>
    <body>
        <div id="pageBody">
            <h1>Welcome to Toms Tatort Vorschau</h1>

            Show <g:link controller="vorschau" action="list">Vorschauen</g:link>  <br />
            Show <g:link controller="tatort" action="list">Tatorte</g:link> <br />
            Show <g:link controller="kommissar" action="list">Kommissare</g:link> <br />
            Show <g:link controller="city" action="list">City</g:link>


            <div class="bottomInfo">
                <hr />
                <br />
                This is App version: <g:meta name="app.version"></g:meta> |
                Grails version: <g:meta name="app.grails.version"></g:meta> |
                Groovy version: ${org.codehaus.groovy.runtime.InvokerHelper.getVersion()} |
                JVM version: ${System.getProperty('java.version')}
            </div>
        </div>
    </body>
</html>
