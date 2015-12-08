/**
 * Created by ICEMAN on 12/7/15.
 */

(function(){

    angular.module('hotelapp').service('reservationService',['$http','$q', function reservationService($http,$q) {
        reservationServiceVm = this;

        reservationServiceVm.getReservationDetailsByID = function(id){
            console.log("Inside reservation to getReservationDetailsByID");
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/HotelApp/api/reservation/find/' + id)
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

        reservationServiceVm.makeReservation = function(reservationobj){
            console.log("Inside make reservation");
            var defer = $q.defer();

            $http
                .post('http://localhost:8080/HotelApp/api/reservation/makeReservation',reservationobj)
                .then(successFn, errorFn);

            function successFn(response) {

                defer.resolve(response.data);
            }

            function errorFn(error) {
                defer.reject(error.statusText);
            }

            return defer.promise;
        };

        reservationServiceVm.cancelReservationDetailsByID = function(id){
            console.log("Inside cancelReservationDetailsByID");
            var defer = $q.defer();

            $http
                .delete('http://localhost:8080/HotelApp/api/reservation/cancelReservation/' + id)
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

        reservationServiceVm.editReservationDetailsByID = function(newReservationObj){
            console.log("Inside editReservationDetailsByID");
            var defer = $q.defer();

            $http
                .put('http://localhost:8080/HotelApp/api/reservation/editReservation/'+newReservationObj.id, newReservationObj)
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

        reservationServiceVm.adminEditReservationDetailsByID = function(newReservationObj){
            console.log("Inside admineditReservationDetailsByID");
            var defer = $q.defer();

            $http
                .put('http://localhost:8080/HotelApp/api/admin/editReservation/'+newReservationObj.id, newReservationObj)
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