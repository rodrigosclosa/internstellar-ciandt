package com.ciandt.internstellarapi.service;

import com.ciandt.internstellarapi.dao.PlanetaDao;
import com.ciandt.internstellarapi.entity.Avaliacao;
import com.ciandt.internstellarapi.entity.DataControl;
import com.ciandt.internstellarapi.entity.Equipe;
import com.ciandt.internstellarapi.entity.Grupo;
import com.ciandt.internstellarapi.entity.GrupoSumarioAvaliacao;
import com.ciandt.internstellarapi.entity.Planeta;
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

    private static final int QUANTIDADE_CONQUISTADOR = 5;

    private static final int MULTIPLICADOR_AVALIACAO = 7;

    private GrupoService grupoService;

    private EquipeService equipeService;

    private RespostaService respostaService;

    private AvaliacaoService avaliacaoService;

    private PlanetaDao planetaDao;

    public GrupoSumarioService() {
        grupoService = new GrupoService();
        equipeService = new EquipeService();
        respostaService = new RespostaService();
        avaliacaoService = new AvaliacaoService();
        planetaDao = new PlanetaDao();
    }

    public List<GrupoSumarioAvaliacao> getSumarioGruposPorBase(String base) {

        List<Equipe> equipesBase = equipeService.findByBase(base);

        List<Long> idEquipes = EntityHelper.extracIds(equipesBase);

        List<Grupo> grupos = grupoService.findByEquipes(idEquipes);

        List<GrupoSumarioAvaliacao> gruposSumario = new ArrayList<>();

        for (Grupo g : grupos) {
            GrupoSumarioAvaliacao sumario = new GrupoSumarioAvaliacao();
            List<Resposta> respostasGrupo = respostaService.findByGrupo(g.getId());
            List<DataControl> dataControls = new ArrayList<>();
            sumario.setGrupo(g);
            if (respostasGrupo != null && !respostasGrupo.isEmpty()) {
                List<Resposta> respostas = respostaService.respostasCorretas(respostasGrupo);
                sumario.setCountRespCorr(respostas.size());
                sumario.setTempoTotalRespostas(DataControlHelper.somarDatas(respostas));
                dataControls.addAll(respostas);
            }
            List<Avaliacao> avaliacoes = avaliacaoService.findByGrupo(g.getId());
            if (avaliacoes != null && !avaliacoes.isEmpty()) {
                sumario.setDesafConc(avaliacoes.size());
                sumario.setTempoTotalDesafios(DataControlHelper.somarDatas(avaliacoes));
                dataControls.addAll(avaliacoes);
            }
            sumario.setPontos(calcPontos(sumario.getDesafConc(), sumario.getCountRespCorr()));
            if (dataControls != null && !dataControls.isEmpty()) {
                DataControl ultimaPontuacao = DataControlHelper.getGreaterDate(dataControls);
                sumario.setUltimaPontuacao(ultimaPontuacao.getData());
                sumario.setUltimaPontuacaoFormatada(ultimaPontuacao.getDataFormatada());
            }
            gruposSumario.add(sumario);
        }

        Collections.sort(gruposSumario, new SumarioComparator());

        sumarizarPlanetas();
        return gruposSumario;
    }

    private int calcPontos(Integer desafiosConcluidos, Integer respostasCorretas) {
        return desafiosConcluidos * MULTIPLICADOR_AVALIACAO + respostasCorretas;
    }

    private class SumarioComparator implements Comparator<GrupoSumarioAvaliacao> {

        @Override
        public int compare(GrupoSumarioAvaliacao s1,
                           GrupoSumarioAvaliacao s2) {
            int resultCompare = 0;
            int pontosCompare = s2.getPontos().compareTo(s1.getPontos());
            int ultimaPontuacaoCompare = s1.getUltimaPontuacao().compareTo(s2.getUltimaPontuacao());

            if (pontosCompare != 0) {
                resultCompare = pontosCompare;
            } else if (ultimaPontuacaoCompare != 0) {
                resultCompare = ultimaPontuacaoCompare;
            }

            return resultCompare;
        }
    }

    public void sumarizarPlanetas() {
        List<Planeta> planetas = planetaDao.listAll();
        initializePlanetasForSumarizar(planetas);
        List<Grupo> grupos = grupoService.list();
        for (Planeta planeta : planetas) {
            for (Grupo grupo : grupos) {
                List<Resposta> respostas =
                        respostaService.findByGrupoPlaneta(grupo.getId(), planeta.getId());
                if (respostaService.respostasCorretas(respostas).size() >= QUANTIDADE_CONQUISTADOR) {
                    planeta.getGruposConquistadoresIds().add(grupo.getId());
                }
            }
        }
        planetaDao.saveMany(planetas);
    }

    private void initializePlanetasForSumarizar(List<Planeta> planetas) {
        for (Planeta p : planetas) {
            p.setGruposConquistadoresIds(new ArrayList<Long>());
        }
    }

}
