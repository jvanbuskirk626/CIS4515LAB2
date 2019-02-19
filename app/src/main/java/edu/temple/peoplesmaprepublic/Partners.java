package edu.temple.peoplesmaprepublic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;

public class Partners  implements Comparable, Serializable {

    String user,lat,lng;

    public Partners(String u, String lt, String lg)
    {
        user=u;
        lat=lt;
        lng=lg;
    }

    public String getUser()
    {
        return user;
    }

    public String getLat()
    {
        return lat;
    }

    public String getLng()
    {
        return lng;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }

}
