package com.supunkanushka.learngraphql.Handler;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler extends DataFetcherExceptionResolverAdapter {

	@Override
	protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
		ErrorType type = null;

		if(ex instanceof DataIntegrityViolationException){
			type = ErrorType.BAD_REQUEST;
		}else{
			type = ErrorType.INTERNAL_ERROR;
		}

		return GraphqlErrorBuilder.newError(env)
				.message("Received message: " + ex.getMessage())
				.errorType(type)
				.build();
	}
}
