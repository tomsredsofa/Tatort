package net.codeplumber.tatort

import grails.test.*

class TatortTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testToString() {

        def tatort1 = new Tatort(
                title: "Hallo Test",
                wikipediaId: 101,
                city: new City(
                        name: "Hamburg",
                        shortName: "HH",
                        country: "Deutschland"
                )
        )

        def tatort2 = new Tatort(
                title: "Hallo Test",
                wikipediaId: 101
        )

        assertEquals("101 HH", tatort1.toString())
        assertEquals("101 ", tatort2.toString())
    }
}
