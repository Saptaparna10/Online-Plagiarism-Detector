(function () {
    angular
        .module("PlagiarismDetector")
        .controller("ProfileController", profileController);
    function profileController($routeParams, UserService, currentUser, $location) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }

        /*event handlers*/
        vm.update = update;
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

        function update(user) {
            UserService.updateUser(user)
                .then(function (usr) {
                    if(usr){
                        vm.message = "Profile successfully updated";
                        vm.error = null;
                        window.scrollTo(0, 0);
                    }
                    else{
                        vm.error = "Unable to update Profile";
                        vm.message = null;
                    }
                });
        }
    }
})();