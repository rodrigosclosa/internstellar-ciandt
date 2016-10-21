/*
    Este é somente um exemplo de como criar uma fábrica para executar uma chamada a uma API.
*/
angular.module('app.services')

    //Serviço de exemplo para construção de uma API
    .factory('ExemploService', function ($http, AppConfig, RetornoServicos) {
        
        //Recupera um parâmetro de configuração do factory de Config
        var urlBase = AppConfig.urlServicos();

        return {
            //Exemplo de chamada de API para retornar uma lista de registros
            all: function (callback) {
                //Crio o objeto de retorno padrão de resposta dos serviços (para facilitar as coisas...)
                var retorno = RetornoServicos.retorno();

                //Aqui é feita uma chamada na API passando seu endereço base e o caminho da chamada do EndPoint completo
                //Este caminho pode ser olhado no API Explorer do projeto
                $http.get(urlBase + 'api/v1/equipes/get')
                    .success(function (response) {
                        //Indico que deu sucesso na chamada
                        retorno.success = true;

                        //A lista dos registros está aqui neste objeto
                        retorno.items = response.items;

                        if (typeof (callback) === "function") {
                            callback(retorno);
                        }
                    })
                    .error(function (data, status) {
                        //Indico que deu algum erro
                        retorno.success = false;

                        //Preencho o objeto de erro com detalhes como mensagem, código do erro..
                        retorno.erro = {
                            codigo: status,
                            mensagem: data
                        };

                        if (typeof (callback) === "function") {
                            callback(retorno);
                        }
                    });
            },
            //Exemplo de chamada de API para retornar um registro
            get: function (id, callback) {
               //TODO: Você precisa pensar um pouco e pesquisar como recuperar somente um registro da API
            },
            //Exemplo de chamada no API para inserir ou atualizar um registro
            addOrUpdate: function (item, callback) {
                //TODO: Você precisa pensar mais um pouco e pesquisar como enviar os dados para um API
            },
            //Exemplo de chamada de API para excluir um registro
            remove: function (id, callback) {
                //TODO: Você vai precisar pensar mais um pouco ainda e pesquisar como remover algum item de uma API
            }
        };
    });
