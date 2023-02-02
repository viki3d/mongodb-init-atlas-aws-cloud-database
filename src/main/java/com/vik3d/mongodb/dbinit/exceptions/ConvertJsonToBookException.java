package com.vik3d.mongodb.dbinit.exceptions;

/**
 * Define custom exception for failures during JSON to Books conversions.
 *
 * @author viki3d
 */
public class ConvertJsonToBookException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ConvertJsonToBookException(String msg) {
    super(msg);
  }

}
