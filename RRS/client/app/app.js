/**
 * Created by ICEMAN on 12/6/15.
 */

(function(){

    angular.module('hotelapp',['ngRoute','ngMessages']);


    angular.module('hotelapp').config(moduleConfig);

    moduleConfig.$inject = ['$routeProvider' ];

    function moduleConfig ($routeprovider) {

        $routeprovider
            .when('/', {
                templateUrl: 'app/views/home.html',
                controller: 'HomeController',
                controllerAs: 'homeVm'

            })
            .when('/reservation', {
                templateUrl: 'app/views/reservation.html',
                controller: 'ReservationController',
                controllerAs: 'reservationVm'
            })

            .when('/admin', {
                templateUrl: 'app/views/admin.html',
                controller: 'AdminController',
                controllerAs: 'adminVm'

            })
            .when('/about', {
                templateUrl: 'app/views/about-us.html',
                controller: 'AboutController',
                controllerAs: 'aboutVm'
            })

            .when('/contact', {
                templateUrl: 'app/views/contact-us.html',
                controller: 'ContactController',
                controllerAs: 'contactVm'
            })

            .when('/confirmation', {
                templateUrl: 'app/views/confirmation.html',
                controller: 'ConfirmationController',
                controllerAs: 'confirmationVm'

            })

            .when('/changeReservation', {
                templateUrl: 'app/views/changeReservation.html',
                controller: 'ChangeReservationController',
                controllerAs: 'changeReservationVm'

            })

            .when('/error', {
                templateUrl: 'app/views/error.html',
                controller: 'ErrorController',
                controllerAs: 'errorVm'

            })

            .when('/editReservation', {
                templateUrl: 'app/views/editReservation.html',
                controller: 'EditReservationController',
                controllerAs: 'editReservationVm'

            })

            .when('/editconfirmation', {
                templateUrl: 'app/views/editconfirmation.html',
                controller: 'EditConfirmationController',
                controllerAs: 'editConfirmationVm'

            })
            .when('/admin/settings', {
                templateUrl: 'app/views/adminSettings.html',
                controller: 'AdminSettingsController',
                controllerAs: 'adminSettingsVm'

            })


            .when('/admin/profile', {
                templateUrl: 'app/views/profile.html',
                controller: 'AdminProfileController',
                controllerAs: 'adminProfileVm'

            })

            .when('/admin/visitors', {
                templateUrl: 'app/views/visitors.html',
                controller: 'AdminVisitorsController',
                controllerAs: 'adminVisitorsVm'

            })

            .when('/admin/settings/modifyreservation', {
                templateUrl: 'app/views/adminModifyReservation.html',
                controller: 'AdminModifyReservationController',
                controllerAs: 'adminModifyReservationVm'

            })
    };






})();