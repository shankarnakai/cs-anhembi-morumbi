(function() {
  'use strict';

  angular.module('BlurAdmin.pages.users', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('users', {
        url: '/user',
        templateUrl: 'app/pages/users/users.html',
        title: 'Usuários',
        sidebarMeta: {
          order: 800,
        },
      });
  }

})();
