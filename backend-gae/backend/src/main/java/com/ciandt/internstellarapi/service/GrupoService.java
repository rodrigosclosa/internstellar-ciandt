package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.GrupoDao;
import com.ciandt.internstellarapi.entity.Grupo;
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

    private GrupoValidator grupoValidator;

    private IntegranteService integranteService;

    public GrupoService() {
        grupoDao = new GrupoDao();
        grupoValidator = new GrupoValidator();
        integranteService = new IntegranteService();
    }

    public List<Grupo> list() {
        return grupoDao.listAll();
    }

    public List<Grupo> findByName(String name) {
        return grupoDao.listByProperty("name", name);
    }

    public Grupo getById(Long id) throws NotFoundException {
        Grupo item = null;

        item = grupoDao.getByKey(id);

        if (item == null) {
            throw new NotFoundException(Messages.GrupoMessages.GRUPO_NAO_ENCONTRADO);
        }

        return item;
    }

    public Grupo insert(Grupo item) throws BadRequestException {

        //Validação Grupo
        if (grupoValidator.validarGrupo(item)) {
            try {
                configGrupoValido(item);
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

    public Grupo update(Grupo item) throws ConflictException, NotFoundException {
        //Validação atualização
        Grupo grupo = grupoDao.getById(item.getId());
        if (grupo == null) {
            throw new NotFoundException(Messages.GrupoMessages.GRUPO_NAO_ENCONTRADO);
        }
        grupoDao.update(item);

        return item;
    }

    public void remove(Long id) throws ConflictException, NotFoundException {
        Grupo item = grupoDao.getByKey(id);
        if (item == null) {
            throw new NotFoundException(Messages.GrupoMessages.GRUPO_NAO_ENCONTRADO);
        }
        grupoDao.delete(item);
    }

    /**
     * Método que configura as informações a serem salvas no grupo
     */
    private void configGrupoValido(Grupo grupo) throws NoSuchAlgorithmException {
        configurarSenha(grupo);
    }

    private void configurarSenha(Grupo grupo) throws NoSuchAlgorithmException {
        grupo.setSenha(AuthHelper.generateHashFromText(grupo.getSenha()));
    }

}
