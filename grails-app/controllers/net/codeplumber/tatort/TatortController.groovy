package net.codeplumber.tatort

//import org.springframework.core.io.ClassPathResource
//import org.springframework.core.io.Resource
//import org.springframework.core.io.UrlResource

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient
import grails.util.GrailsConfig;

class TatortController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def tatortService

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        if (!params.hugo) {
            flash.message = ''
        }
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        if (!params.sort) {
            params.sort = "firstSendingDate"
        }
        [tatortInstanceList: Tatort.list(params), tatortInstanceTotal: Tatort.count()]
    }

    def searchTatorte = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        if (!params.sort) {
            params.sort = "firstSendingDate"
        }
        if (!params.order) {
            params.order = "asc"
        }
        if (!params.offset) {
            params.offset = 0
        }
        if(params.query) {
//            params.reload = true
//            def searchResult = Tatort.search(params.query)
            def search4MovieStatus = false
            def MovieStatus ms = null
            try {
                ms = Enum.valueOf(MovieStatus, params.query)
                search4MovieStatus = true
            } catch (Exception e) {
                search4MovieStatus = false
            }
            def queryLike = "%${params.query}%"
            def crit = Tatort.createCriteria()
            def tatorte = crit.listDistinct{
                if (search4MovieStatus) {
                    eq('movieStatus', ms)
                } else {
                    or{
                        ilike('wikipediaId' , queryLike)
                        ilike('title' , queryLike)
                        ilike('specialInfo' , queryLike)
                        ilike('description' , queryLike)
                        kommissars {
                           or {
                               ilike('firstName', queryLike)
                               ilike('lastName', queryLike)
                           }
                        }
                        city {
                            or {
                                ilike('shortName', queryLike)
                                ilike('name', queryLike)
                                ilike('country', queryLike)
                            }
                        }
                    }
                    join 'kommissars'
                }
                order (params.sort, params.order)
            }
            flash.message = "${message(code: 'tatort.search.query', args: [params.query, tatorte.size()])}"

            def tatorteSlice = []
            def int offset = new Integer(params.offset)
            def int max = new Integer(params.max)
            for (int i = offset; i < offset + max && i < tatorte.size(); i++) {
                tatorteSlice << tatorte[i]
            }
            [tatorte : tatorteSlice, tatorteSize : tatorte.size()]
        } else if (params.wikiStart || params.wikiEnd) {
            // TODO this will not work correct if there are wikipediaIds > 999, because this is a String!
            def crit = Tatort.createCriteria()
            def tatorte = crit.listDistinct{
                if (params.wikiStart && params.wikiEnd) {
                    and {
                        ge('wikipediaId', params.wikiStart)
                        le('wikipediaId', params.wikiEnd)
                    }
                } else if (params.wikiStart) {
                    ge('wikipediaId', params.wikiStart)
                } else {
                    le('wikipediaId', params.wikiEnd)
                }


                order (params.sort, params.order)
            }

            if (params.wikiStart && params.wikiEnd) {
                flash.message = "${message(code: 'tatort.search.query.wikipediaid', args: [params.wikiStart, params.wikiEnd, tatorte.size()])}"
            } else if (params.wikiStart) {
                flash.message = "${message(code: 'tatort.search.query.wikipediaid.start.only', args: [params.wikiStart, tatorte.size()])}"
            } else {
                flash.message = "${message(code: 'tatort.search.query.wikipediaid.end.only', args: [params.wikiEnd, tatorte.size()])}"
            }

            def tatorteSlice = []
            def int offset = new Integer(params.offset)
            def int max = new Integer(params.max)
            for (int i = offset; i < offset + max && i < tatorte.size(); i++) {
                tatorteSlice << tatorte[i]
            }
            [tatorte : tatorteSlice, tatorteSize : tatorte.size()]
        }
    }

    def create = {
        def tatortInstance = new Tatort()
        tatortInstance.properties = params
        return [tatortInstance: tatortInstance]
    }

    def save = {
        def tatortInstance = new Tatort(params)
        if (tatortInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'tatort.label', default: 'Tatort'), tatortInstance.id])}"
            redirect(action: "show", id: tatortInstance.id)
        }
        else {
            render(view: "create", model: [tatortInstance: tatortInstance])
        }
    }

    def show = {
        flash.message = ''
        def tatortInstance = Tatort.get(params.id)
        if (!tatortInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tatort.label', default: 'Tatort'), params.id])}"
            redirect(action: "list")
        }
        else {
            [tatortInstance: tatortInstance]
        }
    }

    def edit = {
        def tatortInstance = Tatort.get(params.id)
        if (!tatortInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tatort.label', default: 'Tatort'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [tatortInstance: tatortInstance]
        }
    }

    def update = {
        def tatortInstance = Tatort.get(params.id)
        if (tatortInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tatortInstance.version > version) {
                    
                    tatortInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tatort.label', default: 'Tatort')] as Object[], "Another user has updated this Tatort while you were editing")
                    render(view: "edit", model: [tatortInstance: tatortInstance])
                    return
                }
            }
            tatortInstance.properties = params
            if (!tatortInstance.hasErrors() && tatortInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tatort.label', default: 'Tatort'), tatortInstance.id])}"
                redirect(action: "show", id: tatortInstance.id)
            }
            else {
                render(view: "edit", model: [tatortInstance: tatortInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tatort.label', default: 'Tatort'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def tatortInstance = Tatort.get(params.id)
        if (tatortInstance) {
            try {
                tatortInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tatort.label', default: 'Tatort'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tatort.label', default: 'Tatort'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tatort.label', default: 'Tatort'), params.id])}"
            redirect(action: "list")
        }
    }

     @Grab(group='org.apache.httpcomponents', module='httpclient', version='4.0.3')
    def importTatort = {
//        http://localhost:8080/Tatort/tatortExisting.html
//        Resource resource = new UrlResource('http://localhost:8080/Tatort/tatortExisting.html')
//        String htmlPage = resource.getFile().text

//        def serverUrl = GrailsConfig.get("grails.serverURL")
         String responseBody = ""
         HttpClient httpclient = new DefaultHttpClient();
         try {
//            HttpGet httpget = new HttpGet("${serverUrl}/tatortExisting.xml");
             //            HttpGet httpget = new HttpGet("http://de.wikipedia.org/wiki/Liste_der_Tatort-Folgen");
             HttpGet httpget = new HttpGet("http://localhost:8080/Tatort-1.0.4/TatortWikipedia.html");

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

         def String tatortResult = tatortService.extractTatorteFromWikipedia(responseBody)
         def resultParts = tatortResult.split(":")
         println "Import result ${resultParts}"

         flash.message = "${message(code: 'found.existing.tatorte', args: [resultParts[0], resultParts[1], resultParts[2], resultParts[3], resultParts[4]])}"
         params.hugo = "egon"
         redirect(action: "list", params: params)
    }


}
