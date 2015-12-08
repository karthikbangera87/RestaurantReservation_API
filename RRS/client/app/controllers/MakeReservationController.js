/**
 * Created by ICEMAN on 12/6/15.
 */

(function(){

    angular.module('hotelapp').controller('ReservationController',['$location','reservationService',
        function ReservationController($location,reservationService ) {
        console.log("Inside reservation controller");
        reservationVm = this;

        reservationVm.addReservation = function (){
            console.log("Inside add reservation");
            reservationService.makeReservation(reservationVm).then(
                function (result){
                    reservationVm.result = result;

                    $location.path('/confirmation');

                },
                function (error){
                    console.log(error)
                    $location.path('/error');
                }

            );


        }



    }]);



}());


