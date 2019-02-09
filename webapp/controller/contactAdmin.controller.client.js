(function () {
    angular
        .module("PlagiarismDetector")
        .controller("ContactAdminController", contactAdminController);
    function contactAdminController($routeParams, AdminService, currentUser, $location, UserService) {
        var vm = this;
        vm.open = true;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }

        /*event handlers*/
        vm.openNav = openNav;
        vm.closeNav = closeNav;
        vm.contactAdmin = contactAdmin;
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
        
        function contactAdmin(note) {
            if(!vm.userId){
                vm.userId=0;
            }
            AdminService.contactAdmin(vm.userId, note)
                .then(function (res) {
                    if(res){
                        vm.message = "Message sent. Admin will contact you soon.";
                        vm.error = null;
                        window.scrollTo(0, 0);
                    }
                    else{
                        vm.error = "There was some error. Please try again.";
                        vm.message = null;
                    }
                });
        }

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

        $(window).resize(function() {
            if ($(this).width() <= 768) {
                closeNav();
            }
            else{
                openNav();
            }
        });
    }
})();