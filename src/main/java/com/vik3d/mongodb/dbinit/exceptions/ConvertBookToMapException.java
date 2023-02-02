package com.vik3d.mongodb.dbinit.exceptions;

/**
 * Define custom exception for failures during Book to Map conversions.
 *
 * @author viki3d
 */
public class ConvertBookToMapException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ConvertBookToMapException(String msg) {
    super(msg);
  }

}
