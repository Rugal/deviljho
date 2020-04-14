package ga.rugal.fridge.springmvc.graphql.exception;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * When tag name is duplicated.
 *
 * @author Rugal Bernstein
 */
public class TagDuplicateException extends RuntimeException implements GraphQLError {

  public TagDuplicateException() {
    super("Duplicate tag");
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
    return Map.of("status", 409);
  }
}
