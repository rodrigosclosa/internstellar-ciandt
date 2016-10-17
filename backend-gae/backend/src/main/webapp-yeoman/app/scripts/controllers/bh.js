'use strict';

angular.module('internstellarDashApp')
  .controller('BhCtrl', ['$scope', '$interval', function ($scope, $interval) {
    $scope.items = [];

    $scope.updateBoard('BH');

    $interval(function() {
      $scope.updateBoard('BH');
    }, $scope.updateInterval);

  }]);
