(function () {
    angular
        .module("PlagiarismDetector")
        .controller("LoginController", loginController);
    function loginController($location, UserService) {
        var vm = this;

        /*event handlers*/
        vm.login = login;
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

        function login(user) {
            UserService
                .login(user)
                .then(function (response) {
                    $location.url("/profile");
                }, function (err) {
                    vm.error = err.data.message;
                });
        }
    }
})();