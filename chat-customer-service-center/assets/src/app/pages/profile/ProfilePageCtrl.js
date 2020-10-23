/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function() {
  'use strict';

  angular.module('BlurAdmin.pages.profile')
    .controller('ProfilePageCtrl', ProfilePageCtrl);

  /** @ngInject */
  function ProfilePageCtrl($scope, $filter, $uibModal, $http, UserCurrent) {

    UserCurrent.getUser(function(user) {
      $scope.user = user;
    });

    $scope.data = {
      pass: ""
    };

    $http.get('organization/' + $scope.user.orgID).then(function(response) {
      $scope.organization = response.data.model;
    });


    $http.get('departament/list').then(function(response) {
      $scope.departaments = response.data.departaments;
    });


    $scope.update = function() {
      $http.post('user/' + $scope.user.id, $scope.user).then(function(response) {
        console.log("return "+ response.data.result);
        if (response.data.result == true) {
          UserCurrent.reload(); 
          alert("Dados salvos com sucesso");

          if ($scope.data.pass != "") {
            console.log("saving password");
            $http.post('user/password/' + $scope.user.id, $scope.data).then(function(response) {
              UserCurrent.reload(); 
              console.log("saved !!");
            }, function(error) {
              alert(error);
            });
          }
        } else {
          alert(response.data.message);
        }
      }, function(error) {
        console.log(error);
      });

    }
  }
})();
