/**
 * Created by ICEMAN on 12/7/15.
 */
(function(){

    angular.module('hotelapp').controller('EditReservationController', ['reservationEditService','reservationService','$location',
        function EditReservationController(reservationEditService,reservationService,$location) {
        editReservationVm = this;
        editReservationVm.id = reservationEditService.id;
        editReservationVm.name = reservationEditService.name;
        editReservationVm.email = reservationEditService.email;
        editReservationVm.contact = reservationEditService.contact;
        editReservationVm.guestcount = reservationEditService.guestcount;

        editReservationVm.alterReservation = function(){

            reservationService.editReservationDetailsByID(editReservationVm).then(
                function (result){
                    editReservationVm.result = result;
                    editReservationVm.error = null;
                    $location.path('/editconfirmation');

                },
                function (error){
                    editReservationVm.result = null;
                    editReservationVm.error = error;
                    $location.path('/editconfirmation');
                }

            );
        };


    }]);



}());