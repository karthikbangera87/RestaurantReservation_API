/**
 * Created by ICEMAN on 12/8/15.
 */
(function(){

    angular.module('hotelapp').service('adminSettingsService',['$http','$q', function adminSettingsService($http,$q) {
        adminSettingsServiceVm = this;

        adminSettingsServiceVm.id ='';
        adminSettingsServiceVm.name= '';
        adminSettingsServiceVm.email='';
        adminSettingsServiceVm.contact='';
        adminSettingsServiceVm.guestcount='';
        adminSettingsServiceVm.tableno='';
        adminSettingsServiceVm.date='';
        adminSettingsServiceVm.time='';
        adminSettingsServiceVm.status='';
        adminSettingsServiceVm.getReservationsByDateTime = function (datetimeobj) {
            console.log("Get admin reservation logs by date and time");
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/HotelApp/api/admin/findall/'
                    + datetimeobj.date + '/' + datetimeobj.frtime+'/'+datetimeobj.totime)
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

        adminSettingsServiceVm.getTableAvailability = function (datetimeobj) {
            console.log("Get  admin table availability");
            var defer = $q.defer();

            $http
                .get('http://localhost:8080/HotelApp/api/admin/findAvailableTables/'
                    + datetimeobj.date + '/' + datetimeobj.frtime+'/'+datetimeobj.totime)
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