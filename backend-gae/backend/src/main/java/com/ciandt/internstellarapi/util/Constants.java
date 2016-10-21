package com.ciandt.internstellarapi.util;

import com.ciandt.internstellarapi.entity.Equipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodrigosclosa on 05/10/16.
 */
public class Constants {
    private static Constants ourInstance = new Constants();

    public static Constants getInstance() {
        return ourInstance;
    }

    private Constants() {
    }

    public String getOneSignalKey() {
        return "NTMwMTMzZDEtOTNmMC00MTIxLThiMzgtYmM4NzgyOTkzNWYw";
    }

//    public String getOneSignalTag(String carteirinha) {
//        return "{\"itemskey\": \"username\", \"relation\": \"=\", \"value\": \"" + carteirinha + "\"}";
//    }

    public String getOneSignalAppId() {
        return "\"5d1aa582-7c6d-4b8d-96fd-4dacaac12ca5\"";
    }

    public String getMensagemParametros () {
        return "Parabéns! Esta é uma mensagem de Push.";
    }

    public static List<Equipe> NomesEquipes() {
        List<Equipe> retorno = new ArrayList<Equipe>(){{
            add(new Equipe("Team Ursa", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/ursa.png"));
            add(new Equipe("Team Ursa", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/ursa.png"));
            add(new Equipe("Team Ursa", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/ursa.png"));
            add(new Equipe("Team Ursa", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/ursa.png"));
            add(new Equipe("Team Órion", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/orion.png"));
            add(new Equipe("Team Órion", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/orion.png"));
            add(new Equipe("Team Órion", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/orion.png"));
            add(new Equipe("Team Órion", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/orion.png"));
            add(new Equipe("Team Andrômeda", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/andromeda.png"));
            add(new Equipe("Team Andrômeda", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/andromeda.png"));
            add(new Equipe("Team Andrômeda", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/andromeda.png"));
            add(new Equipe("Team Andrômeda", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/andromeda.png"));
            add(new Equipe("Team Fênix", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/fenix.png"));
            add(new Equipe("Team Fênix", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/fenix.png"));
            add(new Equipe("Team Fênix", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/fenix.png"));
            add(new Equipe("Team Fênix", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/fenix.png"));
            add(new Equipe("Team Centauro", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/centauro.png"));
            add(new Equipe("Team Centauro", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/centauro.png"));
            add(new Equipe("Team Centauro", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/centauro.png"));
            add(new Equipe("Team Centauro", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/centauro.png"));
            add(new Equipe("Team Águia", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/aguia.png"));
            add(new Equipe("Team Águia", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/aguia.png"));
            add(new Equipe("Team Águia", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/aguia.png"));
            add(new Equipe("Team Águia", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/aguia.png"));
            add(new Equipe("Team Dragão", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/dragao.png"));
            add(new Equipe("Team Dragão", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/dragao.png"));
            add(new Equipe("Team Dragão", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/dragao.png"));
            add(new Equipe("Team Dragão", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/dragao.png"));
            add(new Equipe("Team Lira", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/lira.png"));
            add(new Equipe("Team Lira", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/lira.png"));
            add(new Equipe("Team Lira", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/lira.png"));
            add(new Equipe("Team Lira", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/lira.png"));
            add(new Equipe("Team Hidra", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/hidra.png"));
            add(new Equipe("Team Hidra", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/hidra.png"));
            add(new Equipe("Team Hidra", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/hidra.png"));
            add(new Equipe("Team Hidra", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/hidra.png"));
            add(new Equipe("Team Escorpião", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/escorpiao.png"));
            add(new Equipe("Team Escorpião", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/escorpiao.png"));
            add(new Equipe("Team Escorpião", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/escorpiao.png"));
            add(new Equipe("Team Escorpião", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/escorpiao.png"));
            add(new Equipe("Team Perseu", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/perseu.png"));
            add(new Equipe("Team Perseu", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/perseu.png"));
            add(new Equipe("Team Perseu", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/perseu.png"));
            add(new Equipe("Team Perseu", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/perseu.png"));
            add(new Equipe("Team Pégaso", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/pegaso.png"));
            add(new Equipe("Team Pégaso", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/pegaso.png"));
            add(new Equipe("Team Pégaso", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/pegaso.png"));
            add(new Equipe("Team Pégaso", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/pegaso.png"));
            add(new Equipe("Team Cão", "Verde", "Campinas", "http://internstellar-ciandt.appspot.com/images/verde/cao.png"));
            add(new Equipe("Team Cão", "Verde", "BH", "http://internstellar-ciandt.appspot.com/images/verde/cao.png"));
            add(new Equipe("Team Cão", "Azul", "Campinas", "http://internstellar-ciandt.appspot.com/images/azul/cao.png"));
            add(new Equipe("Team Cão", "Azul", "BH", "http://internstellar-ciandt.appspot.com/images/azul/cao.png"));
        }};

        return retorno;
    }

    public static final String ADM_TOKEN = "edb011adff20426e89536c69ef58b087";
}
