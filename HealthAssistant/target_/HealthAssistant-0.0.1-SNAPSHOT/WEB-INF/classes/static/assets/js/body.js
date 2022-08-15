
window.onload = function () {
    const pieces = document.getElementsByTagName('svg');
    for (var i = 0; i < pieces.length; i++) {
        let _piece = pieces[i];
        if(typeof _piece === "undefined") {
            //nothing
        }
        else {
            _piece.onclick = function(t) {
                var id = 0;
                if (t.target.getAttribute('data-position') != null) {
                    document.getElementById('data').innerHTML = t.target.getAttribute('data-position');
                    id = t.target.getAttribute('data-id');;
                }
                if (t.target.parentElement.getAttribute('data-position') != null) {
                    document.getElementById('data').innerHTML =
                        t.target.parentElement.getAttribute('data-position');
                    id = t.target.parentElement.getAttribute('data-id');
                }

                $.ajax({
                    url: "/api/category/" + id,
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json",
                    success: function (response) {
                        var lists = response.lists;

                        var tabltdata = "<table class='table'>";
                        tabltdata += "<tr><th>Description</th><th>Name</th><th>Prevention</th><th>Symptom</th></tr>";
                        lists.forEach(async (a) => {
                            var description = a.description;
                            var name = a.name;
                            var prevention = a.prevention;
                            var symptom = a.symptom;

                            tabltdata += "<tr style='width: 300px; height: 100px;'>" +
                                "<td >"+ description + "</td>" +
                                "<td >" + name
                                +"</td>" +
                                "<td >" + prevention + "</td>" +
                                "<td >" + symptom + "</td>" +
                                "</tr>";
                        });
                        tabltdata += "</table>";

                        document.getElementById('data').innerHTML =
                            document.getElementById('data').innerHTML +
                            "<div>" + tabltdata + "</div>";
                    },
                    error: function (err) {
                        alert(err);
                    }
                });
            }
            //end
        }

    }
}

