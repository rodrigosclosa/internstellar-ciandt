angular.module('app.routes', [])

  .config(function ($stateProvider, $urlRouterProvider) {

    // Ionic uses AngularUI Router which uses the concept of states
    // Learn more here: https://github.com/angular-ui/ui-router
    // Set up the various states which the app can be in.
    // Each state's controller can be found in controllers.js
    $stateProvider

      // setup an abstract state for the tabs directive
      .state('tab', {
        url: '/tab',
        abstract: true,
        templateUrl: 'templates/tabs.html'
      })

      // Each tab has its own nav history stack:

      .state('tab.grupo', {
        url: '/grupo',
        views: {
          'grupo': {
            templateUrl: 'templates/grupo.html',
            controller: 'GrupoController'
          }
        }
      })

      .state('tab.planetas', {
        url: '/planetas',
        views: {
          'planetas': {
            templateUrl: 'templates/planetas.html',
            controller: 'PlanetasController'
          }
        }
      })
      .state('tab.planetas-perguntas', {
        url: '/planetas/:planetaId',
        views: {
          'planetas': {
            templateUrl: 'templates/planetas-perguntas.html',
            controller: 'PlanetasPerguntasController'
          }
        }
      })

      .state('tab.login', {
        url: '/login',
        views: {
          'login': {
            templateUrl: 'templates/login.html',
            controller: 'LoginController'
          }
        }
      });

    // if none of the above states are matched, use this as the fallback
    $urlRouterProvider.otherwise('/tab/grupo');

  });