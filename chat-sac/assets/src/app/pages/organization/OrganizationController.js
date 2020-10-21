(function() {
  'use strict';

  angular.module('BlurAdmin.pages.organization')
    .controller('OrganizationCtrl', OrganizationCtrl);

  /** @ngInject */
  function OrganizationCtrl($scope, $filter, $uibModal, $http, UserCurrent) {
    UserCurrent.getUser(function(user) {
      $scope.user = user;

      $http.get('organization/' + $scope.user.orgID).then(function(response) {

        $scope.organization = response.data.model;
        if ($scope.organization.facebook_token != "") {
          $http.get('organization/list-pages').then(function(response) {
            $scope.pages = response.data.pages;
          });
        }
      });
    });

    $scope.data = {
      pass: ""
    };

    $scope.organization = {
      name: "lorem ipsum dolor"
    };


    $scope.connect = function() {
      $http.get('organization/connect').then(function(response) {
        if (response.data) {
          var win = window.open(response.data, "_blank");
          win.focus();
        } else {
          console.log("Listar páginas");
          $http.get('organization/list-pages').then(function(response) {
            $scope.pages = response.pages;
          });
        }
      }, function(error) {
        alert(error.data);
      });
    }

    $scope.update = function() {
      $http.post('organization/' + $scope.organization.id, $scope.organization).then(function(response) {
        console.log("return " + response.data.result);
        if (response.data.result == true) {
          alert("Dados salvos com sucesso");
        } else {
          alert(response.data.message);
        }
      }, function(error) {
        console.log(error);
      });
    }
  }
})();
