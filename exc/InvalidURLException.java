package com.matija.infobip.exc;

public class InvalidURLException extends LinkshortenerException {

  public InvalidURLException(String message) {
    super(message);
  }

  public InvalidURLException(String message, Throwable cause) {
    super(message, cause);
  }
}