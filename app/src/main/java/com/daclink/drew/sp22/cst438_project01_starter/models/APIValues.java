package com.daclink.drew.sp22.cst438_project01_starter.models;

import java.util.List;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIValues implements Parcelable {
    @SerializedName("Search")
    @Expose
    private List<Search> search = null;

    @SerializedName("IndividualSearch")
    @Expose
    private List<IndividualSearch> individualSearch;

    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String response;
    public final static Creator<APIValues> CREATOR = new Creator<APIValues>() {


        @SuppressWarnings({
                "unchecked"
        })
        public APIValues createFromParcel(android.os.Parcel in) {
            return new APIValues(in);
        }

        public APIValues[] newArray(int size) {
            return (new APIValues[size]);
        }

    };

    protected APIValues(android.os.Parcel in) {
        in.readList(this.search, (com.daclink.drew.sp22.cst438_project01_starter.models.Search.class.getClassLoader()));
        in.readList(this.individualSearch, (com.daclink.drew.sp22.cst438_project01_starter.models.IndividualSearch.class.getClassLoader()));

        // this.individualSearch = ((IndividualSearch) in.readValue((com.daclink.drew.sp22.cst438_project01_starter.models.IndividualSearch.class.getClassLoader())));
        this.totalResults = ((String) in.readValue((String.class.getClassLoader())));
        this.response = ((String) in.readValue((String.class.getClassLoader())));
    }

    public APIValues() {
    }

    public APIValues(List<Search> search, List<IndividualSearch> individualSearch) {
        this.search = search;
        this.individualSearch = individualSearch;
    }

    public List<Search> getSearch() {
        return search;
    }

    public void setSearch(List<Search> search) {
        this.search = search;
    }

    public List<IndividualSearch> getIndividualSearch() {
        return individualSearch;
    }

    public void setIndividualSearch(List<IndividualSearch> individualSearch) {
        this.individualSearch = individualSearch;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(search);
        dest.writeValue(individualSearch);
        dest.writeValue(totalResults);
        dest.writeValue(response);
    }

    public int describeContents() {
        return  0;
    }

}
