'use strict';

/**
 * @ngdoc overview
 * @name internstellarDashApp
 * @description
 * # internstellarDashApp
 *
 * Main module of the application.
 */
angular
  .module('internstellarDashApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/campinas', {
        templateUrl: 'views/campinas.html',
        controller: 'CampinasCtrl',
        controllerAs: 'campinas'
      })
      .when('/bh', {
        templateUrl: 'views/bh.html',
        controller: 'BhCtrl',
        controllerAs: 'bh'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
