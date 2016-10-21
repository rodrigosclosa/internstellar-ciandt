'use strict';

angular.module('internstellarDashApp')
  .controller('DashCtrl', ['$scope', '$routeParams', '$interval', 'InternstellarService', function ($scope, $routeParams, $interval, InternstellarService) {
    var base = $routeParams.base;

    $scope.items = [];

    $scope.updateBoard = function (cidade) {
      InternstellarService.getLastUpdates(cidade).then(function (data) {
        console.log(data);
        $scope.items = data.items;
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

    console.log(base);

    $scope.updateBoard(base);

    $interval(function () {
      $scope.updateBoard(base);
    }, $scope.updateInterval);

  }]);
