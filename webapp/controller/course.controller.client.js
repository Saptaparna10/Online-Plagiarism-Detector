(function () {
    angular
        .module("PlagiarismDetector")
        .controller("CourseController", courseController);
    function courseController($routeParams, CourseService, StudentCourseService, currentUser, $location, UserService) {
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
            if(vm.user.role == "Student"){
                StudentCourseService.findCourseForStudent(vm.userId)
                    .then(function (courses) {
                        if(courses.data.length == 0) {
                            vm.message = "No Course";
                        }
                        vm.courses = courses.data;
                    }, function (err) {
                        vm.error = err.data.message;
                    });
            }else{
                CourseService.findCourseByProfessorId(vm.userId)
                    .then(function (courses) {
                        if(courses.data.length == 0) {
                            vm.message = "No Courses";
                        }
                        vm.courses = courses.data;
                    }, function (err) {
                        vm.error = err.data.message;
                    });
            }
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