package edu.temple.peoplesmaprepublic;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

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
    public double latNum(){
        double latN=Double.parseDouble(lat);
        return latN;
    }
    public double lngNum(){
        double lngN=Double.parseDouble(lng);
        return lngN;
    }


    @Override
    public int compareTo(@NonNull Object o) {
        Partners p=(Partners) o;
        int distance;
        double x1,x2,y1,y2,d;

        x1=this.latNum();
        y1=this.lngNum();

        x2=p.latNum();
        y2=p.lngNum();

        d=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        distance=(int) d;



        return distance;
    }

}
