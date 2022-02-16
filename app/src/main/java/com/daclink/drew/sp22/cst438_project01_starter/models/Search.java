package com.daclink.drew.sp22.cst438_project01_starter.models;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Search implements Parcelable
    {
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Year")
        @Expose
        private String year;
        @SerializedName("imdbID")
        @Expose
        private String imdbID;
        @SerializedName("Type")
        @Expose
        private String type;
        @SerializedName("Poster")
        @Expose
        private String poster;
        public final static Creator<Search> CREATOR = new Creator<Search>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Search createFromParcel(Parcel in) {
                return new Search(in);
            }

            public Search[] newArray(int size) {
                return (new Search[size]);
            }

        };

        protected Search(android.os.Parcel in) {
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.year = ((String) in.readValue((String.class.getClassLoader())));
            this.imdbID = ((String) in.readValue((String.class.getClassLoader())));
            this.type = ((String) in.readValue((String.class.getClassLoader())));
            this.poster = ((String) in.readValue((String.class.getClassLoader())));
        }

        @Override
        public String toString() {
            return "Search{" +
                    "title='" + title + '\'' +
                    ", year='" + year + '\'' +
                    ", imdbID='" + imdbID + '\'' +
                    ", type='" + type + '\'' +
                    ", poster='" + poster + '\'' +
                    '}';
        }

        public Search(String title, String year, String imdbID, String type, String poster) {
            this.title = title;
            this.year = year;
            this.imdbID = imdbID;
            this.type = type;
            this.poster = poster;
        }

        public Search() {
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getImdbID() {
            return imdbID;
        }

        public void setImdbID(String imdbID) {
            this.imdbID = imdbID;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public void writeToParcel(android.os.Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(year);
            dest.writeString(imdbID);
            dest.writeString(type);
            dest.writeString(poster);
        }

        public int describeContents() {
            return  0;
        }

    }