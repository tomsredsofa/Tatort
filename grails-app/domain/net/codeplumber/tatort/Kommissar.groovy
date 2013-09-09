package net.codeplumber.tatort

/**
 * Created by IntelliJ IDEA.
 * User: tom
 * Date: 19.06.11
 * Time: 10:21
 * To change this template use File | Settings | File Templates.
 */
class Kommissar {

    String firstName;
    String lastName;
    String jobTitle;
    String specialInfo;
    String actorFirstName;
    String actorLastName;

    static constraints = {
        firstName(nullable: true)
        jobTitle(nullable: true)
        specialInfo(nullable: true, maxSize: 1000)
        actorFirstName(nullable: true)
        actorLastName(nullable: true)
    }

    String toString() {
        def firstN = ""
        if (firstName != null) {
           firstN = firstName
        }
        "$firstN $lastName"
    }
}
