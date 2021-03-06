package com.ciandt.internstellarapi.helper;

/**
 * Created by falcao on 06/10/16.
 */

public class Messages {

    public static class GenericMessages {
        public static String ERRO_NAO_ESPERADO = "Erro não esperado, entrar em contato com a equipe responsável pela API: %s";
    }

    public static class GrupoMessages {
        public static String GRUPO_NAO_INFORMADO = "Grupo não informado";
        public static String NOME_GRUPO_NAO_INFORMADA = "Nome do grupo não informado.";
        public static String FOTO_GRUPO_NAO_INFORMADA = "Foto grupo não informada";
        public static String IDENTIFICADOR_EQUIPE_NAO_INFORMADO = "A equipe não foi informada";
        public static String IDENTIFICADOR_EQUIPE_NAO_VALIDO = "A equipe informada não é válida.";
        public static String SENHA_NAO_INFORMADA = "Senha não informada";
        public static String SENHAS_INFORMADAS_NAO_COINCIDEM = "As senhas informadas não coincidem";
        public static String INTEGRANTES_DEVEM_SER_INFORMADOS = "Integrantes devem ser informados";
        public static String NOME_DO_INTEGRANTE_DEVE_SER_INFORMADO = "O nome do integrante deve ser informado";
        public static String FOTO_DO_INTEGRANTE_DEVE_SER_INFORMADA = "A foto do integrante deve ser informada";
        public static String NUMERO_INTEGRANTE_NAO_INFORMADO = "O número do integrante não foi informado";
        public static String GRUPO_NAO_ENCONTRADO = "Grupo não encontrado.";
        public static String ERRO_REGISTRAR_GRUPO = "Erro ao registrar grupo.";
        public static String EQUIPE_JA_CADASTRADA_SENHA_DEVE_SER_VALIDA_PARA_NOVO_REGISTRO =
                "Equipe já tem um grupo cadastrado, senha deve coincidir para novo registro";
    }

    public static class EquipeMessages {
        public static String NAO_FORAM_ENCONTRADAS_EQUIPES_COM_NOME = "Não foram encontradas equipes com o nome: %s";
        public static String EQUIPE_NAO_ENCONTRADA = "Equipe não encontrada.";
        public static String EQUIPE_NAO_INFORMADA = "Equipe não informada.";
        public static String NOME_NAO_INFORMADO = "Nome da equipe não informado.";
        public static String COR_NAO_INFORMADA = "Cor da equipe não informada.";
        public static String BASE_NAO_INFORMADA = "Base da equipe não informada.";
        public static String IMAGEM_NAO_INFORMADA = "Imagem da equipe não informada.";
    }

    public static class PlanetaMessages {
        public static String NAO_FORAM_ENCONTRADOS_PLANETAS_NOME = "Não foram encontrados planetas com o nome: %s";
        public static String PLANETA_NAO_ENCONTRADO = "Planeta não encontrado.";
        public static String PLANETA_NAO_INFORMADO = "Planeta não informado";
        public static String NOME_PLANETA_NAO_INFORMADO = "Nome do planeta não informado.";
        public static String DESCRICAO_PLANETA_NAO_INFORMADA = "Descrição do planeta não informada.";
    }

    public static class PerguntaMessages {
        public static String PERGUNTA_NAO_ENCONTRADA = "Pergunta não encontrada.";
        public static String NENHUMA_PERGUNTA_FOI_ENCONTRADA = "Nenhuma pergunta foi encontrada";
        public static String PERGUNTA_NAO_INFORMADA = "Pergunta não informada.";
        public static String TITULO_PERGUNTA_NAO_INFORMADO = "Titulo da pergunta não informado.";
        public static String DESCRICAO_PERGUNTA_NAO_INFORMADA = "A descrição da pergunta não foi inforamada";
        public static String OPCOES_PERGUNTA_NAO_INFORMADAS = "As opções da pergunta não foram informadas";
        public static String DESCRICAO_OPCAO_DEVE_SER_INFORMADA = "A descrição da opção deve ser informada";
        public static String PLANETA_DEVE_SER_INFORMADO = "Um planeta deve ser informado para a pergunta";
        public static String PLANETA_INFORMADO_INVALIDO = "O planeta informado é inválido";
        public static String OPCAO_CORRETA_NAO_INFORMADA = "A opção correta não foi informada";
        public static String PRIMEIRA_PERGUNTA_NAO_REQUISITADA = "Primeira pergunta não requisitada";
        public static String GRUPO_NAO_INFORMADO = "O grupo não foi informado";
        public static String GRUPO_INVALIDO = "O grupo informado não é válido";
        public static String TODAS_AS_PERGUNTAS_DESTE_PLANETA_FORAM_RESPONDIDAS = "Todos as perguntas deste planeta foram respondidas";


    }

    public static class AutenticacaoMessages {
        public static String AUTENTICACAO_INVALIDA = "Informações inválidas, tente novamente...";
    }

    public static class TokenMessages {
        public static String ITEM_NAO_INFORMADO = "Item não informado";
        public static String TOKEN_NAO_INFORMADO = "Token não informado";
        public static String TOKEN_NAO_ENCONTRADO = "Token não encontrado";
        public static String ACESSO_NEGADO_TOKEN_NAO_ENCONTRADO_INVALIDO = "Acesso negado. Token não encontrado ou inválido.";
        public static String TOKEN_NAO_PERMITE_OPERACAO_PARA_O_GRUPO = "Token não permite operação para o grupo";

    }

    public static class RespostaMessages {
        public static final String TOKEN_INVALIDO_PARA_GRUPO = "Token inválido para operações deste grupo.";
        public static final String RESPOSTA_NAO_INFORMADA = "Resposta não informada.";
        public static final String GRUPO_NAO_INFORMADO = "Grupo não informado.";
        public static final String TOKEN_NAO_INFORMADO = "Token não informado.";
        public static final String PERGUNTA_NAO_INFORMADA = "Pergunta não informada.";
        public static final String PERGUNTA_INFORMADA_INVALIDA = "Pergunta informada inválida.";
        public static final String RESPOSTA_CORRETA_NAO_INFORMADA = "Resposta não informada.";
        public static final String RESPOSTA_INVALIDA_PARA_A_PERGUNTA = "Resposta informada não válida.";
        public static final String RESPOSTA_JA_ENVIADA = "Resposta já enviada.";
        public static final String GRUPO_INFORMADO_INVALIDO = "Grupo informado inválido";
        public static final String PROXIMA_PERGUNTA_NAO_INFORMADA = "Esta pergunta para o planeta ainda não foi selecionada pelo seu grupo";

    }

    public static class AvaliacaoMessages {
        public static final String AVALIACAO_NAO_ENCONTRADA = "Avaliação não encontrada.";
        public static final String GRUPO_NAO_INFORMADO = "O grupo não foi informado.";
        public static final String DESAFIO_NAO_INFORMADO = "O desafio não foi informado.";
        public static final String GRUPO_INFORMADO_INVALIDO = "O grupo informado não é válido.";
        public static final String AVALIACAO_JA_ENVIADA_PARA_O_GRUPO = "Avaliação já enviada para o grupo.";
    }


}
