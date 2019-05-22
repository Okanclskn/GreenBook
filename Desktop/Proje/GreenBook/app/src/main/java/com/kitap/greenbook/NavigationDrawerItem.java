package com.kitap.greenbook;

import java.util.ArrayList;

public class NavigationDrawerItem {
    String header;
    int imageID;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public static ArrayList<NavigationDrawerItem> getData(){

        ArrayList<NavigationDrawerItem> datalist = new ArrayList<NavigationDrawerItem>();
        int[] imageIDs = getImages();
        String[] headers = getHeaders();

        for (int i=0; i<headers.length;i++){
            NavigationDrawerItem navigationItem = new NavigationDrawerItem();
            navigationItem.setHeader(headers[i]);
            navigationItem.setImageID(imageIDs[i]);

            datalist.add(navigationItem);
        }
        return datalist;
    }

    private static int[] getImages() {
        return new int[]{
                R.drawable.home_page_1,R.drawable.home_page_1, R.drawable.library_1,
                R.drawable.forum_1, R.drawable.complaints_suggestions,
                R.drawable.about_us
        };
    }
    private static String[] getHeaders(){
        return new String[]{
                "Ana Sayfa","Ana Sayfa","Katagoriler","Forum","Şikayet ve Öneriler","Hakkımızda"
        };
    }
}
