/**
 * @author Shankar Nakai 
 */
(function() {
 'use strict';

  angular.module('BlurAdmin.pages.chat')
    .controller('ChatCtrl', ChatCtrl);

  /** @ngInject */
  function ChatCtrl($scope, $filter, $uibModal, $http, UserCurrent) {

    var reloadMessages= function() {
        $http.get('message/'+$scope.detail.ID).then(function(response) {
          $scope.messages = response.data.messages;
        });
    };

    var reloadTalks= function() {
        $http.get('message/list').then(function(response) {
          $scope.talks = response.data.talks;
        });
    };
    var tabControl = function(view) {
      if(view == "home") {
        $scope.tab = 1; 
      } else if(view == "detail") {
        $scope.tab = 2; 
      } 
    };
    $scope.tab = 1;
    $scope.detail = {
      ID :0 
    };


    $scope.model= {
      message:"" 
    };

    $scope.talks = [];

    $scope.messages = [
    ];

    UserCurrent.getUser(function(user) {
      $scope.user = user;
      reloadTalks();
    });

    $scope.view = function(id) {
       tabControl("detail"); 
       $scope.detail.ID = id;

       reloadMessages();
    }

    $scope.send = function() {
      if($scope.model.message == "") {
        return false; 
      }
      console.log("send message"); 

      var payload = {
        id : $scope.detail.ID,
        message : $scope.model.message
      }

      console.log($scope.model.message);
      $http.post('message/send/', payload).then(function(response){
        $scope.model.message = "";

       reloadMessages();

      });
    };
  }
})();
