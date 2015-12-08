/**
 * Created by ICEMAN on 12/7/15.
 */
(function(){

    angular.module('hotelapp').service('loginService',['$http','$q', function loginService($http,$q) {
        loginServiceVm = this;

        loginServiceVm.validateLogin = function(loginobj){
            console.log("Inside login service");
            var defer = $q.defer();

            $http
                .post('http://localhost:8080/HotelApp/api/authentication/login', loginobj)
                .then(successFn, errorFn);

            function successFn(response) {
                console.log(response.data);
                defer.resolve(response.data);
            }

            function errorFn(error) {
                console.log(error.statusText);
                defer.reject(error.statusText);
            }

            return defer.promise;
        };

    }]);



}());