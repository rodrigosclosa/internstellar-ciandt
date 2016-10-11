package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.IntegranteDao;
import com.ciandt.internstellarapi.entity.Integrante;

import java.util.ArrayList;
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
        List<Integrante> integrantesToSave = new ArrayList<>();
        for (Integrante grupoIntegrante : grupoIntegrantes) {
            Integrante integrante = new Integrante();
            integrante.setNome(grupoIntegrante.getNome());
            integrante.setFoto(grupoIntegrante.getFoto());
            integrante.setNumero(grupoIntegrante.getNumero());
            integrantesToSave.add(integrante);
        }
        integranteDao.insertMany(integrantesToSave);
        List<Long> idsIntegrantes = new ArrayList<>();
        for (Integrante integranteSaved : integrantesToSave) {
            idsIntegrantes.add(integranteSaved.getId());
        }
        return idsIntegrantes;
    }
}
