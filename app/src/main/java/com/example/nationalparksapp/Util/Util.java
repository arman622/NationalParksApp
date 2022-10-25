package com.example.nationalparksapp.Util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Util {

    //You have authorize through nps api website: https://www.nps.gov/subjects/developer/api-documentation.htm
    //api_key = gNA6p86rFEelKjPX7aI2OXV049VxzIUdLPVkUEKd
    public static final String PARKS_URL = "https://developer.nps.gov/api/v1/parks?stateCode=ny&api_key=gNA6p86rFEelKjPX7aI2OXV049VxzIUdLPVkUEKd";

    public static String getParksUrl(String stateCode) {
        String url = "https://developer.nps.gov/api/v1/parks?stateCode="+stateCode+"&api_key=gNA6p86rFEelKjPX7aI2OXV049VxzIUdLPVkUEKd";
        return url;
    }

    public static void hideSoftKeyboard(View view){
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
