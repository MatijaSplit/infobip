package com.matija.infobip.exc;
public class LinkshortenerException extends RuntimeException {

  public LinkshortenerException(String message) {
    super(message);
  }

  public LinkshortenerException(String message, Throwable cause) {
    super(message, cause);
  }

}