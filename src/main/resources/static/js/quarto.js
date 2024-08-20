$(document).ready(function() {
	loadGuests();

	$('#roomForm').submit(function(event) {
		event.preventDefault();
		const id = $('#roomid').val();
		if (id) {
			updateGuest(id);
		} else {
			addGuest();
		}
	});
});

function loadGuests() {
	$.getJSON('api/quarto/', function(data) {
		$('#roomTableBody').empty();
		data.forEach(room => {
			$('#roomTableBody').append(
				`<tr>
<td>${room.id}</td>
                   <td>${room.capacidade}</td>
                   <td>${room.preco}</td>
                   <td>${room.andar}</td>
                   <td>${room.numero}</td>
                   <td>
                    <button class="btn btn-sm btn-warning" onclick="showEditGuestForm(${room.id},'${room.capacidade}','${room.preco}','${room.andar}','${room.numero}')">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteGuest(${room.id})">Deletar</button>
                   </td>
                </tr>`
			);
		});
	});
}

function showAddGuestForm() {
	$('#formTitle').text('Adicionar Quarto');
	$('#roomid').val('');
	$('#roomcapacidade').val('');
	$('#roompreco').val('');
	$('#roomandar').val('');
	$('#roomnumero').val('');
	$('#roomFormModal').show();
}

function showEditGuestForm(id, capacidade, preco, andar, numero) {
	$('#formTitle').text('Editar Quarto');
	$('#roomid').val(id);
	$('#roomcapacidade').val(capacidade);
	$('#roompreco').val(preco);
	$('#roomandar').val(andar);
	$('#roomnumero').val(numero);
	$('#roomFormModal').show();
}

function closeGuestForm() {
	$('#roomFormModal').hide();
}

function addGuest() {
	const room = {
		capacidade: $('#roomcapacidade').val(),
		preco: $('#roompreco').val(),
		andar: $('#roomandar').val(),
		numero: $('#roomnumero').val()
	};
	$.ajax({
		url: 'api/quarto/',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(room),
		success: function() {
			closeGuestForm();
			loadGuests();
		},
		error: function() {
			alert('Erro ao adicionar o quarto.');
		}
	});
}

function updateGuest(id) {
	const room = {
		capacidade: $('#roomcapacidade').val(),
		preco: $('#roompreco').val(),
		andar: $('#roomandar').val(),
		numero: $('#roomnumero').val()
	};
	$.ajax({
		url: `api/quarto/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(room),
		success: function() {
			closeGuestForm();
			loadGuests();
		},
		error: function() {
			alert('Erro ao editar o quarto.');
		}
	});
}

function deleteGuest(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `api/quarto/${id}`,
			type: 'DELETE',
			success: function() {
				loadGuests();
			},
			error: function() {
				alert('Erro ao deletar o quarto.');
			}
		});
	}
}