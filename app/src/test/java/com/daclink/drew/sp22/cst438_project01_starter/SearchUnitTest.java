package com.daclink.drew.sp22.cst438_project01_starter;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.daclink.drew.sp22.cst438_project01_starter.models.Search;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchUnitTest extends TestCase {

    @Test
    public void testTestToString() {
        Search test = new Search();
        // empty search
        assertEquals("Failed ToString with empty Search", test.toString(), "Search{" +
                "title='" + null + '\'' +
                ", year='" + null + '\'' +
                ", imdbID='" + null + '\'' +
                ", type='" + null + '\'' +
                ", poster='" + null + '\'' +
                '}');
        // non-empty search
        test.setTitle("test");
        test.setYear("test");
        test.setImdbID("test");
        test.setType("test");
        test.setPoster("test");
        assertEquals("Failed ToString with non-empty Search",
                test.toString(),
                "Search{title=\'test\', year=\'test\', imdbID=\'test\', type=\'test\', poster=\'test\'}");
    }

    public void testGetTitle() {
        Search test = new Search();
        assertEquals("Failed testGetTitle() with empty Search", test.getTitle(), null);

    }

    public void testSetTitle() {
        Search test = new Search();
        test.setTitle("test");
        assertEquals("Failed testSetTitle() with \"test\" Search", test.getTitle(), "test");
    }

    public void testGetImdbID() {
        Search test = new Search();
        assertEquals("Failed testGetImdbID() with empty Search", test.getImdbID(), null);
    }

    public void testSetImdbID() {
        Search test = new Search();
        test.setImdbID("test");
        assertEquals("Failed testSetImdbID() with \"test\" Search", test.getImdbID(), "test");
    }

    public void testGetImdbId() {
        Search test = new Search();
        assertEquals("Failed testGetImdbId() with empty Search", test.getImdbId(), null);
    }

    public void testSetImdbId() {
        Search test = new Search();
        test.setImdbId("test");
        assertEquals("Failed testSetImdbId() with empty Search", test.getImdbId(), "test");
    }

    public void testGetValues() {
        Search test = new Search();
        List<String> emptyList = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            emptyList.add(null);
        assertEquals("Failed testGetValues() with empty Search", test.getValues(), emptyList);
    }

    public void testDescribeContents() {
        Search test = new Search();
        assertEquals("Failed testDescribeContents() with empty Search", test.describeContents(), 0);
    }

}