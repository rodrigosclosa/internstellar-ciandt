angular.module('app.services')

    //Serviço de exemplo para construção de uma API
    .factory('ExemploService', function ($http, AppConfig, RetornoServicos) {

        return {
            //Exemplo de chamada de API para retornar uma lista de registros
            all: function (parametros, callback) {
                var retorno = RetornoServicos.retorno();

                $http.get(urlBase + 'api/v1/list', parametros)
                    .success(function (response) {
                        retorno.success = true;
                        retorno.items = response.items;

                        if (typeof (callback) === "function") {
                            callback(retorno);
                        }
                    })
                    .error(function (data, status) {
                        retorno.success = false;
                        retorno.erro = {
                            codigo: status,
                            mensagem: data
                        };

                        if (typeof (callback) === "function") {
                            callback(retorno);
                        }
                    });
            },
            //Exemplo de chamada no API para inserir ou atualizar um registro
            addOrUpdate: function (item, callback) {
                var retorno = RetornoServicos.retorno();

                if (item.id != null) {
                    $http.put(urlBase + 'api/v1/update', item)
                        .success(function (response) {
                            retorno.success = true;
                            retorno.items = response;

                            if (typeof (callback) === "function") {
                                callback(retorno);
                            }
                        })
                        .error(function (data, status) {
                            retorno.success = false;
                            retorno.erro = {
                                codigo: status,
                                mensagem: data
                            };

                            if (typeof (callback) === "function") {
                                callback(retorno);
                            }
                        });
                } else {
                    $http.post(urlBase + 'api/v1/new', item)
                        .success(function (response) {
                            retorno.success = true;
                            retorno.items = response;

                            if (typeof (callback) === "function") {
                                callback(retorno);
                            }
                        })
                        .error(function (data, status) {
                            retorno.success = false;
                            retorno.erro = {
                                codigo: status,
                                mensagem: data
                            };

                            if (typeof (callback) === "function") {
                                callback(retorno);
                            }
                        });
                }
            },
            //Exemplo de chamada de API para excluir um registro
            remove: function (id, callback) {
                var retorno = RetornoServicos.retorno();

                $http.delete(urlBase + 'api/v1/delete/' + id)
                    .success(function (response) {
                        retorno.success = true;
                        if (typeof (callback) === "function") {
                            callback(retorno);
                        }
                    })
                    .error(function (data, status) {
                        retorno.success = false;
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
                var retorno = RetornoServicos.retorno();

                $http.get(urlBase + 'api/v1/get/' + id)
                    .success(function (response) {
                        retorno.success = true;
                        retorno.items = response;

                        if (typeof (callback) === "function") {
                            callback(retorno);
                        }
                    })
                    .error(function (data, status) {
                        retorno.success = false;
                        retorno.erro = {
                            codigo: status,
                            mensagem: data
                        };

                        if (typeof (callback) === "function") {
                            callback(retorno);
                        }
                    });
            }
        };
    });
