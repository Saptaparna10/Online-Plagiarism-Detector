(function () {
    angular
        .module("PlagiarismDetector")
        .factory("AdminService", adminService);
    function adminService($http) {
        var api = {
            "contactAdmin": contactAdmin,
            "getAllNote": getAllNote,
            "deleteNote": deleteNote
        };
        return api;

        function contactAdmin(userId, note) {
            return $http.post("/api/contactAdmin/"+userId, note);
        }

        function deleteNote(noteId) {
            return $http.delete("/api/admin/note/" + noteId);
        }

        function getAllNote() {
            return $http.get("/api/admin/note");
        }
    }
})();