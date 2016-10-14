'use strict';

angular.module('internstellarDashApp')
  .controller('CampinasCtrl', ['$scope', '$interval', function ($scope, $interval) {
    $scope.items = [];

    $scope.updateBoard('campinas');

    $interval(function() {
      $scope.updateBoard('campinas');
    }, $scope.updateInterval);

  }]);
