'use strict';

angular.module('BlurAdmin', [
  'ngAnimate',
  'ui.bootstrap',
  'ui.sortable',
  'ui.router',
  'ngTouch',
  'toastr',
  'smart-table',
  "xeditable",
  'ui.slimscroll',
  'ngJsTree',
  'angular-progress-button-styles',

  'BlurAdmin.theme',
  'BlurAdmin.pages'
]);


angular.module('Front', []);
angular.module('Front').controller('RegisterCtrl', ['$scope', '$http', function($scope, $http) {
  VMasker(document.getElementById("telefone")).maskPattern("(99) 99999-9999");
  VMasker(document.getElementById("cnpj")).maskPattern("999.999.999-99");

  $scope.message = "";
  $scope.model = {
    name: "",
    cnpj: "",
    phone: "",
    email: "",
    password: "",
  };

  $scope.changeCNPJ = function(id) {
    var input = document.getElementById(id);
    if (input == null || input == undefined) {
      return;
    }

    var value = input.value.replace(/[^0-9]+/g, '');
    if (value.length > 11) {
      VMasker(input).unMask(); // unmask!
      VMasker(input).maskPattern("99.999.999/9999-99");
    } else {
      VMasker(input).unMask(); // unmask!
      VMasker(input).maskPattern("999.999.999-99");
    }
  }

  $scope.Register = function(form) {
    if (form.$valid) {
      $http({
        method: 'post',
        url: 'register',
        data: $scope.model
      }).then(function(response) {
        if (response.data.result == true) {
          $scope.message = "Seu cadastro foi enviado com sucesso";
        } else {
          $scope.message = response.data.message;
        }
      }, function(response) {
        $scope.message = "Houve um erro no envio do seu formulário, por favor tente mais tarde";
      });
    }
    return false;
  }
}]);