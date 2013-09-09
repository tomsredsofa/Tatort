package net.codeplumber.tatort

import grails.test.GrailsUnitTestCase

/**
 * Created by IntelliJ IDEA.
 * User: tom
 * Date: 25.06.11
 * Time: 18:18
 * To change this template use File | Settings | File Templates.
 */
class TatortUtilsTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateFSDRange() {
        assertEquals(new IntRange(1970, new Integer(new Date().format("yyyy")) + 1), TatortUtils.createFSDRange())
    }

    void testCreateVorschauRange() {
        assertEquals(new IntRange(new Integer(new Date().format("yyyy")) - 1, new Integer(new Date().format("yyyy")) + 1), TatortUtils.createVorschauRange())
    }
}
