'use strict';

/**
 * @ngdoc service
 * @name internstellarDashApp.InternstellarService
 * @description
 * # InternstellarService
 * Service in the internstellarDashApp.
 */
angular.module('internstellarDashApp')
  .service('InternstellarService', ['$http', '$q', function ($http, $q) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    return {
      getLastUpdates: function (cidade) {
        var defer = $q.defer();

        var token = 'edb011adff20426e89536c69ef58b087';

        $http.get('https://internstellar-ciandt.appspot.com/_ah/api/'
          + 'sumarios/v1/getSumario?base=' + cidade + '&token=' + token)
          .success(function (data) {
            console.log(data);
            defer.resolve(data);
          })
          .error(function (err, status) {
            console.log(err);
            defer.reject(err);
          });

        return defer.promise;
      },
      getPlanetUpdates: function (cidade) {
        var defer = $q.defer();

        var token = 'edb011adff20426e89536c69ef58b087';

        $http.get('https://internstellar-ciandt.appspot.com/_ah/api/'
          + 'planetas/v1/get?base=' + cidade + '&token=' + token)
          .success(function (data) {
            defer.resolve(data);
          })
          .error(function (err, status) {
            defer.reject(err);
          });

        return defer.promise;
      }
    }
  }]);
