$(document).ready(function() {
	loadGuests();

	$('#guestForm').submit(function(event) {
		event.preventDefault();
		const id = $('#guestid').val();
		if (id) {
			updateGuest(id);
		} else {
			addGuest();
		}
	});
});

function loadGuests() {
	$.getJSON('api/hospede/', function(data) {
		$('#guestTableBody').empty();
		data.forEach(guest => {
			$('#guestTableBody').append(
				`<tr>
					<td>${guest.id}</td>
                    <td>${guest.nome}</td>
                    <td>${guest.telefone}</td>
                    <td>${guest.email}</td>
                    <td>
                    	<button class="btn btn-sm btn-warning" onclick="showEditGuestForm(${guest.id}, '${guest.nome}', '${guest.telefone}', '${guest.email}')">Editar</button>
                    	<button class="btn btn-sm btn-danger" onclick="deleteGuest(${guest.id})">Deletar</button>
                    </td>
                 </tr>`
			);
		});
	});
}

function showAddGuestForm() {
	$('#formTitle').text('Adicionar Hóspede');
	$('#guestid').val('');
	$('#guestnome').val('');
	$('#guesttelefone').val('');
	$('#guestemail').val('');
	$('#guestFormModal').show();
}

function showEditGuestForm(id, nome, telefone, email) {
	$('#formTitle').text('Editar Hóspede');
	$('#guestid').val(id);
	$('#guestnome').val(nome);
	$('#guesttelefone').val(telefone);
	$('#guestemail').val(email);
	$('#guestFormModal').show();
}

function closeGuestForm() {
	$('#guestFormModal').hide();
}

function addGuest() {
	const guest = {
		nome: $('#guestnome').val(),
		telefone: $('#guesttelefone').val(),
		email: $('#guestemail').val()
	};
	$.ajax({
		url: 'api/hospede/',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		},
		error: function() {
			alert('Erro ao adicionar o hóspede.');
		}
	});
}

function updateGuest(id) {
	const guest = {
		nome: $('#guestnome').val(),
		telefone: $('#guesttelefone').val(),
		email: $('#guestemail').val()
	};
	$.ajax({
		url: `api/hospede/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(guest),
		success: function() {
			closeGuestForm();
			loadGuests();
		},
		error: function() {
			alert('Erro ao editar o hóspede.');
		}
	});
}

function deleteGuest(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `api/hospede/${id}`,
			type: 'DELETE',
			success: function() {
				loadGuests();
			},
			error: function() {
				alert('Erro ao deletar o hóspede.');
			}
		});
	}
}
