'use strict';
angular.module('app.controllers')
    .controller('LoginController', function ($scope) {
        $scope.settings = {
            enableFriends: true
        };
    });