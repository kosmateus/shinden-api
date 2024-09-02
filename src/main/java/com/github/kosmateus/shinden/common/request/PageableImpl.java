package com.github.kosmateus.shinden.common.request;

import java.util.Optional;

/**
 * Implementation of the {@link Pageable} interface to encapsulate pagination information.
 * <p>
 * The {@code PageableImpl} class provides a concrete implementation of the {@link Pageable} interface,
 * allowing for the creation of paginated requests with specified page numbers, page sizes, and sorting
 * parameters.
 * </p>
 *
 * @version 1.0.0
 */
class PageableImpl implements Pageable {

    private final int pageNumber;
    private final int pageSize;
    private final Sort sort;

    /**
     * Constructs a new {@code PageableImpl} instance with the given page number, page size, and sorting parameters.
     *
     * @param pageNumber the zero-based page number; must not be less than zero
     * @param pageSize   the size of the page; must not be less than one
     * @param sort       the sorting parameters, may be {@code null} for no sorting
     * @throws IllegalArgumentException if the page number is less than zero or the page size is less than one
     */
    PageableImpl(int pageNumber, int pageSize, Sort sort) {
        if (pageNumber < 0) {
            throw new IllegalArgumentException("Page number must not be less than zero!");
        }

        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        }

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    /**
     * Returns the page number to be returned.
     *
     * @return the page number, zero-based
     */
    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Returns the number of items to be returned per page.
     *
     * @return the page size
     */
    @Override
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Returns the sorting parameters for the page request.
     * <p>
     * If no sorting is provided, this method returns an empty {@link Optional}.
     * </p>
     *
     * @return an {@link Optional} containing the sorting parameters, or empty if unsorted
     */
    @Override
    public Optional<Sort> getSort() {
        return Optional.ofNullable(sort);
    }
}
