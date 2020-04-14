package ga.rugal.fridge.springmvc.graphql.exception;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * When item name is duplicated.
 *
 * @author Rugal Bernstein
 */
public class ItemDuplicateException extends RuntimeException implements GraphQLError {

  public ItemDuplicateException() {
    super("Duplicate item");
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
