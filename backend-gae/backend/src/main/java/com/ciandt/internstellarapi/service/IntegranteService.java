package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.IntegranteDao;
import com.ciandt.internstellarapi.entity.Integrante;
import com.ciandt.internstellarapi.helper.EntityHelper;

import java.util.List;

/**
 * Created by falcao on 06/10/16.
 */

public class IntegranteService {

    private IntegranteDao integranteDao;

    public IntegranteService() {
        integranteDao = new IntegranteDao();
    }

    public List<Long> salvarGrupoIntegrantes(List<Integrante> grupoIntegrantes) {

        integranteDao.saveMany(grupoIntegrantes);

        List<Long> idsIntegrantes = EntityHelper.extracIds(grupoIntegrantes);

        return idsIntegrantes;
    }
}
