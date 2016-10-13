package com.ciandt.internstellarapi.helper;

import com.ciandt.internstellarapi.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helder on 13/10/16.
 */

public class EntityHelper {

    public static<T extends BaseEntity> List<Long> extracIds(List<T> entities) {
        List<Long> ids = new ArrayList<>();
        for (BaseEntity entity : entities) {
            ids.add(entity.getId());
        }
        return ids;
    }


}
