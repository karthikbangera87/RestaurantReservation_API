/**
 * Created by ICEMAN on 12/7/15.
 */
(function(){


    angular.module('hotelapp').controller('ConfirmationController', ['confirmationGenerator', function ConfirmationController(confirmationGenerator) {
        confirmationVm = this;
        confirmationVm.id = confirmationGenerator.id


    }]);

}());