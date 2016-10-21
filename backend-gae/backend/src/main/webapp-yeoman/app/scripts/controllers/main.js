'use strict';

/**
 * @ngdoc function
 * @name internstellarDashApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the internstellarDashApp
 */
angular.module('internstellarDashApp')
  .controller('MainCtrl', ['$scope', 'InternstellarService', function ($scope, InternstellarService) {

    $scope.updateInterval = 1000 * 60 * 5;

  }]);
