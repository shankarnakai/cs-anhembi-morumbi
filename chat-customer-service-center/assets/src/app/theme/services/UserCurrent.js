(function() {
  'use strict';
  angular.module('BlurAdmin.theme')
    .service('UserCurrent', ['$http', function($http) {

      var that = this;

      function reload() {
        that.currentUser = null;
        getUser(function(user) {
          console.log("Reload User");
        });
      }

      function getUser(callback) {

        //JUST FOR TEST
        var url = window.location.href;
        if (url.indexOf("localhost:3000") > 0) {
          that.currentUser = {
            Name: "Anonymous",
            Email: "anonymous@gmail.com"
          };
        }

        if (that.currentUser) {
          callback(that.currentUser);
        } else {
          $http.get('user/current').then(function(response) {
            that.currentUser = response.data.user;
            if (!that.currentUser) {
              window.location = "authentication.html";
            }
            callback(that.currentUser);
          });
        }
      }

      function setUser(user) {
        that.currentUser = user;
      }
      return {
        getUser: getUser,
        setUser: setUser,
        reload: reload
      }
    }]);
})();
