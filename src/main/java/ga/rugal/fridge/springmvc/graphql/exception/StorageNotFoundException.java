package ga.rugal.fridge.springmvc.graphql.exception;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * When storage not found.
 *
 * @author Rugal Bernstein
 */
public class StorageNotFoundException extends RuntimeException implements GraphQLError {

  public StorageNotFoundException(final Integer sid) {
    super(String.format("Storage [%d] not found", sid));
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
