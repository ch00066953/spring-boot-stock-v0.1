$(function () {
    $('.js-basic-example').DataTable({
        responsive: true,
        "ajax" : {
			dataType: 'json',
            url: "/datastock"
        },
		"columns" : [ {
			"data" : "ID"
		}, {
			"data" : "name"
		}, {
			"data" : "industry2"
		}, {
			"data" : "netrate"
		}, {
			"data" : "peg"
		}, {
			"data" : "score"
		} ]
    });

    //Exportable table
    $('.js-exportable').DataTable({
        dom: 'Bfrtip',
        responsive: true,
        buttons: [
            'copy', 'csv', 'excel', 'pdf', 'print'
        ]
    });
});