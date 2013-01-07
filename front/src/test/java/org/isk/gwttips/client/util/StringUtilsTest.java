package org.isk.gwttips.client.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void camelCase0() {
        String string = StringUtils.camelCase("MY_VARIABLE", '_');
        Assert.assertEquals("myVariable", string);
    }

    @Test
    public void camelCase1() {
        String string = StringUtils.camelCase("my_variable", '_');
        Assert.assertEquals("myVariable", string);
    }

    @Test
    public void camelCase2() {
        String string = StringUtils.camelCase("My_variABLE", '_');
        Assert.assertEquals("myVariable", string);
    }
    
    @Test
    public void camelCase3() {
        String string = StringUtils.camelCase("MY.VARIABLE", '.');
        Assert.assertEquals("myVariable", string);
    }

    @Test
    public void camelCase4() {
        String string = StringUtils.camelCase("my.variable", '.');
        Assert.assertEquals("myVariable", string);
    }

    @Test
    public void camelCase5() {
        String string = StringUtils.camelCase("My.variABLE", '.');
        Assert.assertEquals("myVariable", string);
    }
    
    @Test
    public void camelCase6() {
        String string = StringUtils.camelCase("My.vari5ABLE", '.');
        Assert.assertEquals("myVari5able", string);
    }
    
    @Test
    public void camelCase7() {
        String string = StringUtils.camelCase("My.variè5ABLE", '.');
        Assert.assertEquals("myVariè5able", string);
    }
    
    @Test
    public void camelCase8() {
        String string = StringUtils.camelCase("My.éariè5ABLE", '.');
        Assert.assertEquals("myÉariè5able", string);
    }
    
}
