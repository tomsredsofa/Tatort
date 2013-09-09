package net.codeplumber.tatort

import groovy.util.slurpersupport.NodeChildren

class VorschauService {

    static transactional = true

    def serviceMethod() {

    }

    @Grab(group='org.ccil.cowan.tagsoup', module='tagsoup', version='1.2')
    def extractVorschauen(String htmlPage) {
        def int vorschauTotal = 0
        def int vorschauNew = 0
        def int tatortNew = 0
        def cleanedUpHtml = cleanUpHtml(htmlPage)

        def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()
        def slurper = new XmlSlurper(tagsoupParser)
        def vorschauPage = slurper.parseText(cleanedUpHtml)

        vorschauPage.'**'.findAll{ it.@class == 'linklist'}.each {
            boxwrapper ->
            println boxwrapper.ul.li.each {
                vorschauItem ->
//                def String vorschauDatum = vorschauItem.div[0].span
//                def String vorschauTitel = vorschauItem.div[1].a.@title
                     def String vorschauInfo = vorschauItem.a

                    def parts = vorschauInfo.replace('\u00A0', ' ').split(" \\| ")
                    if (parts.length < 3) {
                        println "TOM Weg -- ${vorschauInfo}"
                    } else {
                        vorschauTotal++
                        def sendingDate
                        if ((parts[0].trim()).equals("Heute")) {
                            sendingDate = new Date().format("yyyy-MM-dd");
                        } else if ((parts[0].trim()).equals("Morgen") || (parts[0].trim()).equals("Heute Nacht")) {
                            sendingDate = new Date().plus(1).format("yyyy-MM-dd")
                        } else if ((parts[0].trim()).equals("Gestern")) {
                            sendingDate = new Date().minus(1).format("yyyy-MM-dd")
                        } else {
                            try {
                                sendingDate = ((parts[0].trim().split(" "))[1]) + new Date().getAt(Calendar.YEAR)
                                sendingDate = Date.parse('dd.MM.yyyy', sendingDate).format("yyyy-MM-dd")
                            } catch (Exception e) {
                                println("Das laeuft was schief beim Datum " + e.getMessage())
                                sendingDate = new Date().format("yyyy-MM-dd")
                            }
                        }
                        def sendingTime = (parts[1].trim().split(" "))[0]
                        def channel
                        def titelKommissare
                        if (parts.length == 4) {
                            channel = parts[2]
                            titelKommissare = parts[3]
                        } else {
                            channel = "ARD"
                            titelKommissare = parts[2]
                        }
                        def titelKommissareParts = titelKommissare.split(" \\(")
                        def vorschauTitel = titelKommissareParts[0]
                        def kommissare = titelKommissareParts[1]
                        println "TOM -- ${vorschauInfo} : '${sendingDate}' '${sendingTime}' '${channel}' '${vorschauTitel}' '${kommissare}'"

                        def dateSD = Date.parse('yyyy-MM-dd HH:mm', sendingDate + " " + sendingTime)
                        def dateFSD = dateSD

                        def tatorte = Tatort.findAllByTitle(vorschauTitel)
                        def tatort
                        if (tatorte.size() > 1) {
                            println "....Found more than one Tatort for title ${vorschauTitel}"
                            tatort = tatorte[0]
                        } else if (tatorte.size() == 1) {
                            tatort = tatorte[0]
                            println "....Tatort already exists : ${vorschauTitel}"
                        } else if (tatorte.size() == 0) {
                            tatort = new Tatort(
                                    firstSendingDate: dateFSD,
                                    wikipediaId: 'xxx',
                                    fallNr: 0,
                                    title: vorschauTitel,
                                    movieStatus: MovieStatus.shouldBeDownloaded,
                                    specialInfo: 'generated automatic Date is wrong',
                                    description: kommissare
                            )
                            if (!tatort.save()) {
                                tatort.errors.allErrors.each {error ->
                                    println "....An error occured with with saving tatort: ${error}"
                                }
                            } else {
                                tatortNew++
                                println "....created new Tatort for ${vorschauTitel}"
                            }
                        }

                        def vorschau = Vorschau.findByTitleAndSendingDate(vorschauTitel, dateSD)
                        if (vorschau == null) {
                            def downloadAction = DownloadAction.unknown
                            if (tatort.movieStatus == MovieStatus.shouldBeDownloaded || tatort.movieStatus == MovieStatus.hasError) {
                                downloadAction = DownloadAction.shouldBeDownloaded
                            } else if (tatort.movieStatus == MovieStatus.watched
                                    || tatort.movieStatus == MovieStatus.unwatched
                                    || tatort.movieStatus == MovieStatus.alreadyCopiedToLaptop
                                    || tatort.movieStatus == MovieStatus.shouldBeCopiedToLaptop) {
                                downloadAction = DownloadAction.noDownloadRequired
                            }

                            vorschau = new Vorschau(
                                    downloadAction: downloadAction,
                                    firstSendingDate: tatort.firstSendingDate,
                                    tatort: tatort,
                                    title: vorschauTitel,
                                    sendingDate: dateSD,
                                    channel: channel
                            )

                            if (!vorschau.save()) {
                                vorschau.errors.allErrors.each {error ->
                                    println "....An error occured with saving vorschau: ${error}"
                                }
                            } else {
                                println "....Create new Vorschau for : ${vorschauTitel} on ${dateSD}"
                                vorschauNew++
                            }
                        } else {
                            println "....Vorschau already exists for : ${vorschauTitel} on ${dateSD}"
                        }
                    }
//                def String vorschauFirstSendingDate = vorschauItem.div[2].a.@href
//                def vorschauFSD = vorschauFirstSendingDate.replace("sendung.asp?datum=", "")

                // replacing the non-breaking space
//               def parts = vorschauDatum.replace('\u00A0', ' ').split(" \\| ")
//                def sendingDate = (parts[0].trim().split(" "))[1] + " " + (parts[1].trim().split(" "))[0]
//                def channel = parts[2].trim()

//                println "Tatort : '${sendingDate}' '${channel}' '${vorschauTitel}'"
//                println "'${vorschauDatum}' '${vorschauTitel}'"

//                def dateSD = Date.parse('dd.MM.yy HH:mm', sendingDate + " " + sendingTime)
//                def dateFSD = Date.parse('dd.MM.yyyy', vorschauFSD)
//                maybe there is a way to get the first sending date - following the link?
//                def dateFSD = dateSD

//                def tatort = Tatort.findByFirstSendingDate(dateFSD)
                /*def tatorte = Tatort.findAllByTitle(vorschauTitel)
                def tatort
                if (tatorte.size() > 1) {
                    println "....Found more than one Tatort for title ${vorschauTitel}"
                    tatort = tatorte[0]
                } else if (tatorte.size() == 1) {
                    tatort = tatorte[0]
                    println "....Tatort already exists : ${vorschauTitel}"
                } else if (tatorte.size() == 0) {
//                if (tatort == null) {
                    tatort = new Tatort(
                            firstSendingDate: dateFSD,
                            wikipediaId: 'xxx',
                            fallNr: 0,
                            title: vorschauTitel,
                            movieStatus: MovieStatus.shouldBeDownloaded,
                            specialInfo: 'generated automatic Date is wrong',
                            description: ''
                    )
                    if (!tatort.save()) {
                        tatort.errors.allErrors.each {error ->
                            println "....An error occured with with saving tatort: ${error}"
                        }
                    } else {
                        tatortNew++
                        println "....created new Tatort for ${vorschauTitel}"
                    }
                }*/

//                def dateSD = Date.parse('dd.MM.yy HH:mm', sendingDate)
                /*def vorschau = Vorschau.findByTitleAndSendingDate(vorschauTitel, dateSD)

                if (vorschau == null) {
                    def downloadAction = DownloadAction.unknown
                    if (tatort.movieStatus == MovieStatus.shouldBeDownloaded || tatort.movieStatus == MovieStatus.hasError) {
                        downloadAction = DownloadAction.shouldBeDownloaded
                    } else if (tatort.movieStatus == MovieStatus.watched
                            || tatort.movieStatus == MovieStatus.unwatched
                            || tatort.movieStatus == MovieStatus.alreadyCopiedToLaptop
                            || tatort.movieStatus == MovieStatus.shouldBeCopiedToLaptop) {
                        downloadAction = DownloadAction.noDownloadRequired
                    }

                    vorschau = new Vorschau(
                            downloadAction: downloadAction,
                            firstSendingDate: tatort.firstSendingDate,
                            tatort: tatort,
                            title: vorschauTitel,
                            sendingDate: dateSD,
                            channel: channel
                    )

                    if (!vorschau.save()) {
                        vorschau.errors.allErrors.each {error ->
                            println "....An error occured with saving vorschau: ${error}"
                        }
                    } else {
                        println "....Create new Vorschau for : ${vorschauTitel} on ${dateSD}"
                        vorschauNew++
                    }
                } else {
                    println "....Vorschau already exists for : ${vorschauTitel} on ${dateSD}"
                }*/
            }
        }

        return "${vorschauTotal}:${vorschauNew}:${tatortNew}"
    }

    def cleanUpHtml(String htmlPage) {
        //Removing all <strong> tags
        htmlPage.replaceAll("<strong>", "").replaceAll("</strong>", "").replaceAll("<strong class=\"inv\">", "")
    }
}
