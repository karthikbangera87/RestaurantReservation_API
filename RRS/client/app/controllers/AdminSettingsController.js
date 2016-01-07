/**
 * Created by ICEMAN on 12/7/15.
 */
(function(){

    angular.module('hotelapp').controller('AdminSettingsController',['adminSettingsService', function AdminSettingsController(adminSettingsService) {
        console.log("Inside admin settings controller");
        adminSettingsVm = this;

        adminSettingsVm.getReservationsByDateTime = function(){

            adminSettingsService.getReservationsByDateTime(adminSettingsVm).then(
                function (result){
                    adminSettingsVm.reservations = result;


                },
                function (error){
                    adminSettingsVm.error = error;

                }
            );

            adminSettingsService.getTableAvailability(adminSettingsVm).then(
                function (result){
                    adminSettingsVm.tables = result;


                },
                function (error){
                    adminSettingsVm.error = error;

                }
            );

        }
    }]);



}());