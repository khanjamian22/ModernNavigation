package com.example.mypractice.utils;

public interface Constance {
    /*TODO Common Constance*/
    String USER_ROLE = "Client";
    String SERVICE_PROVIDER = "Helper";


    interface paperlib {

        String USER_DETAILS = "login_userDetail";
        String LOGIN_USER_ROLE = "login_user_role";
        String SAVE_USER_ROLE = "login_user_role_name";
        String LOGIN_USER_FORM_FILL = "login_user_form_fill";
        String ANDROID_ID = "android_id";

    }

    interface intent{

        String EMAIL_NAME = "emailname";
        String USER_DETAILS = "user_detail";

        String SIGN_UP_DETAILS = "sign_up_details";

    }


    interface firebase{
        String IMAGE_STORAGE = "Image";
    }
}
