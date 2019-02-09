(function () {
    angular
        .module("PlagiarismDetector")
        .config(configuration);
    function configuration($routeProvider) {
        $routeProvider
            .when("/login", {
                templateUrl: "view/login.view.client.html",
                controller: "LoginController",
                controllerAs: "model",
                resolve: { currentUser: checkLoggedIn}
            })
            .when("/contactAdmin", {
                templateUrl: "view/contactAdmin.view.client.html",
                controller: "ContactAdminController",
                controllerAs: "model",
                resolve: { currentUser: loggedIn}
            })
            .when("/userApprove", {
                templateUrl: "view/userApprove.view.client.html",
                controller: "UserApproveController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/course/:cid/hw", {
                templateUrl: "view/hw.view.client.html",
                controller: "HwController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/course/:cid/student/:arid", {
                templateUrl: "view/studentCourse.view.client.html",
                controller: "StudentCourseController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/course", {
                templateUrl: "view/course.view.client.html",
                controller: "CourseController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/stats", {
                templateUrl: "view/stats.view.client.html",
                controller: "StatsController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/course/:cid/hw/:hwid/submissions", {
                templateUrl: "view/submissions.view.client.html",
                controller: "SubmissionsController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/course/:cid/hw/:hwid/student/:sid/percent/:aid", {
                templateUrl: "view/percent.view.client.html",
                controller: "PercentController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/course/:cid/hw/:hwid/student/:sid/algo/:aid/file1/:f1id/file2/:f2id/report", {
                templateUrl: "view/report.view.client.html",
                controller: "ReportController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/course/:cid/hw/:hwid", {
                templateUrl: "view/hwEdit.view.client.html",
                controller: "HwEditController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/course/:cid", {
                templateUrl: "view/courseEdit.view.client.html",
                controller: "CourseEditController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/course/:cid/hw/:hwid/submission", {
                templateUrl: "view/upload.view.client.html",
                controller: "UploadController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/register", {
                templateUrl: "view/register.view.client.html",
                controller: "RegisterController",
                controllerAs: "model",
                resolve: { currentUser: checkLoggedIn}
            })
            .when("/profile", {
                templateUrl: "view/profile.view.client.html",
                controller: "ProfileController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/home", {
                templateUrl: "view/home.view.client.html",
                controller: "HomeController",
                controllerAs: "model"
            })
            .when("/analysis", {
                templateUrl: "view/analysis.view.client.html",
                controller: "AnalysisController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .when("/userContact", {
                templateUrl: "view/userContact.view.client.html",
                controller: "UserContactController",
                controllerAs: "model",
                resolve: { currentUser: checkCurrentUser}
            })
            .otherwise({redirectTo: "/home"});

        function checkCurrentUser($q, UserService, $location) {
            var deferred = $q.defer();
            UserService
                .loggedIn()
                .then(function (response) {
                    var user = response.data;
                    if (user.id != 0) {
                        deferred.resolve(user);
                    } else {
                        deferred.resolve(null);
                        $location.url('/login');
                    }
                }, function (err) {
                    deferred.reject();
                    $location.url('/home');
                });
            return deferred.promise;
        }

        function loggedIn($q, UserService, $location) {
            var deferred = $q.defer();
            UserService
                .loggedIn()
                .then(function (response) {
                    var user = response.data;
                    if (user.id != 0) {
                        deferred.resolve(user);
                    } else {
                        deferred.resolve(null);
                    }
                }, function (err) {
                    deferred.reject();
                    $location.url('/home');
                });
            return deferred.promise;
        }

        function checkLoggedIn($q, UserService, $location) {
            var deferred = $q.defer();
            UserService
                .loggedIn()
                .then(function (response) {
                    var user = response.data;
                    if (user.id != 0) {
                        deferred.resolve(user);
                        $location.url('/profile');
                    } else {
                        deferred.resolve(null);
                    }
                }, function (err) {
                    deferred.reject();
                    $location.url('/home');
                });
            return deferred.promise;
        }
    }
})();