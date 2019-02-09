(function () {
    angular
        .module("PlagiarismDetector")
        .factory("StudentCourseService", studentCourseService);
    function studentCourseService($http) {
        var api = {
            "addStudentToCourse": addStudentToCourse,
            "removeStudentFromCourse": removeStudentFromCourse,
            "findCourseForStudent": findCourseForStudent,
            "findStudentOfCourse": findStudentOfCourse
        };
        return api;

        function findStudentOfCourse(courseId) {
            return $http.get("/api/course/"+courseId+"/enrolled");
        }
        function addStudentToCourse(courseId, studentId) {
            return $http.post("/api/course/"+courseId+"/student/"+studentId+"/add");
        }
        function removeStudentFromCourse(studentCourseId, courseId) {
            return $http.post("/api/course/" + courseId + "/studentCourse/" + studentCourseId);
        }
        function findCourseForStudent(studentId) {
            return $http.get("/api/student/"+studentId+"/course");
        }
    }
})();