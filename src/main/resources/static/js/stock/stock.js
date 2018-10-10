$.fn.dataTable.ext.errMode = 'none'; //不显示任何错误信息
var stockTable ;
$(document).ready(function () {
	$('a[data-toggle="tab"]').on( 'shown.bs.tab', function (e) {
        $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
    } );
	
	stockid = $.cookie('stockid');
	stockTable = $('#stock-pegs-table').DataTable({
		"language": {
            url: "/zh_CN.json"
        },
        "fnInitComplete": function() {
            this.fnAdjustColumnSizing(true);
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
	/*$(window).bind('resize', function () {
		stockTable.fnAdjustColumnSizing(true);
	} );*/
	stockTable.order( [ 0, 'desc' ] ).draw();
});

