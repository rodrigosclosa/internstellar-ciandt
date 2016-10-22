package com.ciandt.internstellarapi.util;

import com.ciandt.internstellarapi.entity.DataControl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by helder on 14/10/16.
 */

public class DataControlHelper {

    private static final int FIRST_ITEM = 0;

    public static <T extends DataControl> void PreencherDataComHoraAtual(T dataControl) {
        Date dataAtual = new Date();
        dataControl.setData(dataAtual.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dataControl.setDataFormatada(simpleDateFormat.format(dataAtual));
    }

    public static <T extends DataControl> long somarDatas(List<T> dataControllers) {
        Long tempoTotal = 0L;
        for (T dataControl : dataControllers) {
            tempoTotal += dataControl.getData();
        }
        return tempoTotal;
    }

    public static <T extends DataControl> DataControl getGreaterDate(List<T> dataControllers) {
        List<T> dataControlsOrdered = new ArrayList<>(dataControllers);
        Collections.sort(dataControlsOrdered, new DataControllerComparator<>());
        return dataControlsOrdered.get(FIRST_ITEM);
    }

    private static class DataControllerComparator<T extends DataControl> implements Comparator<T> {

        @Override
        public int compare(T dataControl, T t1) {
            return t1.getData().compareTo(dataControl.getData());
        }
    }
}
