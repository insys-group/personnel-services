package com.insys.trapps.util;

import com.insys.trapps.model.AbstractEntity;

/**
 * Created by vnalitkin on 11/18/2016.
 */
public interface AbstractEntityFactory<P extends AbstractEntity> {
    P create();
}
