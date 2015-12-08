/**
 * Created by ICEMAN on 12/7/15.
 */
(function(){

    angular.module('hotelapp').controller('AdminProfileController',['profileService',function AdminProfileController(profileService) {
        adminProfileVm = this;
        console.log("Inside admin AdminProfileController");
        profileService.getProfile().then(
            function (result){
                adminProfileVm.result = result;


            },
            function (error){
                adminProfileVm.error = error;

            }
        );

        adminProfileVm.changeProfile = function(adminProfileVm){
            profileService.getProfile().then(
                function (result){
                    adminProfileVm.result = result;


                },
                function (error){
                    adminProfileVm.error = error;

                }
            );

        };

    }]);



}());