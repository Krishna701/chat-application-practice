package com.practice.services;

public class AdminServices {
    public boolean getLoginResponse(String id, String username)
    {
        System.out.println(username + " " + id);
        return username.equals("Krishna Rijal") && id.equals("hello123");
    }
}
