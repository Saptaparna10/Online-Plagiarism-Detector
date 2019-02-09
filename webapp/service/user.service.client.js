(function () {
    angular
        .module("PlagiarismDetector")
        .factory("UserService", userService);
    function userService($http) {
        var api = {
            "login": login,
            "register": register,
            "findUserById": findUserById,
            "updateUser": updateUser,
            "findAllStudentForCourse": findAllStudentForCourse,
            "loggedIn": loggedIn,
            "logout": logout,
            "findAllProf": findAllProf,
            "approveOrReject": approveOrReject
        };
        return api;

        function findAllProf() {
            return $http.get("/api/prof");
        }
        function approveOrReject(prof) {
            return $http.put("/api/prof/approve", prof);
        }
        function loggedIn() {
            return $http.get("/api/loggedIn");
        }
        function logout() {
            return $http.post("/api/logout");
        }
        function findAllStudentForCourse(courseId) {
            return $http.get("/api/course/"+courseId+"/student");
        }
        function login(user) {
            return $http.post("/api/login", user);
        }
        function register(user) {
            return $http.post("/api/register", user);
        }
        function findUserById(id) {
            return $http.get("/api/user/" + id);
        }

        function updateUser(user) {
            return $http.put("/api/user/update", user);
        }
    }
})();