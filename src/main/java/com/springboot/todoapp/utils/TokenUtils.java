package com.springboot.todoapp.utils;

public class TokenUtils {

  public static String getRawTokenWithoutBearer(String accessToken) {
    return accessToken.startsWith("Bearer ") ? accessToken.substring(7) : accessToken;
  }
}
