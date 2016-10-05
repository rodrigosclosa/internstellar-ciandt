angular.module('app.config', [])

    //Arquivo de configurações padrões do Projeto
    .factory('AppConfig', function ($http) {

        return {
            urlServicos: function () {
                return "https://internstellar-cit.appspot.com/_ah/api/";
            }
        };
    });