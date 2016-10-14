'use strict';

angular.module('internstellarDashApp')
  .controller('CampinasCtrl', ['$scope', '$interval', function ($scope, $interval) {
    $scope.items = [];

    $scope.updateBoard('Campinas');

    $interval(function() {
      $scope.updateBoard('Campinas');
    }, $scope.updateInterval);

  }]);
