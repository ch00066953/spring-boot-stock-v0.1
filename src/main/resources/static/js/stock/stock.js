$.fn.dataTable.ext.errMode = 'none'; //不显示任何错误信息

$(document).ready(function () {
	stockid = $.cookie('stockid');
	var table = $('#stock-pegs-table').DataTable({
		"language": {
            url: "/zh_CN.json"
        },
		/* "processing": true,
        "serverSide": true, */
		"ajax" : {
			dataType: 'json',
            url: "/rest/"+stockid
        },
		"columns" : [ {
			"data" : "日期-前"
		}, {
			"data" : "最高价格"
		}, {
			"data" : "净利润"
		}, {
			"data" : "增长率"
		}, {
			"data" : "最高PE"
		}, {
			"data" : "最高PEG"
		} ]
	});
	table.order( [ 0, 'desc' ] ).draw();
});

