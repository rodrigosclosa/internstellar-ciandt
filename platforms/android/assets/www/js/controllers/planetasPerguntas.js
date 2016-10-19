'use strict';
angular.module('app.controllers')

    .controller('PlanetasPerguntasController', function ($scope, $stateParams, PlanetasMock) {
        $scope.planeta = PlanetasMock.get($stateParams.planetaId);
    });