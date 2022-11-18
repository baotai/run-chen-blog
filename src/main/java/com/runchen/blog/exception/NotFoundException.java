package com.runchen.blog.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AbstractApolloHttpException {


  public NotFoundException(String str) {
    super(str);
    setHttpStatus(HttpStatus.NOT_FOUND);
  }
}
