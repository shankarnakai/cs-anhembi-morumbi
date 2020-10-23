(function() {
  'use strict';

  angular.module('BlurAdmin.pages.organization', [])
    .config(routeConfig);

  /** @ngInject */
  function routeConfig($stateProvider) {
    $stateProvider
      .state('organization', {
        url: '/organization',
        templateUrl: 'app/pages/organization/organization.html',
        title: 'Organização',
        controller: 'OrganizationCtrl',
        sidebarMeta: {
          order: 800,
        },
      });
  }

})();
