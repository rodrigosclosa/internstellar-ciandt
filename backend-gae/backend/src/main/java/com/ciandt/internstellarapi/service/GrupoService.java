package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.GrupoDao;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.GrupoValidator;
import com.ciandt.internstellarapi.helper.AuthHelper;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.ConflictException;
import com.google.api.server.spi.response.NotFoundException;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by rodrigosclosa on 29/08/16.
 */
public class GrupoService {

    private static final String ALGORITIMO_HASH_SENHA = "MD5";

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
            throw new NotFoundException("Equipe não encontrada.");
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
            throw new BadRequestException("Erro ao registrar grupo");
        }

        return item;
    }

    public Grupo update(Grupo item) throws ConflictException, NotFoundException {
        //Validação atualização
        Grupo grupo = grupoDao.getById(item.getId());
        if (grupo == null) {
            throw new NotFoundException("Equipe não encontrada");
        }
        grupoDao.update(item);

        return item;
    }

    public void remove(Long id) throws ConflictException, NotFoundException {
        Grupo item = grupoDao.getByKey(id);
        if (item == null) {
            throw new NotFoundException("Equipe não encontrada.");
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
