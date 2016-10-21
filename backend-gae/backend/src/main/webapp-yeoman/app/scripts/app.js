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
      .when('/dash/:base', {
        templateUrl: 'views/dash.html',
        controller: 'DashCtrl',
        controllerAs: 'dash'
      })
      .when('/aval/:base', {
        templateUrl: 'views/aval.html',
        controller: 'AvalCtrl',
        controllerAs: 'aval'
      })
      .when('/planetas/:base', {
        templateUrl: 'views/planetas.html',
        controller: 'PlanetasCtrl',
        controllerAs: 'planetas'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
