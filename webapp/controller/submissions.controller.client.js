(function () {
    angular
        .module("PlagiarismDetector")
        .controller("SubmissionsController", submissionsController);
    function submissionsController($routeParams, UploadService, NgTableParams, $filter, currentUser, $location, UserService) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }
        vm.hwId = $routeParams['hwid'];
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
            UploadService.findUploadForHw(vm.hwId)
                .then(function (submissions) {
                    if(submissions.data.length == 0) {
                        vm.message = "No Submissions";
                    }
                    var data = submissions.data;
                    vm.data = data;
                    for(var i = 0; i < data.length; i++){
                        data[i].uploadDateTime = $filter('date')(data[i].uploadDateTime, "short");
                    }

                    vm.tableParams = new NgTableParams({
                        page: 1,
                        count: 10
                    }, {
                        filterDelay: 0,
                        counts: [],
                        dataset: data
                    });
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