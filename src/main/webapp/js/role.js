function getRole() {
	$.ajaxSetup({
		cache : false
	});
	$('#dg')
			.datagrid(
					{
						url : "queryRole.do",
						pagination : true,
						height : 320,
					    pageSize:10,
					    pageList:[10],
						loadMsg : '数据装载中......',
						sortName : 'name',
						sortOrder : 'desc',
						frozenColumns : [ [ {
							field : 'ck',
							checkbox : true
						} ] ],
						columns : [ [
								{
									field : 'id',
									title : '编号',
									width : 150
								},{
									field : 'name',
									title : '名称',
									width : 150
								},
								{
									field : 'status',
									title : '状态',
									width : 100,
									formatter : function(value, row) {
										if (value == 0)
											return "停用";
										else
											return "启用";
									}
								},
								{
									field : 'remark',
									title : '备注',
									width : 100
								},
								{
									field : 'opter',
									title : '操作',
									width : 200,
									align:'center',
									formatter : function(value, row, index) {
										return "<button class='positive' onclick='modifyRole("
												+ index
												+ ");'>修改</button>&nbsp;&nbsp;"
												+ "<button class='positive' onclick='deleteRole(\""
												+ row.id
												+ "\",\""
												+ row.name
												+ "\");'>删除</button>&nbsp;&nbsp;"
												+ "<button class='positive' onclick='anth(\""
												+ row.id
												+ "\",\""
												+ row.name
												+ "\");'>授权</button>";
									}
								} ] ]
					});
}

function addRole() {
	var o = {};
	var a = $("#frmRole").serializeArray();
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
		url : "addRole.do", // 调用WebService的地址和方法名称组合 ---- WsURL/方法名
		data : jsonuserinfo, // 这里是要传递的参数，格式为 data:
								// "{paraName:paraValue}",下面将会看到
		dataType : 'json',
		success : function(result) { // 回调函数，result，返回值
			closeW();
			getRole();
		}
	});

}

function closeW() {
	$('#w').window('close');
}

function anth(id, name) {
	$('#w3').window('open');
	$('#tt').tree({
	    url: 'getAuthMenuTree.do?roleId='+id,
	    checkbox : true,
	    loadFilter: function(data){
	        if (data.d){
	            return data.d;
	        } else {
	            return data;
	        }
	    }
	});
}

function saveAnth() {
	
	var row = $('#dg').datagrid("getSelected");
	var roleId = row['id'];
	var checked = "";
	var indeterminate ="";
	
	var nodes1 = $('#tt').tree('getChecked', 'indeterminate');
	var nodes2 = $('#tt').tree('getChecked');
	$.each(nodes1, function(index, node){
		indeterminate = indeterminate + node.id + ":";
	});
	$.each(nodes2, function(index, node){
		checked = checked + node.id + ":";
	});
	
	$.post("roleAuthMenu.do", {
		roleId : roleId,
		checked : checked,
		indeterminate : indeterminate
	}, function(data, textStatus) {
		$('#w3').window('close');

	});

}

function deleteRole(id, name) {
	if (confirm("确认要删除？")) {
		$.post("deleteRole.do", {
			roleId : id
		}, function(data, textStatus) {
			if (data == 1) {
				var row = $('#dg').datagrid("getSelected");
				var index = $('#dg').datagrid('getRowIndex', row);
				$('#dg').datagrid("deleteRow", index);
			}
		}, "json");
	}

}

function modifyRole(index) {
	$('#dg').datagrid("selectRow", index);
	var row = $('#dg').datagrid("getSelected");
	var id = row['id'];
	$('#w').window('open');
	$('#frmRole').form('load', {
		id : row['id'],
		name : row['name'],
		status : row['status'],
		remark : row['remark']
	});
}

var toolbar = [{
    text:'添加',
    iconCls:'icon-add',
    handler:function(){
    	$('#w').window({title:"新增角色"});
        $('#w').window('open');
        $('#frmRole').form('load',{
            id:null,
            name:"",
            status:1,
            remark:""
        });
    }
}];
