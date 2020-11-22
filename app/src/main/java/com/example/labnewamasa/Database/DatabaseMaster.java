package com.example.labnewamasa.Database;

import android.provider.BaseColumns;

public class DatabaseMaster {
    private DatabaseMaster(){

    }

    public static final class User implements BaseColumns{
        public static final String TABLE_NAME="User";
        public static final String COLUMN_NAME_USERNAME="Name";
        public static final String COLUMN_NAME_TYPE="Type";
        public static final String COLUMN_NAME_PASSWORD="Password";

    }

    public static final class Message implements BaseColumns{
        public static final String TABLE_NAME="Message";
        public static final String COLUMN_NAME_USERNAME="User";
        public static final String COLUMN_NAME_SUBJECT="Subject";
        public static final String COLUMN_NAME_MESSAGE="Message";
    }






}
