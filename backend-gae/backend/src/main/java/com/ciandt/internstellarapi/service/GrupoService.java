package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.GrupoDao;
import com.ciandt.internstellarapi.dao.IntegranteDao;
import com.ciandt.internstellarapi.entity.Equipe;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Integrante;
import com.ciandt.internstellarapi.helper.AuthHelper;
import com.ciandt.internstellarapi.helper.EntityHelper;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.validator.GrupoValidator;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class GrupoService {

    private GrupoDao grupoDao;
    private IntegranteDao integranteDao;
    private GrupoValidator grupoValidator;
    private IntegranteService integranteService;
    private EquipeService equipeService;

    public GrupoService() {
        grupoDao = new GrupoDao();
        grupoValidator = new GrupoValidator();
        integranteService = new IntegranteService();
        integranteDao = new IntegranteDao();
        equipeService = new EquipeService();
    }

    public List<Grupo> list() {
        List<Grupo> retorno = grupoDao.listAll();

        fetchIntegrantes(retorno);
        fetchEquipe(retorno);

        return retorno;
    }

    public List<Grupo> listByCidade(String cidade) {
        List<Grupo> grupos = null;
        List<Equipe> equipesBase = equipeService.findByBase(cidade);

        if(equipesBase != null) {
            List<Long> idEquipes = EntityHelper.extracIds(equipesBase);
            grupos = findByEquipes(idEquipes);

            fetchIntegrantes(grupos);
            fetchEquipe(grupos);
        }

        return grupos;
    }

    private void fetchIntegrantes(Collection<Grupo> retorno) {
        for (Grupo gr : retorno) {
            for (Long idIntegrante : gr.getIdIntegrantes()) {
                Integrante in = integranteDao.getByKey(idIntegrante);

                if (idIntegrante != null) {
                    gr.addIntegrantes(in);
                }
            }
        }
    }

    private void fetchEquipe(Collection<Grupo> retorno) {
        for (Grupo gr : retorno) {
            if (gr.getIdEquipe() != null) {
                Equipe eq = null;
                try {
                    eq = equipeService.getById(gr.getIdEquipe());
                    gr.setEquipe(eq);
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Grupo getById(Long id) throws NotFoundException {
        Grupo item = null;

        item = grupoDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.GrupoMessages.GRUPO_NAO_ENCONTRADO);
        }

        fetchIntegrantes(Collections.singleton(item));
        fetchEquipe(Collections.singleton(item));

        return item;
    }

    public Grupo insert(Grupo item) throws BadRequestException, UnauthorizedException, NotFoundException {

        //Validação Grupo
        if (grupoValidator.validarGrupo(item)) {
            try {
                configGrupo(item);
                Grupo grupoJaCadastrado = validarEquipeComGrupoJaCadastrado(item);
                item.setIdIntegrantes(integranteService.salvarGrupoIntegrantes(item.getIntegrantes()));
                if (grupoJaCadastrado != null) {
                    item.setId(grupoJaCadastrado.getId());
                    this.update(item);
                } else {
                    grupoDao.insert(item);
                }
            } catch (NoSuchAlgorithmException e) {
                throw new BadRequestException(e.getMessage());
            }
        } else {
            throw new BadRequestException(Messages.GrupoMessages.ERRO_REGISTRAR_GRUPO);
        }

        return item;
    }

    public Grupo validarEquipeComGrupoJaCadastrado(Grupo grupoSalvar) throws UnauthorizedException {

        Grupo grupoBanco;
        grupoBanco = grupoDao.getByProperty("idEquipe", grupoSalvar.getIdEquipe());
        if (grupoBanco != null) {
            if (!grupoSalvar.getSenha().equals(grupoBanco.getSenha())) {
                throw new UnauthorizedException(
                        Messages.GrupoMessages.EQUIPE_JA_CADASTRADA_SENHA_DEVE_SER_VALIDA_PARA_NOVO_REGISTRO);
            }
        }

        return grupoBanco;
    }

    public Grupo update(Grupo item) throws NotFoundException {
        //Validação atualização
        Grupo grupo = grupoDao.getById(item.getId());
        if (grupo == null) {
            throw new NotFoundException(Messages.GrupoMessages.GRUPO_NAO_ENCONTRADO);
        }
        grupoDao.update(item);

        return item;
    }

    public void remove(Long id) throws NotFoundException {
        Grupo item = grupoDao.getByKey(id);
        if (item == null) {
            throw new NotFoundException(Messages.GrupoMessages.GRUPO_NAO_ENCONTRADO);
        }
        grupoDao.delete(item);
    }

    public List<Grupo> findByEquipes(List<Long> idEquipes) {
        Query.Filter filterByEquipes = new Query.FilterPredicate("idEquipe", Query.FilterOperator.IN, idEquipes);

        List<Grupo> grupos = grupoDao.listByFilter(filterByEquipes);
        fetchIntegrantes(grupos);
        fetchEquipe(grupos);
        return grupos;
    }

    /**
     * Método que configura as informações a serem salvas no grupo
     */
    private void configGrupo(Grupo grupo) throws NoSuchAlgorithmException {
        configurarSenha(grupo);
    }

    private void configurarSenha(Grupo grupo) throws NoSuchAlgorithmException {
        grupo.setSenha(AuthHelper.generateHashFromText(grupo.getSenha()));
    }

}
