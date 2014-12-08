function query(){
	var queryParams = {};
	queryParams.menuName=$("#tabQuery").find("input[name='menuName']").val();
	queryParams.userName=$("#tabQuery").find("input[name='userName']").val();
	$.ajaxSetup({cache:false});
    $('#dg').datagrid({
	    url:"queryOptLog.do",
	    pagination:true,
	    queryParams:queryParams,
	    method:'POST',
	    height:340,
	    pageSize:10,
	    pageList:[10],
	    loadMsg:'数据装载中......',
	    columns:[[ 
	        {field:'optTime',title:'操作时间',width:100,formatter:function(value,row){
				if(value>0)
					return getFormatDateByLong(value,true);
				else
					return "";
			}},
	        {field:'userName',title:'用户名称',width:70},
	        {field:'funName',title:'操作类型',width:70},
	        {field:'menuName',title:'操作对象',width:70},
	        {field:'args',title:'参数',width:70},
	        {field:'targetName',title:'目标名称',width:80,align:'center'},
	        {field:'success',title:'是否成功',width:80,align:'center'},
	        {field:'resultMsg',title:'结果信息',width:90,align:'right',titleAlign: 'center'}
	    ]]
	});	
}