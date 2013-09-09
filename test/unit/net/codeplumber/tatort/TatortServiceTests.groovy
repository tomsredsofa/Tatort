package net.codeplumber.tatort

import grails.test.*
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource

class TatortServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
        Resource resource = new ClassPathResource('testresources/tatortWikipedia.html')
        String htmlPage = resource.getFile().text
        mockDomain(Tatort)
        mockDomain(Kommissar)

        def tatortService = new TatortService()
        tatortService.extractTatorteFromWikipedia(htmlPage)

        assertEquals(2, Tatort.list().size())
    }
}
