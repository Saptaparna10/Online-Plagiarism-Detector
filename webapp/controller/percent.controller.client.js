(function () {
    angular
        .module("PlagiarismDetector")
        .controller("PercentController", percentController);
    function percentController($routeParams, UserService, UploadService, HwService, NgTableParams, currentUser, $location) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }
        vm.hwId = $routeParams['hwid'];
        vm.studentId = $routeParams['sid'];
        vm.courseId = $routeParams['cid'];
        vm.algoId = $routeParams['aid'];

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
            UploadService.checkPlagiarism(vm.hwId, vm.studentId, vm.algoId)
                .then(function (resp) {
                    var data = resp.data;
                    vm.tableParams = new NgTableParams({
                        page: 1,
                        count: 10
                    }, {
                        filterDelay: 0,
                        counts: [],
                        dataset: data
                    });
                }, function (reason) {
                    vm.error = "There was error in parsing for one of the files."
                });
            UserService.findUserById(vm.studentId)
                .then(function (student) {
                    vm.student = student.data;
                });
            HwService.findHwById(vm.hwId)
                .then(function (hw) {
                    vm.hw = hw.data;
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