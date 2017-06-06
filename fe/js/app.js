$.getJSON("api/todos", function(data){	
	var html ='';
	var cnt=0;
	$.each(data, function(index,todo){
		var complete = ' class=""';
		var check ='';
		if(todo.completed==1){
			complete =' class="completed"';
			check =' checked ';
		}else
			cnt +=1;
		html +='<li'+complete+'><div class="view"><input class="toggle" type="checkbox" '+check+'value="'+todo.id+'" onclick="updateComplete(this)"><label>'
				+todo.todo+'</label><button class="destroy" value="'+todo.id+'" onclick="deleteTodo(this)"></button></div><input class="edit" value="Rule the web"></li>'
	});
	$(html).appendTo('ul.todo-list');
	$('span strong').text(cnt);
});

function insertTodo() {
	if($(".new-todo").val().trim()){
		var completed = 0;
		var date = '2017-06-06';
		var todo = {"todo":$(".new-todo").val(),"completed":completed,"date":date};
		$.ajax({
			url: "../api/todos",
		    type: "POST", 
		    contentType:"application/json; charset=utf-8",
		    data: JSON.stringify(todo),
		    success: function(data) {
		    	var html ='<li class=""><div class="view"><input class="toggle" type="checkbox" value="'+data.id+'" onclick="updateComplete(this)"><label>'
					+data.todo+'</label><button class="destroy"></button></div><input class="edit" value="Rule the web"></li>'
		    	$(html).prependTo('ul.todo-list');
		    	var cnt = Number($('span strong').text()) +1;
		    	$('span strong').text(cnt);
		    	$('.new-todo').val('');
		    },
		    error: function(error){
		    	alert("error");
		    }
		});
	}
};
function updateComplete(e){
	$.ajax({
		url: "../api/todos/"+e.value,
	    type: "PUT", 
	    contentType:"application/json; charset=utf-8",
	    data: e.value,
	    success: function(data) {
	    	var cnt = Number($('span strong').text()) -1;
	    	$('span strong').text(cnt);
	    	$(e).parents('li').addClass('completed');
	    },
	    error: function(error){
	    	alert("error");
	    }
	});
}

function deleteTodo(e){
	var deleteData=$(e);
	$.ajax({
		url: "../api/todos/"+e.value,
	    type: "DELETE", 
	    contentType:"application/json; charset=utf-8",
	    data: e.value,
	    success: function(data) {
	    	$(deleteData).parents('li').remove();
	    	var cnt = Number($('span strong').text()) -1;
	    	$('span strong').text(cnt);
	    },
	    error: function(error){
	    	alert("error");
	    }
	});
}

function deleteCompleted(e){
	$.ajax({
		url: "../api/todos",
	    type: "DELETE",
	    success: function(data) {
	    	$("ul.todo-list li.completed").remove();
	    },
	    error: function(error){
	    	alert("error "+e.value);
	    }
	});
}

function filterTodo(filter) {
	var list = $("ul.todo-list li");
	list.show();
	if (filter=="completed") {
		$('ul.todo-list li:not(.completed) ').hide();
	}else if(filter=="active") {
		$('li.completed').hide();
	}
}
