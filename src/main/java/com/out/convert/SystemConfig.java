/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.out.convert;

import java.util.Properties;

public class SystemConfig {

    private Properties sysProerites;

    private static SystemConfig syscfg;

    private SystemConfig() {
    }

    public static SystemConfig getInstance() {
        if (syscfg == null) {
            syscfg = new SystemConfig();
        }
        return syscfg;
    }

    public Properties getSysProerites() {
        return sysProerites;
    }

    public void setSysProerites(Properties sysProerites) {
        this.sysProerites = sysProerites;
    }

}
