/**
 * Created by ICEMAN on 12/7/15.
 */
(function(){

    angular.module('hotelapp').controller('AdminController',['loginService','$location', function AdminController(loginService,$location) {
        adminVm = this;
        console.log("Inside admin controller");
        adminVm.login = function(){

            loginService.validateLogin(adminVm).then(
                function (result){
                    adminVm.result = result;
                    $location.path('/admin/settings');

                },
                function (error){
                    console.log(error)
                    //$location.path('/error');
                    $location.path('/admin/settings');
                }

            );
        };


    }]);



}());