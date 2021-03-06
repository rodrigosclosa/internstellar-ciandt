package com.ciandt.internstellarapi.service.validator;

import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.Integrante;
import com.ciandt.internstellarapi.helper.Messages;
import com.ciandt.internstellarapi.service.EquipeService;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.repackaged.com.google.common.base.StringUtil;

import java.util.List;

/**
 * Created by falcao on 06/10/16.
 */

public class GrupoValidator {

    private EquipeService equipeService;

    public GrupoValidator() {
        equipeService = new EquipeService();
    }

    private boolean validarGrupoInformado(Grupo grupo) throws BadRequestException {
        if (grupo == null) {
            throw new BadRequestException(Messages.GrupoMessages.GRUPO_NAO_INFORMADO);
        }
        return Boolean.TRUE;
    }

    private boolean validarNomeInformado(String nome) throws BadRequestException {
        if (nome == null) {
            throw new BadRequestException(Messages.GrupoMessages.NOME_GRUPO_NAO_INFORMADA);
        }
        return Boolean.TRUE;
    }

    private boolean validarFotoInformada(Text foto) throws BadRequestException {
        if (foto == null) {
            throw new BadRequestException(Messages.GrupoMessages.FOTO_GRUPO_NAO_INFORMADA);
        }
        return Boolean.TRUE;
    }

    private boolean validarIdEquipeInformado(Long idEquipe) throws BadRequestException {
        try {
            if (idEquipe != null) {
                equipeService.getById(idEquipe);
            } else {
                throw new BadRequestException(Messages.GrupoMessages.IDENTIFICADOR_EQUIPE_NAO_INFORMADO);
            }
        } catch (NotFoundException ex) {
            throw new BadRequestException(Messages.GrupoMessages.IDENTIFICADOR_EQUIPE_NAO_VALIDO);
        }
        return Boolean.TRUE;
    }

    private boolean validarSenhasInformadas(String senha, String senhaVerificadora) throws BadRequestException {
        if (StringUtil.isEmptyOrWhitespace(senha) || StringUtil.isEmptyOrWhitespace(senhaVerificadora)) {
            throw new BadRequestException(Messages.GrupoMessages.SENHA_NAO_INFORMADA);
        } else if (!senha.equals(senhaVerificadora)) {
            throw new BadRequestException(Messages.GrupoMessages.SENHAS_INFORMADAS_NAO_COINCIDEM);
        }
        return Boolean.TRUE;
    }

    private boolean validarIntegrantesInformados(List<Integrante> integrantes) throws BadRequestException {
        if (integrantes == null || integrantes.isEmpty()) {
            throw new BadRequestException(Messages.GrupoMessages.INTEGRANTES_DEVEM_SER_INFORMADOS);
        }

        for (Integrante gi : integrantes) {
            if (StringUtil.isEmptyOrWhitespace(gi.getNome())) {
                throw new BadRequestException(Messages.GrupoMessages.NOME_DO_INTEGRANTE_DEVE_SER_INFORMADO);
            }
            if (gi.getFoto() == null || StringUtil.isEmptyOrWhitespace(gi.getFoto().toString())) {
                throw new BadRequestException(Messages.GrupoMessages.FOTO_DO_INTEGRANTE_DEVE_SER_INFORMADA);
            }
            if (gi.getNumero() == null) {
                throw new BadRequestException(Messages.GrupoMessages.NUMERO_INTEGRANTE_NAO_INFORMADO);
            }
        }
        return Boolean.TRUE;
    }

    public boolean validarGrupo(Grupo grupo) throws BadRequestException {
        return validarGrupoInformado(grupo)
                && validarFotoInformada(grupo.getFotoEquipe())
                && validarNomeInformado(grupo.getNome())
                && validarIdEquipeInformado(grupo.getIdEquipe())
                && validarSenhasInformadas(grupo.getSenha(), grupo.getSenhaVerificadora())
                && validarIntegrantesInformados(grupo.getIntegrantes());

    }

}
