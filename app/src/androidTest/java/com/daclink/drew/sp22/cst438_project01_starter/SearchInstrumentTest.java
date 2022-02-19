package com.daclink.drew.sp22.cst438_project01_starter;

import static junit.framework.TestCase.assertNull;

import android.content.Context;
import android.os.Parcel;
import android.util.Pair;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import androidx.lifecycle.ViewModelProviders;

import com.daclink.drew.sp22.cst438_project01_starter.adapters.SearchResultsAdapter;
import com.daclink.drew.sp22.cst438_project01_starter.models.APIValues;
import com.daclink.drew.sp22.cst438_project01_starter.models.Search;
import com.daclink.drew.sp22.cst438_project01_starter.viewModels.SearchViewModel;

import androidx.lifecycle.Observer;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * Class: SearchInstrumentTest.java
 * Description: Basic Search results test for the program.
 * */
@RunWith(AndroidJUnit4.class)
public class SearchInstrumentTest {

    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private Search testSearch;

    @Before
    public void createTestSearch() {
        testSearch = new Search();
    }

    private List<String> createFilledParcel() {

        // Set up the Parcelable object to send and receive.
        testSearch.setSearch(
                "TRON: Legacy",
                "2010",
                "tt1104001",
                "movie",
                "https://m.media-amazon.com/images/M/MV5BMTk4NTk4MTk1OF5BMl5BanBnXkFtZTcwNTE2MDIwNA@@._V1_SX300.jpg"
        );

        // Write the data.
        Parcel parcel = Parcel.obtain();
        testSearch.writeToParcel(parcel, testSearch.describeContents());

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        Search createdFromParcel = Search.CREATOR.createFromParcel(parcel);

        return createdFromParcel.getValues();
    }

    private List<String> createEmptyParcel() {

        // Set up the Parcelable object to send and receive.
        testSearch.setSearch(
                null,
                null,
                null,
                null,
                null
        );

        // Write the data.
        Parcel parcel = Parcel.obtain();
        testSearch.writeToParcel(parcel, testSearch.describeContents());

        // After you're done with writing, you need to reset the parcel for reading.
        parcel.setDataPosition(0);

        // Read the data.
        Search createdFromParcel = Search.CREATOR.createFromParcel(parcel);

        return createdFromParcel.getValues();
    }

    @Test
    public void searchParcelableWriteRead() {
        // creating standard testing parcel for data.
        List<String> createdFromParcelData
                = createFilledParcel();

        // Verify that the received data is correct.
        assertEquals("Parsable failed getting correct title", createdFromParcelData.get(0), "TRON: Legacy");
        assertEquals("Parsable failed getting correct year", createdFromParcelData.get(1), "2010");
        assertEquals("Parsable failed getting correct poster", createdFromParcelData.get(2), "https://m.media-amazon.com/images/M/MV5BMTk4NTk4MTk1OF5BMl5BanBnXkFtZTcwNTE2MDIwNA@@._V1_SX300.jpg");
        assertEquals("Parsable failed getting correct imdbID", createdFromParcelData.get(3), "tt1104001");
    }

    @Test
    public void testEmptySearch() {
        // creating standard testing parcel for data.
        List<String> createdFromParcelData
                = createEmptyParcel();

        // Verify that the received data is correct.
        assertNull("Parsable failed getting correct title", createdFromParcelData.get(0));
        assertNull("Parsable failed getting correct year", createdFromParcelData.get(1));
        assertNull("Parsable failed getting correct poster", createdFromParcelData.get(2));
        assertNull("Parsable failed getting correct imdbID", createdFromParcelData.get(3));
    }
}
