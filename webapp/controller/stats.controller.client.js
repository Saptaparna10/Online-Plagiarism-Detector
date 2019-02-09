(function () {
    angular
        .module("PlagiarismDetector")
        .controller("StatsController", statsController);
    function statsController($routeParams, StatsService, currentUser, $location, UserService, $filter, NgTableParams) {
        var vm = this;
        if(currentUser){
            vm.userId = currentUser.id;
            vm.user = currentUser;
        }

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
            StatsService.findStatsForProf(vm.userId)
                .then(function (value) {
                    var data = value.data;
                    if(data.length == 0) {
                        vm.message = "No plagiarism check performed";
                    }
                    for(var i = 0; i < data.length; i++){
                        data[i].runDateTime = $filter('date')(data[i].runDateTime, "short");
                    }

                    vm.tableParams = new NgTableParams({
                        page: 1,
                        count: 20
                    }, {
                        filterDelay: 0,
                        counts: [],
                        dataset: data
                    });
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