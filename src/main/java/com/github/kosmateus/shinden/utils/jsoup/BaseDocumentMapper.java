package com.github.kosmateus.shinden.utils.jsoup;

import com.github.kosmateus.shinden.auth.PageStructureChangedException;
import com.github.kosmateus.shinden.exception.ErrorCode;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.github.kosmateus.shinden.constants.ShindenConstants.DATE_FORMAT;
import static com.github.kosmateus.shinden.constants.ShindenConstants.DATE_TIME_FORMAT;

/**
 * Abstract base class for document mappers used to map data from web documents.
 * <p>
 * The {@code BaseDocumentMapper} class provides a framework for creating document mappers that
 * parse and map data from HTML documents. It utilizes the {@link DocumentMapperEngine} to perform
 * the actual mapping based on predefined or custom type mappers.
 * </p>
 *
 * @version 1.0.0
 */
public abstract class BaseDocumentMapper {

    /**
     * The {@link DocumentMapperEngine} instance used for mapping document data.
     */
    protected final DocumentMapperEngine mapper = createMapper();

    /**
     * Returns a code that uniquely identifies the mapper.
     * This code is used to generate exception codes.
     *
     * @return a unique mapper code
     */
    protected abstract String getMapperCode();

    /**
     * Creates a new instance of {@link DocumentMapperEngine} with the appropriate type mappers.
     *
     * @return a configured {@link DocumentMapperEngine} instance
     */
    private DocumentMapperEngine createMapper() {
        Map<Class<?>, Function<String, ?>> typeMappers = typeMappers();
        if (typeMappers.isEmpty()) {
            return new DocumentMapperEngine(getDateTimeFormat(), getDateFormat(), this::createExceptionSupplier);
        }
        return new DocumentMapperEngine(getDateTimeFormat(), getDateFormat(), typeMappers, this::createExceptionSupplier);
    }

    /**
     * Returns a map of type mappers used for converting strings to specific types.
     * Subclasses can override this method to provide custom mappers.
     *
     * @return a map of type mappers
     */
    protected Map<Class<?>, Function<String, ?>> typeMappers() {
        return ImmutableMap.of();
    }

    /**
     * Returns the date-time format used by the mapper.
     * Subclasses can override this method to specify a different format.
     *
     * @return the date-time format as a string
     */
    protected String getDateTimeFormat() {
        return DATE_TIME_FORMAT;
    }

    /**
     * Returns the date format used by the mapper.
     * Subclasses can override this method to specify a different format.
     *
     * @return the date format as a string
     */
    protected String getDateFormat() {
        return DATE_FORMAT;
    }

    /**
     * Creates a supplier for exceptions when the document structure changes.
     *
     * @param code the error code used to identify the exception
     * @return a supplier that provides an exception when called
     */
    protected Supplier<? extends RuntimeException> createExceptionSupplier(String code) {
        return () -> new PageStructureChangedException(new ExceptionBuilder(code));
    }

    /**
     * Inner class used to build exceptions with a specific error code.
     */
    @Getter
    protected class ExceptionBuilder implements ErrorCode {

        /**
         * The error code associated with the exception.
         */
        private final String code;

        /**
         * Constructs a new {@code ExceptionBuilder} with the specified code.
         *
         * @param code the error code
         */
        protected ExceptionBuilder(String code) {
            this.code = BaseDocumentMapper.this.getMapperCode() + "." + code;
        }
    }
}
