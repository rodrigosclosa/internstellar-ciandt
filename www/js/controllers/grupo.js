'use strict';
angular.module('app.controllers')

    .controller('GrupoController', function ($scope, EquipesMock, ExemploService, $cordovaCamera, $state, $stateParams, $ionicPopup, $filter) {

        $scope.dadosExemplo = [];

        //Crio um objeto vazio que representa meu objeto de Grupo da API para enviar a ela depois.
        $scope.grupo = {
            "fotoEquipe": {
                "value": ""
            },
            "idEquipe": "",
            "nome": ""
        };

        //Para controlar o meu botão de gravar. Somente quando todo o formulário estiver completo e os dados validados eu ativo ele.
        $scope.botaoDisabled = true;

        //Recupero as equipes para preencher o campo select. 
        //Você vai precisar criar um servico pra trazer as equipes da API.
        $scope.equipes = EquipesMock.all();

        //Crio minha função que aciona o plugin da Câmera do celular
        $scope.tirarFoto = function () {
            var options = {
                quality: 75,
                destinationType: Camera.DestinationType.DATA_URL,
                sourceType: Camera.PictureSourceType.CAMERA,
                encodingType: Camera.EncodingType.JPEG,
                targetWidth: 800,
                targetHeight: 600,
                popoverOptions: CameraPopoverOptions,
                saveToPhotoAlbum: false
            };

            $cordovaCamera.getPicture(options).then(function (imageData) {
                $scope.grupo.fotoEquipe.value = "data:image/jpeg;base64," + imageData;
            }, function (err) {
                //Se der algum erro ao tentar acionar a câmera ou você cancelar a foto, exibe esta mensagem
                $ionicPopup.alert({
                    title: 'Oops',
                    template: 'Erro ao acionar a câmera ou você cancelou a foto: \r\n' + message
                });

                $scope.grupo.fotoEquipe.value = "";
                $scope.botaoDisabled = true;
            });
        };

        //Aqui um exemplo de como utilizar o meu serviço
        ExemploService.all(function (dados) {
            if (dados.success) {
                //Preencho meu objeto com os dados que foram retornados a API
                $scope.dadosExemplo = dados.items;
                console.log($scope.dadosExemplo);
            } else {
                //Exibo uma mensagem para informar que ocorreu um erro
                $ionicPopup.alert({
                    title: 'Erro ao recuperar os dados',
                    template: 'Desculpe, mas ocorreu um erro ao tentar recupera os dados. Mensagem: ' + retorno.erro.mensagem
                });
            }
        });

    });