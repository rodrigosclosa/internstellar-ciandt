package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.GrupoDao;
import com.ciandt.internstellarapi.dao.IntegranteDao;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Integrante;
import com.ciandt.internstellarapi.entity.Token;
import com.ciandt.internstellarapi.helper.AuthHelper;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.validator.GrupoValidator;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Query;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class GrupoService {

    private GrupoDao grupoDao;
    private IntegranteDao integranteDao;

    private GrupoValidator grupoValidator;

    private IntegranteService integranteService;

    public GrupoService() {
        grupoDao = new GrupoDao();
        grupoValidator = new GrupoValidator();
        integranteService = new IntegranteService();
        integranteDao = new IntegranteDao();
    }

    public List<Grupo> list() {
        List<Grupo> retorno = grupoDao.listAll();

        for (Grupo gr : retorno) {
            for (Long idIntegrante : gr.getIdIntegrantes()) {
                Integrante in = integranteDao.getByKey(idIntegrante);

                if(idIntegrante != null) {
                    gr.addIntegrantes(in);
                }
            }
        }

        return retorno;
    }

    public List<Grupo> findByName(String name) {
        List<Grupo> retorno = grupoDao.listByProperty("name", name);

        for (Grupo gr : retorno) {
            for (Long idIntegrante : gr.getIdIntegrantes()) {
                Integrante in = integranteDao.getByKey(idIntegrante);

                if(idIntegrante != null) {
                    gr.addIntegrantes(in);
                }
            }
        }

        return retorno;
    }

    public Grupo getById(Long id) throws NotFoundException {
        Grupo item = null;

        item = grupoDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.GrupoMessages.GRUPO_NAO_ENCONTRADO);
        }

        for (Long idIntegrante : item.getIdIntegrantes()) {
            Integrante in = integranteDao.getByKey(idIntegrante);

            if(idIntegrante != null) {
                item.addIntegrantes(in);
            }
        }

        return item;
    }

    public Grupo insert(Grupo item) throws BadRequestException, UnauthorizedException, NotFoundException {

        //Validação Grupo
        if (grupoValidator.validarGrupo(item)) {
            try {
                configGrupo(item);
                Grupo grupoJaCadastrado = validarEquipeComGrupoJaCadastrado(item);
                if (grupoJaCadastrado != null) {
                    this.remove(grupoJaCadastrado.getId());
                }
                item.setIdIntegrantes(integranteService.salvarGrupoIntegrantes(item.getIntegrantes()));
                grupoDao.insert(item);
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

    public Grupo update(Grupo item) throws ConflictException, NotFoundException {
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
