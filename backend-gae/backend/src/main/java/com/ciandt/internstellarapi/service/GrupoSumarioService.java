package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.entity.Avaliacao;
import com.ciandt.internstellarapi.entity.Equipe;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.GrupoSumarioAvaliacao;
import com.ciandt.internstellarapi.entity.Resposta;
import com.ciandt.internstellarapi.helper.EntityHelper;
import com.google.appengine.api.datastore.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by helder on 13/10/16.
 */

public class GrupoSumarioService {

    private GrupoService grupoService;

    private EquipeService equipeService;

    private RespostaService respostaService;

    private AvaliacaoService avaliacaoService;

    public GrupoSumarioService() {
        grupoService = new GrupoService();
        equipeService = new EquipeService();
        respostaService = new RespostaService();
        avaliacaoService = new AvaliacaoService();
    }

    public List<GrupoSumarioAvaliacao> getSumarioGruposPorBase(String base) {

        List<Equipe> equipesBase = equipeService.findByBase(base);

        List<Long> idEquipes = EntityHelper.extracIds(equipesBase);

        List<Grupo> grupos = grupoService.findByEquipes(idEquipes);

        List<GrupoSumarioAvaliacao> gruposSumario = new ArrayList<>();

        for (Grupo g : grupos) {
            GrupoSumarioAvaliacao sumario = new GrupoSumarioAvaliacao();
            List<Resposta> respostasGrupo = respostaService.findByGrupo(g.getId());
            sumario.setGrupo(g);

            if (respostasGrupo != null && !respostasGrupo.isEmpty()) {
                Integer quantidadeRespostasCorretas = respostaService.countRespostasCorretas(respostasGrupo);
                sumario.setCountRespostasCorretas(quantidadeRespostasCorretas);
            }
            List<Avaliacao> avaliacoes = avaliacaoService.findByGrupo(g.getId());
            if (avaliacoes != null && !avaliacoes.isEmpty()) {
                sumario.setDesafiosConcluídos(avaliacoes.size());
            }

        }

        return gruposSumario;
    }


}
