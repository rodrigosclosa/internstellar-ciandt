'use strict';

angular.module('internstellarDashApp')
  .controller('BhCtrl', ['$scope', '$interval', function ($scope, $interval) {
    $scope.items = [];

    $scope.updateBoard('bh');

    $interval(function() {
      $scope.updateBoard('bh');
    }, $scope.updateInterval);

  }]);
