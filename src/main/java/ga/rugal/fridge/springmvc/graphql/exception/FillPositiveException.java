package ga.rugal.fridge.springmvc.graphql.exception;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * Fill number must be positive. Throw this exception when input number is non-positive.
 *
 * @author Rugal Bernstein
 */
public class FillPositiveException extends RuntimeException implements GraphQLError {

  public FillPositiveException() {
    super("Fill quantity must be positive");
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.ValidationError;
  }

  @Override
  public Map<String, Object> getExtensions() {
    return Map.of("status", 401);
  }
}
