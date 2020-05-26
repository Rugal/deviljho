package ga.rugal.fridge.springmvc.graphql.exception;

import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * When target item does not have enough in storage.
 *
 * @author Rugal Bernstein
 */
public class StorageNotEnoughException extends RuntimeException implements GraphQLError {

  public StorageNotEnoughException(final Integer sid) {
    super(String.format("Not enough storage [%d]", sid));
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
