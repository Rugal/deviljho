package ga.rugal.fridge.springmvc.graphql.exception;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * When item not found.
 *
 * @author Rugal Bernstein
 */
public class ItemNotFoundException extends RuntimeException implements GraphQLError {

  public ItemNotFoundException(final Integer iid) {
    super(String.format("Item [%d] not found", iid));
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
    return Map.of("status", 404);
  }
}
