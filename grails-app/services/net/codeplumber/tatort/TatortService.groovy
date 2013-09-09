package net.codeplumber.tatort

import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

class TatortService {

    static transactional = true
    static BigInteger kommissarCounter = new BigInteger("0")

    def serviceMethod() {

    }

    @Grab(group='org.ccil.cowan.tagsoup', module='tagsoup', version='1.2')
    def extractTatorteFromWikipedia(String htmlPage) {
        // some counter
        def int tatortTotal = 0
        def int tatortNew = 0
        def int tatortOld = 0
        def int tatortFehler = 0


        def cleanedUpHtml = cleanUpHtml(htmlPage)

        def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()
        def slurper = new XmlSlurper(tagsoupParser)
        def tatortPage = slurper.parseText(cleanedUpHtml)

        SimpleDateFormat sdf = new SimpleDateFormat("dd. MMM. yyyy", Locale.GERMANY)

        tatortPage.'**'.findAll{ it.@class == 'wikitable sortable'}.each {
            boxwrapper ->
            println boxwrapper.tr.each {
                tatortItem ->
                if (tatortItem.td[0]) {
                    tatortTotal++

                    def String wikipediaId = tatortItem.td[0]
                    def String tatortTitel = tatortItem.td[1].a
                    def String sender = tatortItem.td[2]
                    def String tatortFSD = tatortItem.td[3]
                    def String ermittler = tatortItem.td[4]
                    def String fallNr = tatortItem.td[5]
                    def String autor = tatortItem.td[6]
                    def String regie = tatortItem.td[7]
                    def String besonderheiten = tatortItem.td[8]

                    def kommissars = extractKommissars(ermittler)

                    if (wikipediaId.length() == 1) {
                        wikipediaId = "00" + wikipediaId
                    } else if (wikipediaId.length() == 2) {
                        wikipediaId = "0" + wikipediaId
                    }

                    def dateFSD = sdf.parse(tatortFSD)
                    println "Tatort : ${wikipediaId} | ${tatortTitel} | ${sender} | ${dateFSD} | ${kommissars} | ${fallNr} | ${autor} | ${regie} | ${besonderheiten}"

                    def tatort = Tatort.findByFirstSendingDate(dateFSD)
                    if (tatort == null) {
                        tatort = new Tatort(
                                firstSendingDate: dateFSD,
                                wikipediaId: wikipediaId,
                                fallNr: fallNr,
                                title: tatortTitel,
                                kommissars: kommissars,
                                movieStatus: MovieStatus.shouldBeDownloaded,
                                description: "generated automatic from scanning Wikipedia",
                                specialInfo: "Sender: ${sender}\nAutor: ${autor}\nRegie: ${regie}\nBesonderheiten: ${besonderheiten}"
                        )
                        if (!tatort.save()) {
                            tatortFehler++
                            tatort.errors.allErrors.each {error ->
                                println "....An error occured with saving tatort: ${error}"
                            }
                        } else {
                            tatortNew++
                            println "....created new Tatort for ${tatortTitel}"
                        }
                    } else {
                        tatortOld++
                        tatort.specialInfo = tatort.specialInfo + "generated automatic from scanning Wikipedia\nSender: ${sender}\nAutor: ${autor}\nRegie: ${regie}\nBesonderheiten: ${besonderheiten}"
                        println "Den Tatort hab ich schon ${tatortTitel}"
                        if (!tatort.save()) {
                            tatortFehler++
                            tatort.errors.allErrors.each {error ->
                                println "....An error occured with updating old tatort: ${error}"
                            }
                        } else {
                            tatortNew++
                            println "....updated old Tatort for ${tatortTitel}"
                        }
                    }
                } else {
                    println "----That was only the headline"
                }
            }
        }

//        Found {0} Wikipedia Tatorte, {1} created new, {2} existierten schon und bei {3} gab es Fehler und {4} neue Kommissare
        return "${tatortTotal}:${tatortNew}:${tatortOld}:${tatortFehler}:${kommissarCounter.intValue()}"
    }

    private def extractKommissars(String ermittler) {
        def kommissars = []
        def ermittlersString = ermittler.replaceAll(", ", " ").replaceAll(" und ", " ").replaceAll("/", " ").replaceAll("\n", " ").replaceAll("  ", " ")

        Pattern p = Pattern.compile("\\(Gastauftritt")
        Matcher m = p.matcher(ermittlersString)
        if (m.find()) {
            println "-------Pattern: " + m.group()
            def gastauftritt = ermittlersString.substring(m.start())
            ermittlersString = ermittlersString.subSequence(0, m.start() - 1)
            println "-------Ermittler:  ${ermittlersString} / Rest: ${gastauftritt}"
        }
        def ermittlersParts = ermittlersString.split(" ")
        ermittlersParts.each {
            ermittlerName ->
            if (ermittlerName.trim().length() > 0) {
                def kommissar = Kommissar.findByLastName(ermittlerName.trim())
                if (kommissar == null) {
                    kommissar = new Kommissar(
                            lastName: ermittlerName.trim()
                    )
                    if (!kommissar.save()) {
                        kommissar.errors.allErrors.each {error ->
                            println "Ging was schief beim Kommissar sichern: ${error}"
                        }
                    } else {
                        kommissarCounter = kommissarCounter.add(BigInteger.ONE)
                        println "Neuer Kommissar : ${ermittlerName}"
                    }
                } else {
                    println "Den Kommissar hab ich schon ${ermittlerName}"
                }
                kommissars.add(kommissar)
            }
        }
        return kommissars
    }

    def cleanUpHtml(String htmlPage) {
        //Removing all <strong> tags
//        htmlPage.replaceAll("<strong>", "").replaceAll("</strong>", "").replaceAll("<strong class=\"inv\">", "")

        // Replace Mar to Mrz because this is the official translation
        htmlPage.replaceAll("Mär.", "Mrz.").replaceAll("Mai", "Mai.")


    }
}
