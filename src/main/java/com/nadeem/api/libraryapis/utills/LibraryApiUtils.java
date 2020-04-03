package com.nadeem.api.libraryapis.utills;

public class LibraryApiUtils {

    public static boolean doesStringValueExist(String str) {
        if(str!=null && str.trim().length()>0){
            return true;
        }else{
            return false;
        }
    }
}
