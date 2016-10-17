'use strict';

angular.module('internstellarDashApp')
  .controller('AvalCtrl', ['$scope', '$routeParams', 'AvaliacaoService', function ($scope, $routeParams, AvaliacaoService) {

    var base = $routeParams.base;

    $scope.cidade = base;

    $scope.aval = {
      idGrupo: "",
      loginSensei: "",
      desafio: 1,
      notes: ""
    };

    AvaliacaoService.gatAllGrupos(base).then(function (data) {
      $scope.grupos = data.items;
    }, function (err) {
      console.log(err);
      bootbox.alert('Ocorreu um erro ao tentar carregar os grupos da cidade ' + base + ': \r\n' + err.error.message);
    });

    $scope.enviar = function (dados) {
      console.log(dados);

      bootbox.confirm({
        title: "Confirmar",
        message: "Confirma esta avaliação?",
        buttons: {
          cancel: {
            label: '<i class="fa fa-times"></i> Cancel'
          },
          confirm: {
            label: '<i class="fa fa-check"></i> Confirm'
          }
        },
        callback: function (result) {
          if (result) {
            AvaliacaoService.sendData(dados).then(function (data) {
              console.log(data);
              var dataFormatada = data.dataFormatada;

              bootbox.alert('Sua avaliação foi enviada com sucesso. Data/hora: ' + dataFormatada);

            }, function (err) {
              console.log(err);
              bootbox.alert('Erro ao enviar os dados da avaliação: \r\n' + err.error.message);
            });
          }
        }
      });
    };

  }]);
