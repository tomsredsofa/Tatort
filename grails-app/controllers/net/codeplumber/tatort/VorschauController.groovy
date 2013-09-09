package net.codeplumber.tatort

//import groovyx.net.http.HTTPBuilder
//import groovyx.net.http.Method
//import static groovyx.net.http.ContentType.TEXT

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

class VorschauController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def vorschauService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        if (!params.sort) {
            params.sort = "sendingDate"
        }
        [vorschauInstanceList: Vorschau.list(params), vorschauInstanceTotal: Vorschau.count(), pageToShow: "listPage"]
    }

    def create = {
        def vorschauInstance = new Vorschau()
        vorschauInstance.properties = params
        return [vorschauInstance: vorschauInstance]
    }

    def save = {
        def vorschauInstance = new Vorschau(params)
        if (vorschauInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), vorschauInstance.id])}"
            redirect(action: "show", id: vorschauInstance.id)
        }
        else {
            render(view: "create", model: [vorschauInstance: vorschauInstance])
        }
    }

    def show = {
        def vorschauInstance = Vorschau.get(params.id)
        if (!vorschauInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), params.id])}"
            redirect(action: "list")
        }
        else {
            [vorschauInstance: vorschauInstance]
        }
    }

    def edit = {
        def vorschauInstance = Vorschau.get(params.id)
        if (!vorschauInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [vorschauInstance: vorschauInstance]
        }
    }

    def update = {
        def vorschauInstance = Vorschau.get(params.id)
        if (vorschauInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (vorschauInstance.version > version) {
                    
                    vorschauInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'vorschau.label', default: 'Vorschau')] as Object[], "Another user has updated this Vorschau while you were editing")
                    render(view: "edit", model: [vorschauInstance: vorschauInstance])
                    return
                }
            }
            vorschauInstance.properties = params
            if (!vorschauInstance.hasErrors() && vorschauInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), vorschauInstance.id])}"
                redirect(action: "show", id: vorschauInstance.id)
            }
            else {
                render(view: "edit", model: [vorschauInstance: vorschauInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def vorschauInstance = Vorschau.get(params.id)
        if (vorschauInstance) {
            try {
                vorschauInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), params.id])}"
            redirect(action: "list")
        }
    }

//    @Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.1' )
    @Grab(group='org.apache.httpcomponents', module='httpclient', version='4.0.3')
    def importVorschau = {
//        def http = new groovyx.net.http.HTTPBuilder("http://www.daserste.de/tatort/vorschau.asp")
//
//        def String htmlPage
//
//        http.request(groovyx.net.http.Method.GET, groovyx.net.http.ContentType.TEXT) {req ->
//            headers.'User-Agent' = 'Mozilla/5.0'
//
//            response.success = {resp, reader ->
//                 htmlPage = reader.text
//            }
//        }

        String responseBody = ""
        HttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet("http://www.daserste.de/unterhaltung/krimi/tatort/vorschau/index.html");

            System.out.println("executing request " + httpget.getURI());

            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
            System.out.println("----------------------------------------");

        } finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }


        def String vorschauResult = vorschauService.extractVorschauen(responseBody)
        def resultParts = vorschauResult.split(":")
        flash.message = "${message(code: 'found.new.vorschauen', args: [resultParts[0], resultParts[1], resultParts[2]])}"
        redirect(action: "list")
    }

    def showDownload = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        params.sort = "sendingDate"
        def downloadVorschau = Vorschau.findAllByDownloadAction(DownloadAction.shouldBeDownloaded, params)
        def downloadVorschauTotal = Vorschau.findAllByDownloadAction(DownloadAction.shouldBeDownloaded).size()

        render(view: "list", model: [vorschauInstanceList: downloadVorschau, vorschauInstanceTotal: downloadVorschauTotal, pageToShow: "downloadPage"])
    }

    def deleteOldVorschau = {
        def deleteDate = Date.parse('yyyy-MM-dd', params.deleteDate)
        // TODO search and delete
        def crit = Vorschau.createCriteria()
        def vorschauen = crit.listDistinct {
            and {
                le('sendingDate', deleteDate)
                eq('downloadAction', DownloadAction.noDownloadRequired)
            }
        }

        def deleteSuccess = 0
        def deleteError = 0
        vorschauen.each {
            try {
                it.delete(flush: true)
                deleteSuccess++
//                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), params.id])}"
//                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
//                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'vorschau.label', default: 'Vorschau'), params.id])}"
//                redirect(action: "show", id: params.id)
                deleteError++
            }
        }
        flash.message =  "${message(code: 'deleted.old.vorschauen', args: [vorschauen.size(), params.deleteDate, deleteSuccess, deleteError])}"
        redirect(action: "list")
    }
}
