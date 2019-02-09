(function () {
    angular
        .module("PlagiarismDetector")
        .controller("RegisterController", registerController);
    function registerController($location, UserService) {
        var vm = this;
        vm.isDisabled = false;

        /*event handlers*/
        vm.register = register;
        vm.openNav = openNav;
        vm.closeNav = closeNav;

        function init() {
            $(window).width(function() {
                if ($(this).width() <= 768) {
                    closeNav();
                }
                else{
                    openNav();
                }
            });
            closeNav();
        }
        init();

        function openNav() {
            vm.open = null;
            document.getElementById("mySidenav").style.width = "250px";
            document.getElementById("main").style.marginLeft = "250px";
        }

        function closeNav() {
            vm.open = "open";
            document.getElementById("mySidenav").style.width = "0";
            document.getElementById("main").style.marginLeft = "0";
        }

        function register(user) {
            vm.isDisabled = true;
            UserService
                .register(user)
                .then(function(response) {
                    $location.url("/profile");
                    vm.isDisabled = false;
                }, function (reason) {
                    vm.error = reason.data.message;
                    vm.isDisabled = false;
                });
        }
    }
})();