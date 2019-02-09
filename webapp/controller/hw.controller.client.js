(function () {
    angular
        .module("PlagiarismDetector")
        .controller("HwController", hwController);
    function hwController($routeParams, HwService, currentUser, $location, UserService) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }
        vm.courseId = $routeParams['cid'];

        /*event handlers*/
        vm.openNav = openNav;
        vm.closeNav = closeNav;
        vm.logout = logout;

        function logout() {
            UserService.logout()
                .then(function (value) {
                    $location.url("/login");
                }, function (reason) {
                    vm.error = "There was a prob logging out. Please contact admin.";
                });
        }

        function init() {
            HwService.findHwByCourseId(vm.courseId)
                .then(function (hws) {
                    if(hws.data.length == 0) {
                        vm.message = "No Homework folders.";
                    }
                    vm.hws = hws.data;
                }, function (err) {
                    vm.error = err.data.message;
                });

            openNav();
            $(window).width(function() {
                if ($(this).width() <= 768) {
                    closeNav();
                }
                else{
                    openNav();
                }
            });
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
    }
})();