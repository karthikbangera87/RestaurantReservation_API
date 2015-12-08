/**
 * Created by ICEMAN on 12/7/15.
 */

(function(){

    angular.module('hotelapp').controller('ChangeReservationController', ['reservationService','reservationEditService',
        function ChangeReservationController(reservationService,reservationEditService) {

        changeReservationVm = this;

        changeReservationVm.checkReservation = function(){

            reservationService.getReservationDetailsByID(changeReservationVm.id).then(
                function (result){
                    changeReservationVm.result = result;
                    reservationEditService.id = changeReservationVm.id;
                    reservationEditService.name = result.customer_name;
                    reservationEditService.contact = result.phone;
                    reservationEditService.email = result.email;
                    reservationEditService.guestcount = result.party_size;
                    changeReservationVm.error = null;



                },
                function (error){
                    changeReservationVm.result = null;
                    changeReservationVm.error = error;
                }

            );

        };

        changeReservationVm.cancelReservation = function(){

            reservationService.cancelReservationDetailsByID(changeReservationVm.id).then(
                function (result){
                    changeReservationVm.result = result;
                    changeReservationVm.error = null;

                },
                function (error){
                    changeReservationVm.result = null;
                    changeReservationVm.error = error;
                }

            );

        };



    }]);



}());
