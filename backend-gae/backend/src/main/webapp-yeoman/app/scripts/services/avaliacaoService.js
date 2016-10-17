'use strict';

angular.module('internstellarDashApp')
  .service('AvaliacaoService', ['$http', '$q', function ($http, $q) {
    // AngularJS will instantiate a singleton by calling "new" on this function
    var token = 'edb011adff20426e89536c69ef58b087';

    return {
      gatAllGrupos: function (cidade) {
        cidade = cidade.toLowerCase();

        if (cidade == "campinas") {
          cidade = "Campinas";
        } else if (cidade == "bh") {
          cidade = "BH";
        } else {
          cidade = "";
        }

        console.log(cidade);

        var defer = $q.defer();

        $http.get('https://internstellar-ciandt.appspot.com/_ah/api/'
          + 'grupos/v1/get?cidade=' + cidade)
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
      sendData: function (form) {
        var defer = $q.defer();

        $http.post('https://internstellar-ciandt.appspot.com/_ah/api/'
          + 'avaliacoes/v1/new?token=' + token,
          form)
          .success(function (data) {
            console.log(data);
            defer.resolve(data);
          })
          .error(function (err, status) {
            console.log(err);
            defer.reject(err);
          });

        return defer.promise;
      }
    }
  }]);
