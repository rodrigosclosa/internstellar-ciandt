package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.entity.Avaliacao;
import com.ciandt.internstellarapi.entity.Equipe;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.GrupoSumarioAvaliacao;
import com.ciandt.internstellarapi.entity.Resposta;
import com.ciandt.internstellarapi.helper.EntityHelper;
import com.ciandt.internstellarapi.util.DataControlHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
                List<Resposta> respostas = respostaService.respostasCorretas(respostasGrupo);
                sumario.setCountRespCorr(respostas.size());
                sumario.setTempoTotalRespostas(DataControlHelper.somarDatas(respostas));
            }
            List<Avaliacao> avaliacoes = avaliacaoService.findByGrupo(g.getId());
            if (avaliacoes != null && !avaliacoes.isEmpty()) {
                sumario.setDesafConc(avaliacoes.size());
                sumario.setTempoTotalDesafios(DataControlHelper.somarDatas(avaliacoes));
            }
            gruposSumario.add(sumario);
        }

        Collections.sort(gruposSumario, new SumarioComparator());
        return gruposSumario;
    }

    private class SumarioComparator implements Comparator<GrupoSumarioAvaliacao> {

        @Override
        public int compare(GrupoSumarioAvaliacao s1,
                           GrupoSumarioAvaliacao s2) {
            int resultCompare = 0;
            int desafioCompare = s2.getDesafConc().compareTo(s1.getDesafConc());
            int desafioTimeCompare = s1.getDesafConc().compareTo(s2.getDesafConc());
            int respostaCountCompare = s2.getCountRespCorr().compareTo(s1.getCountRespCorr());
            int respostaTimeCompare = s1.getTempoTotalRespostas().compareTo(s2.getTempoTotalRespostas());

            if (desafioCompare != 0) {
                resultCompare = desafioCompare;
            } else if (respostaCountCompare != 0) {
                resultCompare = respostaCountCompare;
            } else if (desafioTimeCompare != 0) {
                resultCompare = desafioTimeCompare;
            } else if (respostaTimeCompare != 0) {
                resultCompare = respostaTimeCompare;
            }

            return resultCompare;
        }
    }


}
