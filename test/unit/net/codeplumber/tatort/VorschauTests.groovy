package net.codeplumber.tatort

import grails.test.*

class VorschauTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testToString() {
        def vorschau = new Vorschau(downloadAction: DownloadAction.unknown,
                sendingDate:  Date.parse('yyyy-MM-dd', '2011-03-27'),
                channel: "ARD",
                title: "Reifepruefung"
        );

        assertEquals '2011-03-27 Reifepruefung ARD', vorschau.toString()
    }

    void testCreateFileName() {
        def hamburg = new City(shortName: 'HH');
        def albert = new Kommissar(lastName: 'Albert');
        def hugo = new Kommissar(lastName: 'Hügö');
        def egon = new Kommissar(lastName: 'Ägon');
        def tatort = new Tatort(city: hamburg,
                wikipediaId: '007',
                kommissars: [hugo, egon, albert],
                fallNr: 17
        );

        def vorschau = new Vorschau(downloadAction: DownloadAction.unknown,
                sendingDate:  Date.parse('yyyy-MM-dd', '2011-03-27'),
                channel: "ARD",
                title: "Die, die Kommas; haben! Oder?",
                firstSendingDate: Date.parse('yyyy-MM-dd', '2008-10-23'),
                tatort: tatort
        );

        assertEquals '007-2008-10-23-HH-AlbertHuegoeAegon-17-DieDieKommasHabenOder', vorschau.createFileName()
    }
}
