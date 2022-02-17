package com.daclink.drew.sp22.cst438_project01_starter;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/*
 * Class: ExampleInstrumentedTest.java
 * Description: Basic Instrument test for the program.
 * */

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.daclink.drew.sp22.cst438_project01_starter", appContext.getPackageName());
    }
}