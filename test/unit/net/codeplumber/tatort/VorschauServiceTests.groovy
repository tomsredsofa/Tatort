package net.codeplumber.tatort

import grails.test.*
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource

class VorschauServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        Resource resource = new ClassPathResource('testresources/vorschauTest-Sep2013.html')
        String htmlPage = resource.getFile().text
        mockDomain(Tatort)
        mockDomain(Vorschau)

        def vorschauService = new VorschauService()
        vorschauService.extractVorschauen(htmlPage)

        assertEquals(27, Vorschau.list().size())
    }

    void testSlurper() {
        // need to delete the &nbsp; entities to make slurper happy
        def tl ='''
           <li>
			    <div class="vorschauicon"><img class="symbol" src="/cmspix/icons/000000_ffffff/icn_01.gif" alt="Sendetermin" /></div>
                <div class="vorschaudatum">So, 15.05.11|20:15 Uhr | Das Erste</div>
                <div class="vorschautitel"><a href="sendung.asp?datum=03.05.2009">Der illegale Tod</a></div>

                <div class="clearMe"></div>
           </li>
        '''

        def tatortList = new XmlSlurper().parseText(tl)

        println tatortList.div[1]
        println tatortList.div[2].a
        println tatortList.div[2].a.@href
    }
}
