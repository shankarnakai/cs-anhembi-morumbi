(function() {
  'use strict';

  angular.module('BlurAdmin.pages.departaments', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('departaments', {
        url: '/departaments',
        templateUrl: 'app/pages/departaments/departaments.html',
        title: 'Departamentos',
        sidebarMeta: {
          order: 800,
        },
      });
  }

})();
