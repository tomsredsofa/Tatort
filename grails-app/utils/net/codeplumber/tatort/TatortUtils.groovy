package net.codeplumber.tatort

/**
 * Created by IntelliJ IDEA.
 * User: tom
 * Date: 25.06.11
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
class TatortUtils {

    /**
     * Create a range for year field of FirstSendingDate.
     * The range starts 1970 and end in current year + 1
     * @return
     */

    public static IntRange createFSDRange() {
        def endYear = new Integer(new Date().format("yyyy")) + 1

        return new IntRange(1970, endYear)
    }

    public static IntRange createVorschauRange() {
        def currentYear = new Integer(new Date().format("yyyy"))

        return new IntRange(currentYear - 1, currentYear + 1)
    }
}
