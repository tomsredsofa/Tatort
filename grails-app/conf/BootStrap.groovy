import net.codeplumber.tatort.Vorschau
import net.codeplumber.tatort.DownloadAction
import net.codeplumber.tatort.Tatort
import net.codeplumber.tatort.City
import net.codeplumber.tatort.MovieStatus
import net.codeplumber.tatort.Kommissar
import grails.util.Environment

class BootStrap {

    def init = { servletContext ->
        def date1 = Date.parse('yyyy-MM-dd', '1983-01-17')
        def date2 = Date.parse('yyyy-MM-dd', '2000-03-18')

//        if (!City.get(1)) {
        if (Environment.getCurrent() == Environment.DEVELOPMENT){
            def city1 = new City(
                    name: "Hamburg",
                    shortName: "HH",
                    country: "Deutschland"
            )
            if (!city1.save()) {
                city1.errors.allErrors.each {error ->
                    println "An error occured with city1 (HH): ${error}"
                }
            }

            def city2 = new City(
                    name: "München",
                    shortName: "M",
                    country: "Deutschland"
            )
            if (!city2.save()) {
                city2.errors.allErrors.each {error ->
                    println "An error occured with city2 (M): ${error}"
                }
            }

            def kommissar1 = new Kommissar(
                    firstName: "Paul",
                    lastName: "Stoever"
            )
            if (!kommissar1.save()) {
                kommissar1.errors.allErrors.each {error ->
                    println "An error occured with kommissar1 (Stoever): ${error}"
                }
            }

            def franz = new Kommissar(
                    firstName: "Franz",
                    lastName: "Leitmayr"
            )
            if (!franz.save()) {
                franz.errors.allErrors.each {error ->
                    println "An error occured with franz: ${error}"
                }
            }

            def ivo = new Kommissar(
                    firstName: "Ivo",
                    lastName: "Batic"
            )
            if (!ivo.save()) {
                ivo.errors.allErrors.each {error ->
                    println "An error occured with ivo: ${error}"
                }
            }

            def tatort1 = new Tatort(
                    firstSendingDate: date1,
                    wikipediaId: '101',
                    city: city1,
                    fallNr: 17,
                    title: 'Der Mord',
                    movieStatus: MovieStatus.watched,
                    specialInfo: '',
                    description: 'Hier ist mal echt ganz viel passiert und so. Und dann noch dies und das. Und später auch noch ganz viel anderes.'
            )
            tatort1.addToKommissars(kommissar1)
            tatort1.addToKommissars(new Kommissar(
                    firstName: "Peter",
                    lastName: "Brockmöller"
            ))
            if (!tatort1.save()) {
                tatort1.errors.allErrors.each {error ->
                    println "An error occured with tatort1: ${error}"
                }
            }

            def tatort2 = new Tatort(
                    firstSendingDate: date2,
                    wikipediaId: '222',
                    city: city2,
                    fallNr: 12,
                    title: 'Hackenhuberl',
                    movieStatus: MovieStatus.hasError,
                    specialInfo: '',
                    description: ''
            )
            tatort2.addToKommissars(franz)
            tatort2.addToKommissars(ivo)
            if (!tatort2.save()) {
                tatort2.errors.allErrors.each {error ->
                    println "An error occured with tatort2: ${error}"
                }
            }

            for (int i = 3; i < 13; i++) {
                def movieStatus = MovieStatus.unwatched
                if (i == 12) {
                    movieStatus = MovieStatus.shouldBeDownloaded
                }
                def tatort = new Tatort(
                        firstSendingDate: date2.plus(i),
                        wikipediaId: '22' + i,
                        city: city2,
                        fallNr: 12 + i,
                        title: 'Hackenhuberl' + i,
                        movieStatus: movieStatus,
                        specialInfo: '',
                        description: ''
                )

                tatort.addToKommissars(franz)
                tatort.addToKommissars(ivo)
                if (!tatort.save()) {
                    tatort.errors.allErrors.each {error ->
                        println "An error occured with tatort ${i}: ${error}"
                    }
                }
            }

            def vorschau1 = new Vorschau(
                    downloadAction: DownloadAction.unknown,
                    firstSendingDate: date1,
                    tatort: Tatort.findByFirstSendingDate(date1),
                    title: 'Der Mord',
                    sendingDate: Date.parse('yyyy-MM-dd', '2011-03-27'),
                    channel: 'WDR'
            )
            if (!vorschau1.save()) {
                vorschau1.errors.allErrors.each {error ->
                    println "An error occured with vorschau1: ${error}"
                }
            }

            def vorschau2 = new Vorschau(
                    downloadAction: DownloadAction.shouldBeDownloaded,
                    firstSendingDate: date2,
                    tatort: Tatort.findByFirstSendingDate(date2),
                    title: 'Hackenhuberl',
                    sendingDate: Date.parse('yyyy-MM-dd', '2011-03-27'),
                    channel: 'NDR'
            )
            if (!vorschau2.save()) {
                vorschau2.errors.allErrors.each {error ->
                    println "An error occured with vorschau2: ${error}"
                }
            }

            for (int i = 3; i < 13; i++) {
                def downloadAction = DownloadAction.shouldBeDownloaded
                if (i == 12) {
                    downloadAction = DownloadAction.noDownloadRequired
                }
                def vorschau = new Vorschau(
                        downloadAction: downloadAction,
                        firstSendingDate: date2.plus(i),
                        tatort: Tatort.findByFirstSendingDate(date2.plus(i)),
                        title: 'Hackenhuberl' + i,
                        sendingDate: Date.parse('yyyy-MM-dd', '2011-03-27'),
                        channel: 'NDR'
                )
                if (!vorschau.save()) {
                    vorschau.errors.allErrors.each {error ->
                        println "An error occured with vorschau${i}: ${error}"
                    }
                }
            }
        }
    }

    def destroy = {
    }
}
