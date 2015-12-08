/**
 * Created by ICEMAN on 12/7/15.
 */
(function(){


    angular.module('hotelapp').controller('EditConfirmationController', ['reservationEditService', function ConfirmationController(reservationEditService) {
        editConfirmationVm = this;
        editConfirmationVm.id = reservationEditService.id


    }]);

}());