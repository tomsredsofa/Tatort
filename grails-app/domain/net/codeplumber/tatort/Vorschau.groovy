package net.codeplumber.tatort

class Vorschau {

    static constraints = {
        downloadAction()
        firstSendingDate()
        tatort(nullable:true)
        title()
        sendingDate()
        channel()
    }

    DownloadAction downloadAction;
    Date sendingDate;
    String channel;
    String title;
    Date firstSendingDate;
    Tatort tatort;

    String toString() {
        String dateString = sendingDate.format('yyyy-MM-dd');

        "$dateString $title $channel"
    }

    String createFileName() {
//        805-2011-06-19-LU-OdenthalKopper-53-ImAbseits

        String wikipediaId = tatort.wikipediaId
        String fsd = firstSendingDate.format('yyyy-MM-dd')
        String cityCode = tatort.city?.shortName
        String komm = ""
        tatort.kommissars.sort{it.lastName}.each {
            komm = komm + it.lastName
        }
        String fallNr = tatort.fallNr
        String theTitel = ""
        title.split(" ").each {
            theTitel = theTitel + it.capitalize()
        }

        "$wikipediaId-$fsd-$cityCode-$komm-$fallNr-$theTitel"
            .replaceAll("ä", "ae")
            .replaceAll("Ä", "Ae")
            .replaceAll("ö", "oe")
            .replaceAll("Ö", "Oe")
            .replaceAll("ü", "ue")
            .replaceAll("Ü", "ue")
            .replaceAll("ß", "ss")
            .replaceAll(",", "")
            .replaceAll(";", "")
            .replaceAll("!", "")
            .replaceAll("\\?", "")
    }
}
