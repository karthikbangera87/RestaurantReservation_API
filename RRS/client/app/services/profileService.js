/**
 * Created by ICEMAN on 12/8/15.
 */
(function(){

    angular.module('hotelapp').service('profileService',['$http','$q', function profileService($http,$q) {
        profileServiceVm = this;

        profileServiceVm.getProfile = function(){
            console.log("Inside get restaurant profile");
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/HotelApp/api/restaurant')
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

        profileServiceVm.changeProfile = function(changeobj){
            console.log("Inside change restaurant profile");
            var defer = $q.defer();

            $http
                .post('http://localhost:8080/HotelApp/api/restaurant',changeobj)
                .then(successFn, errorFn);

            function successFn(response) {

                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;
        };


    }]);



}());