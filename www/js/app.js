// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js
angular.module('app', ['ionic', 'ngCordova', 'app.config', 'app.routes', 'app.controllers', 'app.services', 'app.directives'])

  .run(function ($ionicPlatform) {
    $ionicPlatform.ready(function () {
      // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
      // for form inputs)
      if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
        cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
        cordova.plugins.Keyboard.disableScroll(true);
      }
      if (window.StatusBar) {
        // org.apache.cordova.statusbar required
        StatusBar.styleDefault();
      }

      var notificationOpenedCallback = function (jsonData) {
        console.log('Abriu uma notificação: ' + JSON.stringify(jsonData));
      };

      window.plugins.OneSignal
        .startInit("5d1aa582-7c6d-4b8d-96fd-4dacaac12ca5", "824247522381")
        .handleNotificationOpened(notificationOpenedCallback)
        .endInit();

      // Show an alert box if a notification comes in when the user is in your app.
      window.plugins.OneSignal.enableInAppAlertNotification(true);
    });
  });
