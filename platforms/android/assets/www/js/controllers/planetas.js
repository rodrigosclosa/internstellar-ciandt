'use strict';
angular.module('app.controllers')

    .controller('PlanetasController', function ($scope, PlanetasMock) {
        
        // Para executar algum código toda vez que entrar em uma tela (por exemplo, para atualizar algum dado),
        // espere o evento the $ionicView.enter:
        //
        //$scope.$on('$ionicView.enter', function(e) {
        //});

        $scope.planetas = PlanetasMock.all();

        //Exemplo de uma função sendo criada no $scope para ser chamada por um botão
        $scope.remove = function (planeta) {
            PlanetasMock.remove(planeta);
        };
    });