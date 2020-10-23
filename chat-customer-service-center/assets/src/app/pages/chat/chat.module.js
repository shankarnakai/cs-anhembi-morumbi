(function() {
  'use strict';

  angular.module('BlurAdmin.pages.chat', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('chat', {
        url: '/chat',
        templateUrl: 'app/pages/chat/chat.html',
        title: 'Chat',
        controller: 'ChatCtrl',
        sidebarMeta: {
          order: 800
        }
      });
  }
})();
