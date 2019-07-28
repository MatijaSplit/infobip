package com.matija.infobip.exc;

public class LinkNotFoundException extends LinkshortenerException {

  public LinkNotFoundException(String message) {
    super(message);
  }

  public LinkNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
