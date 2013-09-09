package net.codeplumber.tatort

class Tatort {

    Date firstSendingDate;
    String wikipediaId;
    static hasMany = [kommissars : Kommissar];
    City city;
    int fallNr;
    String title;
    MovieStatus movieStatus;
    String specialInfo;
    String description;

    static constraints = {
        city(nullable: true)
        specialInfo(maxSize: 1000)
        description(maxSize: 3000)
    }

    static mapping = {
        kommissars lazy: false
        city fetch: 'join'
    }

    String toString() {
        def cityPart = ""
        if (city != null) {
            cityPart = city.shortName
        }
        "$wikipediaId $cityPart"
    }
}
