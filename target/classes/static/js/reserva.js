$(document).ready(function() {
	loadGuests();

	$('#reservationForm').submit(function(event) {
		event.preventDefault();
		const id = $('#reservationid').val();
		if (id) {
			updateReservation(id);
		} else {
			addReservation();
		}
	});
});

function loadGuests() {
	$.getJSON('api/reserva', function(data) {
		$('#reservationTableBody').empty();
		data.forEach(reservation => {
			$('#reservationTableBody').append(
				`<tr>
				   <td>${reservation.hospede.nome}</td>
				   <td>${reservation.quarto.numero}</td>
                   <td>${reservation.checkInDate}</td>
                   <td>${reservation.checkOutDate}</td>
                   <td>
                    <button class="btn btn-sm btn-warning" onclick="showEditReservationForm(${reservation.id},'${reservation.hospede.id}','${reservation.quarto.id}','${reservation.checkInDate}','${reservation.checkOutDate}')">Editar</button>
                    <button class="btn btn-sm btn-danger" onclick="deleteReservation(${reservation.id})">Deletar</button>
                   </td>
                </tr>`
			);
		});
	});
}

function showAddReservationtForm() {
	$('#formTitle').text('Adicionar reserva');
	$('#reservationid').val('');
	$('#reservationguest').val('');
	$('#reservationroom').val('');
	$('#reservationcheckin').val('');
	$('#reservationcheckout').val('');
	$('#reservationFormModal').show();
}

function showEditReservationForm(id, hospede, quarto, checkInDate, checkOutDate) {
	$('#formTitle').text('Editar reserva');
	$('#reservationid').val(id);
	$('#reservationguest').val(hospede);
	$('#reservationroom').val(quarto);
	$('#reservationcheckin').val(checkInDate);
	$('#reservationcheckout').val(checkOutDate);
	$('#reservationFormModal').show();
}

function closeReservationForm() {
	$('#reservationFormModal').hide();
}

function addReservation() {
	const reservation = {
		hospede: {id: $('#reservationguest').val()  },
		quarto: {id: $('#reservationroom').val() },
		checkInDate:  $('#reservationcheckin').val() ,
		checkOutDate:  $('#reservationcheckout').val() 
	};
	$.ajax({
		url: 'api/reserva',
		type: 'POST',
		contentType: 'application/json',
		data: JSON.stringify(reservation),
		success: function() {
			closeGuestForm();
			loadGuests();
		},
		error: function() {
			alert('Erro ao adicionar o reserva.');
		}
	});
}

function updateGuest(id) {
	const reservation = {
		guest: {id: $('#reservationguest').val() },
		room: {id: $('#reservationroom').val() },
		checkInDate: {id: $('#reservationcheckin').val() },
		checkOutDate: {id: $('#reservationcheckout').val() }
	};
	$.ajax({
		url: `api/reserva/${id}`,
		type: 'PUT',
		contentType: 'application/json',
		data: JSON.stringify(reservation),
		success: function() {
			closeGuestForm();
			loadGuests();
		},
		error: function() {
			alert('Erro ao editar a reserva.');
		}
	});
}

function deleteGuest(id) {
	if (confirm('Realmente vai deletar?')) {
		$.ajax({
			url: `api/reserva/${id}`,
			type: 'DELETE',
			success: function() {
				loadGuests();
			},
			error: function() {
				alert('Erro ao deletar a reserva.');
			}
		});
	}
}