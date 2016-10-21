'use strict';

angular.module('internstellarDashApp')
  .controller('PlanetasCtrl', ['$scope', '$routeParams', '$interval', 'InternstellarService', function ($scope, $routeParams, $interval, InternstellarService) {
    var base = $routeParams.base;

    $scope.planets = [];

    $scope.updatePlanets = function (cidade) {
      InternstellarService.getPlanetUpdates(cidade).then(function (data) {
        $scope.planets = data.items;
      }, function (err) {
        console.log(err);
      });
    };

    base = base.toLowerCase();
    if (base == "campinas") {
      base = "Campinas";
    } else if (base == "bh") {
      base = "BH";
    } else {
      base = "";
    }

    $scope.updatePlanets(base);

    $interval(function () {
      $scope.updatePlanets(base);
    }, $scope.updateInterval);

  }]);
