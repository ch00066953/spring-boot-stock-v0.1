$.fn.dataTable.ext.errMode = 'none'; //不显示任何错误信息

$(document).ready(function () {
	$('#pegs-table').DataTable({
		/* "processing": true,
        "serverSide": true, */
		"deferRender": true,
		"stateSave": true,
		"stateSaveParams": function (settings, data) {
	        console.log("stateSaveParams");
	        //这里可以操作保存的数据，写上自己特定的逻辑
	    },
	    "stateSaveCallback": function (settings, data) {
	        console.log("stateSaveCallback");
	        //DT默认保存的key值为DataTables_+表格id+页面名称
	        localStorage.setItem('DataTables_' + settings.sInstance, JSON.stringify(data));
	        // 你可以把这些数据保存在服务器上，上面的代码标识使用本地储存来存储这些数据
	        /**$.ajax( {
	                "url": "/state_save",
	                "data": data,
	                "dataType": "json",
	                "type": "POST",
	                "success": function () {}
	            } );
	         **/
	    },
	    //读取状态操作
	    "stateLoadParams": function (settings, data) {
	        console.log("stateSaveParams");

	        //在读取数据的时候可以改变数据，根据自己逻辑来处理

	        //data.search.search = "";


	        //或者你可以直接禁用从缓存里读取数据，只要直接返回false即可

	        //return false;
	    },
	    "stateLoadCallback": function (settings) {
	        console.log("stateLoadCallback");
	        return JSON.parse(localStorage.getItem('DataTables_' + settings.sInstance));
	        //同样你还可以从服务器取数，采用同步的方式获取到保存在服务器里的数据
	        /**var o;
	         $.ajax( {
	            "url": "/state_load",
	            "async": false,
	            "dataType": "json",
	            "success": function (json) {
	                o = json;
	            }
	        } );
	         return o;**/
	    },
	    //状态加载完后执行的回调函数
	    "stateLoaded": function (settings, data) {
	        console.log("stateLoaded");
	        //在这里你可以打印出保存的缓存数据
	        //alert( 'Saved filter was: '+data.search.search );
	    },
		"language": {
            url: "/zh_CN.json"
        },
		"ajax" : {
			dataType: 'json',
            url: "/rest/totalstockpegs"
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
		} ],
		"aoColumnDefs":[{"aTargets":[0],"mRender":function(data,type,full){
			return "<a href=\"/stock/"+data+ "\">"+data+"</a>"
		 }
		}]
	});
});

