'use strict';
angular.module('app.controllers')

    .controller('GrupoController', function ($scope, EquipesMock, $cordovaCamera, $state, $stateParams, $ionicPopup, $filter) {

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

        //Crio minha função que aciona o plugin da Câmera do celular
        // $scope.tirarFoto = function () {
        //     console.log("Entrou camera");

        //     //Aqui eu aciono o plugin que inicializa a camera
        //     navigator.camera.getPicture(function (imagedata) {

        //         console.log(imagedata);

        //         //Depois de tirada a foto, ela é retornada neste objeto imagedata, como forma de URL interna do sistema
        //         if (typeof (imagedata) !== "undefined" && imagedata !== "") {

        //             //Se a foto foi tirada, aciono um outro plugin que redimensiona a foto para ocupar menos espaço ao enviar pra API
        //             window.plugins.imageResizer.resizeImage(
        //                 function (data) {
        //                     console.log(data);
        //                     //Imagem em formato base64
        //                     $scope.grupo.fotoEquipe.value = "data:image/jpeg;base64," + data.imageData;

        //                     console.log($scope.grupo.fotoEquipe.value);

        //                     //Altero a flag da foto para fazer ela aparecer na tela
        //                     $scope.mostrarFoto = true;
        //                     //Crio uma variável para controlar quando meu botão de enviar está ativo ou não
        //                     $scope.botaoDisabled = false;

        //                     console.log($scope.mostrarFoto);
        //                     console.log($scope.botaoDisabled);
        //                 },
        //                 function (error) {
        //                     //Se der algum erro ao tentar redimensionar a imagem, exibe a mensagem
        //                     $ionicPopup.alert({
        //                         title: 'Oops',
        //                         template: 'Erro ao redimensionar imagem : \r\n' + error
        //                     });

        //                     $scope.grupo.fotoEquipe.value = "";
        //                     $scope.mostrarFoto = false;
        //                     $scope.botaoDisabled = true;

        //                     console.log($scope.mostrarFoto);
        //                     console.log($scope.botaoDisabled);

        //                 }, imagedata, 800, 0, {
        //                     resizeType: ImageResizer.RESIZE_TYPE_MAX_PIXEL,
        //                     imageDataType: ImageResizer.IMAGE_DATA_TYPE_URL,
        //                     format: ImageResizer.FORMAT_JPG,
        //                     quality: 70
        //                 }
        //             );
        //         }

        //     },
        //         function (message) {
        //             //Se der algum erro ao tentar acionar a câmera ou você cancelar a foto, exibe esta mensagem
        //             $ionicPopup.alert({
        //                 title: 'Oops',
        //                 template: 'Erro ao acionar a câmera ou você cancelou a foto: \r\n' + message
        //             });

        //             $scope.grupo.fotoEquipe.value = "";
        //             $scope.mostrarFoto = false;
        //             $scope.botaoDisabled = true;
        //         },
        //         {
        //             //Configurações do plugin da Camera para tirar a foto
        //             quality: 80,
        //             destinationType: Camera.DestinationType.FILE_URI,
        //             sourceType: Camera.PictureSourceType.CAMERA,
        //             encodingType: Camera.EncodingType.JPEG,
        //             targetWidth: 1024,
        //             targetHeight: 768,
        //             cameraDirection: Camera.Direction.BACK
        //         });
        // };

    });