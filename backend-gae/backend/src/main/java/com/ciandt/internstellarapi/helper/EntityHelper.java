package com.ciandt.internstellarapi.helper;

import com.ciandt.internstellarapi.entity.BaseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by helder on 13/10/16.
 */

public class EntityHelper {

    public static <T extends BaseEntity> List<Long> extracIds(List<T> entities) {
        List<Long> ids = new ArrayList<>();
        for (BaseEntity entity : entities) {
            ids.add(entity.getId());
        }
        return ids;
    }

    public static <T extends BaseEntity> void orderEntities(List<T> entities) {
        Collections.sort(entities, new DefaultOrderEntity());
    }

    private static class DefaultOrderEntity<T extends BaseEntity> implements Comparator<T> {

        @Override
        public int compare(T o, T t1) {
            return o.getId().compareTo(t1.getId());
        }
    }


}
