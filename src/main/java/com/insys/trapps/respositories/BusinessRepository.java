package com.insys.trapps.respositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.insys.trapps.model.Business;

/**
 * {@link BusinessRepository} for PersonellServices.
 *
 * @author Kris Krishna
 * @since 1.0.0
 **/
public interface BusinessRepository extends PagingAndSortingRepository<Business, Long> {
    // TODO
}
