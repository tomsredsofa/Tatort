package net.codeplumber.tatort

/**
 * Created by IntelliJ IDEA.
 * User: tom
 * Date: 19.06.11
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
class City {
    String name;
    String shortName;
    String country;

    static constraints = {
        name(nullable: true)
        country(nullable: true)
    }

    String toString() {
        "$shortName"
    }
}
