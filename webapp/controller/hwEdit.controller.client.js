(function () {
    angular
        .module("PlagiarismDetector")
        .controller("HwEditController", hwEditController);
    function hwEditController($routeParams, HwService, $location, currentUser, UserService) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }
        vm.courseId = $routeParams['cid'];
        vm.hwId = $routeParams['hwid'];

        /*event handlers*/
        vm.openNav = openNav;
        vm.closeNav = closeNav;
        vm.createUpdateHw = createUpdateHw;
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
                        vm.message = "No HW";
                    }
                    vm.hws = hws.data;
                });
            if(vm.hwId != "new"){
                HwService.findHwById(vm.hwId)
                    .then(function (hw) {
                        if(hw.data.length == 0) {
                            vm.message = "No HW";
                        }
                        vm.hw = hw.data;
                    });
            }
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

        function createUpdateHw(hw) {
            if(vm.hwId != "new"){
                HwService.updateHw(hw)
                    .then(function (resp) {
                        $location.url("/course/"+vm.courseId+"/hw");
                    }, function (err) {
                        vm.error = err.data.message;
                    });
            } else{
                HwService.createHw(vm.courseId, hw)
                    .then(function (resp) {
                        $location.url("/course/"+vm.courseId+"/hw");
                    }, function (err) {
                        vm.error = err.data.message;
                    });
            }
        }

        function closeNav() {
            vm.open = "open";
            document.getElementById("mySidenav").style.width = "0";
            document.getElementById("main").style.marginLeft = "0";
        }
    }
})();