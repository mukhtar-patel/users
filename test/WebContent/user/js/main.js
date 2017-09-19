// The root URL for the RESTful services
var rootURL = "http://localhost:85/test/rest/users";

var currentUser;

// Retrieve user list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();


$('#btnAdd').click(function() {
	newUser();
	return false;
});

$('#btnSave').click(function() {
	if ($('#userId').val() == '')
		addUser();
	else
		updateUser();
	return false;
});

$('#btnDelete').click(function() {
	deleteUser();
	return false;
});

$('#userList a').live('click', function() {
	findById($(this).data('identity'));
});


function newUser() {
	$('#btnDelete').hide();
	currentUser = {};
	renderDetails(currentUser); // Display empty form
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL + '/all',
		dataType: "json", // data type of response
		success: renderList
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.foreName);
			currentUser = data;
			renderDetails(currentUser);
		}
	});
}

function addUser() {
	console.log('addUser');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/add',
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('User created successfully');
			$('#btnDelete').show();
			$('#userId').val(data.userId);
			findAll();
			newUser();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addUser error: ' + textStatus);
			console.log(jqXHR);
			console.log(errorThrown);
		}
	});
}

function updateUser() {
	console.log('updateUser');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL + '/update',
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('User updated successfully');
			findAll();
			newUser();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateUser error: ' + textStatus);
		}
	});
}

function deleteUser() {
	console.log('deleteUser');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#userId').val(),
		success: function(data, textStatus, jqXHR){
			alert('User deleted successfully');
			findAll();
			newUser();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteUser error');
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [data]);

	$('#userList li').remove();
	$.each(list, function(index, user) {
		$('#userList').append('<li><a href="#" data-identity="' + user.userId + '">'+user.foreName+' ' +user.lastName+'</a></li>');
	});
}

function renderDetails(user) {
	$('#userId').val(user.userId);
	$('#foreName').val(user.foreName);
	$('#lastName').val(user.lastName);
	$('#email').val(user.email);
	$('#created').val(formatDate(user.created));
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var userId = $('#userId').val();
	
	var created = $('#created').val();
	var milliseconds = 0;
	if (created != "")
	{
		var date = new Date(created);
		milliseconds = date.getTime(); 
	}
	
	return JSON.stringify({
		"userId": userId == "" ? null : userId, 
		"foreName": $('#foreName').val(), 
		"lastName": $('#lastName').val(),
		"email": $('#email').val(),
		"created": milliseconds,
		});
}

function formatDate(num) {
	
	  if (num > 0) {
		  var date = new Date(num);
		  var monthNames = [
		    "January", "February", "March",
		    "April", "May", "June", "July",
		    "August", "September", "October",
		    "November", "December"
		  ];
	
		  var day = date.getDate();
		  var monthIndex = date.getMonth();
		  var year = date.getFullYear();
	
		  return day + ' ' + monthNames[monthIndex] + ' ' + year;
	  }
	  else 
		  return '';
	}

