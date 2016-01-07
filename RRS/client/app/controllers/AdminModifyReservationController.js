/**
 * Created by ICEMAN on 12/8/15.
 */
(function(){

    angular.module('hotelapp').controller('AdminModifyReservationController', ['adminSettingsServiceVm','reservationService','$location',
        function AdminModifyReservationController(adminSettingsServiceVm,reservationService,$location) {
            adminModifyReservationVm = this;

            adminModifyReservationVm.id = adminSettingsServiceVm.id;
            adminModifyReservationVm.name = adminSettingsServiceVm.name;
            adminModifyReservationVm.email = adminSettingsServiceVm.email;
            adminModifyReservationVm.contact = adminSettingsServiceVm.contact;
            adminModifyReservationVm.guestcount = adminSettingsServiceVm.guestcount;
            adminModifyReservationVm.tableno = adminSettingsServiceVm.tableno;
            adminModifyReservationVm.date = adminSettingsServiceVm.date;
            adminModifyReservationVm.time = adminSettingsServiceVm.time;
            adminModifyReservationVm.status = adminSettingsServiceVm.status;

            adminModifyReservationVm.modifyReservation = function(){

                reservationService.adminEditReservationDetailsByID(adminModifyReservationVm).then(
                    function (result){
                        adminModifyReservationVm.result = result;
                        adminModifyReservationVm.error = null;
                        $location.path('/editconfirmation');

                    },
                    function (error){
                        adminModifyReservationVm.result = null;
                        adminModifyReservationVm.error = error;
                        $location.path('/error');
                    }

                );
            };


        }]);



}());