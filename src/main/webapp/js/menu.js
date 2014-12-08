$(document).ready(function(){
	$('#tt').tree({
	    url: 'getAsyncMenuTree.do',
	    loadFilter: function(data){
	        if (data.d){
	            return data.d;
	        } else {
	            return data;
	        }
	    }
	});
	
	$('#tt').tree({
		onClick: function(node){
			getMenu(node.id);
			init(node);  // alert node text property when clicked
		}
	});
	
	getMenu("0");
});

function init(data){
	$.ajax({
		type : "GET", 
		url : "getMenuById.do?id="+data.id, 
		dataType : 'json',
		success : function(res) { 
			$('#frmDetail').form('load', {
				id : res.id,
				parentId : res.parentId,
				text : res.text,
				url : res.url,
				iconCls : res.iconCls,
				status : res.status
			});
		}
	});
	
	$('#frmMenu').form('load', {
		parentId : data.id,
		status : 1
	});
}

function getMenu(parentId) {
	$.ajaxSetup({
		cache : false
	});
	$('#dg')
			.datagrid(
					{
						url : "getAsyncMenuTree.do?id="+parentId,
						pagination : true,
						height : 330,
						pageSize : 10,
						pageList : [ 10 ],
						loadMsg : '数据装载中......',
						columns : [ [
								{
									field : 'id',
									title : '编号',
									width : 150,
									hidden:true
								},{
									field : 'parentId',
									title : '父编号',
									width : 150,
									hidden:true
								},{
									field : 'text',
									title : '名称',
									width : 100
								},
								{
									field : 'url',
									title : 'URL',
									width : 150
								},
								{
									field : 'iconCls',
									title : '图标',
									width : 100
								},{
									field : 'leaf',
									title : '是否叶子',
									width : 100
								},{
									field : 'sort',
									title : '顺序',
									width : 100
								},
								{
									field : 'status',
									title : '状态',
									width : 50,
									formatter : function(value, row) {
										if (value == 0)
											return "停用";
										else
											return "启用";
									}
								},
								{
									field : 'opter',
									title : '操作',
									width : 100,
									align:'center',
									formatter : function(value, row, index) {
										return "<button class='positive' onclick='deleteMenu(\""
												+ row.id
												+ "\",\""
												+ row.name
												+ "\");'>删除</button>";
									}
								} ] ]
					});
}

function addMenu() {
	var o = {};
	var a = $("#frmMenu").serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});

	var jsonuserinfo = JSON.stringify(o);
	$.ajax({
		type : "POST", 
		contentType : "application/json",
		url : "addMenu.do", 
		data : jsonuserinfo, 
		dataType : 'json',
		success : function(result) { //
			closeW();
			$('#tt').tree('reload');
			getMenu(o.parentId);
		}
	});
}


function deleteMenu(id, name) {
	if (confirm("确认要删除？")) {
		$.post("deleteMenu.do", {
			menuId : id
		}, function(data, textStatus) {
			if (data == 1) {
				var row = $('#dg').datagrid("getSelected");
				var index = $('#dg').datagrid('getRowIndex', row);
				$('#dg').datagrid("deleteRow", index);
				var node = $('#tt').tree('find', id);
				$('#tt').tree('remove',node.target);
			}else if(data == 0){
				alert("请先删除子项！")
			}
		}, "json");
	}

}



function updateMenu() {
	var o = {};
	var a = $("#frmDetail").serializeArray();
	$.each(a, function() {
		if (o[this.name]) {
			if (!o[this.name].push) {
				o[this.name] = [ o[this.name] ];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});

	var jsonuserinfo = JSON.stringify(o);
	$.ajax({
		type : "POST", // 访问WebService使用Post方式请求
		contentType : "application/json", // WebService 会返回Json类型
		url : "addMenu.do", // 调用WebService的地址和方法名称组合 ---- WsURL/方法名
		data : jsonuserinfo, // 这里是要传递的参数，格式为 data:
								// "{paraName:paraValue}",下面将会看到
		dataType : 'json',
		success : function(result) { // 回调函数，result，返回值
			closeW();
			$('#tt').tree('reload');
			getMenu(o.parentId);
		}
	});
}


function closeW() {
	$('#w').window('close');
}


var toolbar = [{
    text:'添加',
    iconCls:'icon-add',
    handler:function(){
    	$('#w').window({title:"新增菜单功能"});
        $('#w').window('open');
        $('#frmMenu').form('load',{
            id:"",
            text:"",
            iconCls:"",
            url:"",
            status:1
        });
        
    }
}];