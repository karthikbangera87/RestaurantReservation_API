/**
 * Created by ICEMAN on 12/8/15.
 */
(function(){

    angular.module('hotelapp').service('adminVisitorService',['$http','$q', function adminVisitorService($http,$q) {
        adminVisitorServiceVm = this;

        adminVisitorServiceVm.getVisitorsByDateTime = function(datetimeobj){
            console.log("Get visitor logs by date and time");
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/HotelApp/api/admin/findall/'
                    +datetimeobj.date+'/'+datetimeobj.frtime +'/'+datetimeobj.totime)
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

        adminVisitorServiceVm.getVisitorsByEmail = function(email){
            console.log("Get visitors log by email");
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/HotelApp/api/admin/findall/' + email)
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