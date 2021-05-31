package com.moringaschool.luna.Utilis;

import com.moringaschool.luna.LunaService;
import com.moringaschool.luna.RetrofitClient;

public class NetworkUtils {
    private static LunaService service;
    public static LunaService getService() {
        if (service == null) {
            service = RetrofitClient.getRetrofit().create(LunaService.class);
        }
        return service;
    }
}
