(function () {
    angular
        .module("PlagiarismDetector")
        .factory("CourseService", courseService);
    function courseService($http) {
        var api = {
            "findCourseByProfessorId": findCourseByProfessorId,
            "createCourse": createCourse,
            "findCourseById": findCourseById,
            "updateCourse": updateCourse
        };
        return api;
        
        function findCourseByProfessorId(profId) {
            return $http.get("/api/prof/"+profId+"/course");
        }

        function updateCourse(course) {
            return $http.put("/api/course", course);
        }

        function findCourseById(courseId) {
            return $http.get("/api/course/"+courseId);
        }

        function createCourse(profId, course) {
            return $http.post("/api/prof/"+profId+"/course", course);
        }
    }
})();