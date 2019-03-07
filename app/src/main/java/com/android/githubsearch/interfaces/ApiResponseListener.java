package com.android.githubsearch.interfaces;

import com.android.volley.VolleyError;

public interface ApiResponseListener {
    public void onSuccessResponse(String apiTag, String resJsonStr);
    public void onErrorResponse(String apiTag, VolleyError error);
}
