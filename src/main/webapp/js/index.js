var i=0;

function openUrl(url,name,child){
	$("#center").attr("src",url);
	$('#daohang').accordion('select',name);
	$('#content').panel("setTitle",name+" >> "+child);
}

$(document).ready(function(){
    $.ajax({
        type: "get", 
        url: "queryMenuByUser.do", 
        dataType: 'json',
        success: function (result) {
        	$.each(result, function(index, content){
        		createMainMenu(content);
        		createAccordion(content);
        	});
        	$.parser.parse($('#daohang'));
        }
    });
});


function createAccordion(data){
	$('#daohang').accordion('add', {
		title: data.text,
		id : "acc"+data.id,
		selected: false
	});
	if(data.children!=null&&data.children.length>0){
		$.each(data.children, function(index, content){
			$('#acc'+data.id).append("<a href=\"javascript:openUrl('"+content.url+"','"+data.text+"','"+content.text+"')\" class='easyui-linkbutton' data-options=\"plain:true,iconCls:'"+content.iconCls+"'\">"+content.text+"</a><br />");
		});
	}
}


function findChild(data){
	createChildMenu(data);
	if(data.children==null||data.children.length==0){
		return;
	}
	else{
		$.each(data.children, function(index, content){
			findChild(content);
		});
	}
}

function createMainMenu(data){
	$("#menu").append("<a href=\"#\" id=\""+data.id+"\">"+data.text+"</a>");
	if(data.children!=null&&data.children.length>=0){
		var index = ++i;
		$("#menuCtx").append(createButton(index,data));
		$("#"+data.id+"").menubutton({
			iconCls: data.iconCls,
			menu: "#"+index+"_"+data.id+""
		});
	}
}

//function createChildMenu(parentIndex,data){
//	var index = ++i;
//	$("#menuCtx").append(createButton(index,data));
//	$("#"+parentIndex+"_"+data.id+"").menubutton({
//		iconCls: 'icon-edit',
//		menu: "#"+index+"_"+data.id+""
//	});
//}

function createButton(idx,data){
	var div =  "<div id=\""+idx+"_"+data.id+"\" style=\"width:150px;\">";
	$.each(data.children, function(index, content){
		div+="<div data-options=\"iconCls:'"+content.iconCls+"'\" onclick=\"javascript:openUrl('"+content.url+"','"+data.text+"','"+content.text+"')\">"+content.text+"</div>";
	});
	div+="</div>";
	return div;
			
}

