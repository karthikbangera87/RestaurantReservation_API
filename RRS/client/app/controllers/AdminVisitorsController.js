/**
 * Created by ICEMAN on 12/7/15.
 */
(function(){

    angular.module('hotelapp').controller('AdminVisitorsController',['adminVisitorService', function AdminVisitorsController(adminVisitorService) {
        adminVisitorsVm = this;
        console.log("Inside admin AdminVisitorsController");

        adminVisitorsVm.checkVisitLogTime = function(){
            adminVisitorService.getVisitorsByDateTime(adminVisitorsVm).then(
                function (result){
                    adminVisitorsVm.result = result;


                },
                function (error){
                    adminVisitorsVm.error = error;

                }
            );
        };
        adminVisitorsVm.checkVisitLogEmail = function(){
            adminVisitorService.getVisitorsByEmail(adminVisitorsVm.email).then(
                function (result){
                    adminVisitorsVm.result = result;


                },
                function (error){
                    adminVisitorsVm.error = error;

                }

            );


        };
    }]);



}());