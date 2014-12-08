var statusMap = {};  
$(document).ready(function(){
    $.ajax({
        type: "get", 
        url: "getDictionary.do?code=SERVER_STATUS&isFull=true", 
        dataType: 'json',
        success: function (result) {
        	$.each(result, function(index, content){
        		statusMap[content.key]=content.value;
        	});
        }
    }); 
});

function query(){
	var queryParams = {};
	queryParams.serverName=$("#tabQuery").find("input[name='serverName']").val();
	queryParams.status=$("#tabQuery").find("input[name='status']").val();
	queryParams.gameName=$("#tabQuery").find("input[name='gameName']").val();
	$.ajaxSetup({cache:false});
    $('#dg').datagrid({
	    url:"queryServer.do",
	    pagination:true,
	    queryParams:queryParams,
	    method:'POST',
	    height:340,
	    pageSize:10,
	    pageList:[10],
	    loadMsg:'数据装载中......',
	    columns:[[ 
	        {field:'id',title:'编号',width:100,hidden:true},
	        {field:'gameName',title:'游戏名称',width:100},
	        {field:'serverName',title:'服务器名称',width:100},
	        {field:'ip',title:'IP',width:70},
	        {field:'port',title:'端口',width:50},
	        {field:'registerCount',title:'注册用户数',width:70,align:'center'},
	        {field:'onlineCount',title:'在线用户数',width:70,align:'center'},
	        {field:'maxCount',title:'最大用户数',width:70,align:'right',titleAlign: 'center'},
	        {field:'createTime',title:'创建时间',width:100,align:'center',formatter:function(value,row){
				if(value>0)
					return getFormatDateByLong(value,true);
				else
					return "";
			}},
	        {field:'lastUpdateTime',title:'最后更新时间',width:100,align:'center',formatter:function(value,row){
				if(value>0)
					return getFormatDateByLong(value,true);
				else
					return "";
			}},
	        {field:'status',title:'状态',width:40,align:'center',formatter:function(value,row){
	        	return statusMap[value];
			}},
			{field:'opter',title:'操作',width:100,align:'center',formatter:function(value,row,index){
				return "<button class='positive' onclick='modifyStatus("+index+")'>编辑状态</button>";
			}}
	    ]]
	});	
}






function modifyStatus(index){
	$('#dg').datagrid("selectRow",index);
	var row = $('#dg').datagrid("getSelected");
	$('#w').window('open');
    $('#frmServer').form('load',{
        id:row['id'],
        serverName:row['serverName'],
        status:row['status']
    });
}

function closeW(){
	$('#w').window('close');
}

function saveStatus(){
	var status= $("#frmServer").find("input[name='status']").val();
	if(status=='-1'){
		alert("请选择状态！");
		return;
	}
	$.ajax({
        type: "POST", 
        url: "modifyStatus.do", 
        data: $("#frmServer").serialize(),         
        dataType: 'json',
        success: function (result) {     
        	closeW();
        	if(result==1){
            	query();
        	}else{
        		alert("保存失败！");
        	}
        }
    });
}