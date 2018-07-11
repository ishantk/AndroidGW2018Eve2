package com.auribises.activitydatapassing;

import android.net.Uri;

public class Util {

    // TDB Info
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Users.db";

    // Table Info
    public static final String TAB_NAME = "User";

    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "name";
    public static final String COL_PHONE = "phone";
    public static final String COL_EMAIL = "email";

    public static final String CREATE_TAB_QUERY = "create table User(" +
            "_ID integer primary key autoincrement," +
            "name varchar(256)," +
            "phone varchar(256)," +
            "email varchar(256)" +
            ")";

    public static final Uri USER_URI = Uri.parse("content://com.auribises.activitydatapassing.mycp/"+TAB_NAME);

}
