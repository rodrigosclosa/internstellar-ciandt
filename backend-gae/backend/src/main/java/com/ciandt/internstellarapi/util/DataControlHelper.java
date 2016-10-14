package com.ciandt.internstellarapi.util;

import com.ciandt.internstellarapi.entity.DataControl;
import com.google.api.server.spi.types.SimpleDate;
import com.google.appengine.repackaged.com.google.api.client.util.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by helder on 14/10/16.
 */

public class DataControlHelper {

    public static <T extends DataControl> void PreencherDataComHoraAtual(T dataControl) {
        Date dataAtual = new Date();
        dataControl.setData(dataAtual.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dataControl.setDataFormatada(simpleDateFormat.format(dataAtual));
    }

    public static <T extends DataControl> long somarDatas(List<T> dataControllers){
        Long tempoTotal = 0L;
        for(T dataControl : dataControllers){
            tempoTotal += dataControl.getData();
        }
        return tempoTotal;
    }
}
