/**
 * Created by ICEMAN on 12/7/15.
 */

(function(){

    angular.module('hotelapp').service('confirmationGenerator', function confirmationGenerator() {
        confirmationService = this;
        confirmationService.id = Math.floor((Math.random() * 1000000) + 1);
    });



}());